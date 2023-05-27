package doctintuc.com.websitedoctintuc.application.service.impl;

import doctintuc.com.websitedoctintuc.application.constants.CommonConstant;
import doctintuc.com.websitedoctintuc.application.constants.DevMessageConstant;
import doctintuc.com.websitedoctintuc.application.constants.EnumRole;
import doctintuc.com.websitedoctintuc.application.jwt.JwtUtils;
import doctintuc.com.websitedoctintuc.application.repository.RoleRepository;
import doctintuc.com.websitedoctintuc.application.repository.UserRepository;
import doctintuc.com.websitedoctintuc.application.request.LoginRequest;
import doctintuc.com.websitedoctintuc.application.response.UserResponse;
import doctintuc.com.websitedoctintuc.application.service.IUserService;
import doctintuc.com.websitedoctintuc.application.service.user_detail.UserDetailImp;
import doctintuc.com.websitedoctintuc.application.utils.GetPrincipal;
import doctintuc.com.websitedoctintuc.config.exception.VsException;
import doctintuc.com.websitedoctintuc.domain.dto.UserDTO;
import doctintuc.com.websitedoctintuc.domain.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final GetPrincipal getPrincipal;

    @Override
    public User create(UserDTO accountDTO) {
        if (userRepository.existsByUsername(accountDTO.getUsername())) {
            throw new VsException(String.format(DevMessageConstant.Common.EXITS_USERNAME, accountDTO.getUsername()));
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(CommonConstant.FORMAT_DATE_PATTERN);
            Date birthday = null;
            if (accountDTO.getBirthday() != null) {
                birthday = sdf.parse(accountDTO.getBirthday());
            }
            //Check current login
            String createBy;
            if (getPrincipal.getCurrentPrincipal() != null){
                if (getPrincipal.getCurrentPrincipal().equals(EnumRole.ROLE_ADMIN.toString())){
                    createBy = CommonConstant.ROLE__ADMIN;
                }else{
                    createBy = CommonConstant.ROLE_SUPER_ADMIN;
                }
            }else{
                createBy = CommonConstant.ROLE_USER;
            }
            User account = new User();
            account.setEmail(accountDTO.getEmail());
            account.setGender(accountDTO.getGender());
            account.setAddress(accountDTO.getAddress());
            account.setFullName(accountDTO.getFullName());
            account.setUsername(accountDTO.getUsername());
            account.setPhone(accountDTO.getPhone());
            account.setAvatar(accountDTO.getAvatar());
//            PropertyMap<UserDTO, User> userMap = new PropertyMap<>() {
//                @Override
//                protected void configure() {
//                    skip().setBirthday(null);
//                    skip().setAvatar(null);
//                    skip().setCreateBy(null);
//                    skip().setLastModifiedBy(null);
//                    skip().setPassword(null);
//                    skip().setRole(null);
//                }
//            };
//            modelMapper.addMappings(userMap);
//            account = modelMapper.map(accountDTO, User.class);
            account.setBirthday(birthday);
            account.setCreateBy(createBy);
//            account.setLastModifiedBy(CommonConstant.ROLE_SUPER_ADMIN);
            account.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));
            account.setRole(roleRepository.findRoleByRoleName(EnumRole.ROLE_USER));

            return userRepository.save(account);
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return null;
    }

    @Override
    public User get(int id) {
        Optional<User> account = userRepository.findById(id);
        if (ObjectUtils.isEmpty(account)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                    User.class.getName(), id));
        }
        return account.get();
    }

    @Override
    public User update(int id, UserDTO accountDTO) {
        if (!userRepository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                    CommonConstant.ClassName.USER_CLASS_NAME, id));
        } else {
            Optional<User> findAccount = userRepository.findById(id);
            if (findAccount.get().getUsername().equals(accountDTO.getUsername()) ||
                    !userRepository.existsByUsername(accountDTO.getUsername())) {
                String lastModifiedBy;
                if (getPrincipal.getCurrentPrincipal() != null){
                    if (getPrincipal.getCurrentPrincipal().equals(EnumRole.ROLE_ADMIN.toString())){
                        lastModifiedBy = CommonConstant.ROLE__ADMIN;
                    }else{
                        lastModifiedBy = CommonConstant.ROLE_SUPER_ADMIN;
                    }
                }else{
                    lastModifiedBy = CommonConstant.ROLE_USER;
                }
                Date birthday = null;
                try {
                    if (accountDTO.getBirthday() != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat(CommonConstant.FORMAT_DATE_PATTERN);
                        birthday = sdf.parse(accountDTO.getBirthday());
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
                User account = modelMapper.map(accountDTO, User.class);
                account.setBirthday(birthday);
                account.setAvatar(accountDTO.getAvatar());
                account.setId(id);
                account.setCreateBy(findAccount.get().getCreateBy());
                account.setLastModifiedBy(lastModifiedBy);
                account.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));
                return userRepository.save(account);
            } else {
                throw new VsException(String.format(DevMessageConstant.Common.EXITS_USERNAME,
                        accountDTO.getUsername()));
            }
        }
    }

    @Override
    public String delete(int id) {
        if (!userRepository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                    CommonConstant.ClassName.USER_CLASS_NAME, id));
        } else {
            userRepository.deleteById(id);
        }
        return DevMessageConstant.Common.NOTIFICATION_DELETE_SUCCESS;
    }

    @Override
    public List<User> searchAll(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public UserResponse login(LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailImp user = (UserDetailImp) authentication.getPrincipal();
            String accessToken = jwtUtils.generateTokenByUsername(user.getUsername());
            List<String> role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            return new UserResponse(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getBirthday(),
                    user.getGender(),
                    user.getAvatar(),
                    accessToken, role);
        } catch (BadCredentialsException e) {
            log.error(String.valueOf(e));
            SecurityContextHolder.clearContext();
        }
        return null;
    }

    @Override
    public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
        return DevMessageConstant.Common.LOGOUT;
    }
}
