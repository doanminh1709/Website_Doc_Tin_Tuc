package doctintuc.com.websitedoctintuc.application.service.impl;

import doctintuc.com.websitedoctintuc.application.constants.DevMessageConstant;
import doctintuc.com.websitedoctintuc.application.constants.EnumRole;
import doctintuc.com.websitedoctintuc.application.jwt.JwtUtils;
import doctintuc.com.websitedoctintuc.application.repository.RoleRepository;
import doctintuc.com.websitedoctintuc.application.repository.UserRepository;
import doctintuc.com.websitedoctintuc.application.request.LoginRequest;
import doctintuc.com.websitedoctintuc.application.response.UserResponse;
import doctintuc.com.websitedoctintuc.application.service.IUserService;
import doctintuc.com.websitedoctintuc.application.service.user_detail.UserDetailImp;
import doctintuc.com.websitedoctintuc.application.utils.UploadCloudinary;
import doctintuc.com.websitedoctintuc.config.exception.VsException;
import doctintuc.com.websitedoctintuc.domain.dto.UserDTO;
import doctintuc.com.websitedoctintuc.domain.entity.Role;
import doctintuc.com.websitedoctintuc.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UploadCloudinary cloudinary;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public User create(UserDTO accountDTO) {
        if (userRepository.existsByUsername(accountDTO.getUsername())) {
            throw new VsException(String.format(DevMessageConstant.Common.EXITS_USERNAME, accountDTO.getUsername()));
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date birthday = null;
            if (accountDTO.getBirthday() != null) {
                birthday = sdf.parse(accountDTO.getBirthday());
            }
            String linkImg = cloudinary.getUrlFromFile(accountDTO.getAvatar());
            User account = new User();
            account.setBirthday(birthday);
            account.setEmail(accountDTO.getEmail());
            account.setGender(accountDTO.getGender());
            account.setAddress(accountDTO.getAddress());
            account.setFullName(accountDTO.getFullName());
            account.setUsername(accountDTO.getUsername());
            account.setAvatar(linkImg);
            account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
            Role role = roleRepository.findRoleByRoleName(EnumRole.ROLE_USER);
            account.setRole(role);

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
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID, User.class.getName(), id));
        }
        return account.get();
    }

    @Override
    public User update(int id, UserDTO accountDTO) {
        if (!userRepository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID, User.class.getName(), id));
        } else {
            try {
                Optional<User> findAccount = userRepository.findById(id);
                String linkUrl;
                if (accountDTO.getAvatar().getOriginalFilename() == null) {
                    linkUrl = findAccount.get().getAvatar();
                }
                if (findAccount.get().getUsername().equals(accountDTO.getUsername()) ||
                        !userRepository.existsByUsername(accountDTO.getUsername())) {
                    Date birthday = null;
                    if (accountDTO.getBirthday() != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        birthday = sdf.parse(accountDTO.getBirthday());
                    }
                    linkUrl = cloudinary.getUrlFromFile(accountDTO.getAvatar());
                    User account = modelMapper.map(accountDTO, User.class);
                    account.setBirthday(birthday);
                    account.setAvatar(linkUrl);
                    account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
                } else {
                    throw new VsException(String.format(DevMessageConstant.Common.EXITS_USERNAME, accountDTO.getUsername()));
                }
            } catch (Exception e) {
                log.error(String.valueOf(e));
            }
            return null;
        }
    }

    @Override
    public String delete(int id) {
        if (!userRepository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID, "User", id));
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
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailImp user = (UserDetailImp) authentication.getPrincipal();
            if (user != null) {
                String accessToken = jwtUtils.generateTokenByUsername(user.getUsername());
                String birthday = user.getBirthday().toString();
                return new UserResponse(
                        user.getId(),
                        user.getFullName(),
                        user.getEmail(),
                        birthday,
                        user.getGender(),
                        user.getAvatar(),
                        accessToken);
            }
        } catch (Exception e) {
            log.error(String.valueOf(e));
            SecurityContextHolder.clearContext();
        }
        return null;
    }

    @Override
    public User logout() {
        return null;
    }
}
