package doctintuc.com.websitedoctintuc.adapter.web.rest.impl;

import doctintuc.com.websitedoctintuc.adapter.web.base.RestApiV1;
import doctintuc.com.websitedoctintuc.adapter.web.base.VsResponseUtil;
import doctintuc.com.websitedoctintuc.adapter.web.rest.UserResource;
import doctintuc.com.websitedoctintuc.application.service.IUserService;
import doctintuc.com.websitedoctintuc.domain.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestApiV1
@RequiredArgsConstructor
public class UserResourceImpl implements UserResource {
    private final IUserService userService;

    @Override
    public ResponseEntity<?> create(UserDTO userDTO) {
        return VsResponseUtil.ok(userService.create(userDTO));
    }

    @Override
    public ResponseEntity<?> get(Integer id) {
        return VsResponseUtil.ok(userService.get(id));
    }

    @Override
    public ResponseEntity<?> update(Integer id, UserDTO userDTO) {
        return VsResponseUtil.ok(userService.update(id, userDTO));
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        return VsResponseUtil.ok(userService.delete(id));
    }

    @Override
    public ResponseEntity<?> searchAll(Integer page, Integer size) {
        return VsResponseUtil.ok(userService.searchAll(page, size));
    }

    @Override
    public ResponseEntity<?> login(String username , String password) {
        return VsResponseUtil.ok(userService.login("test123" , "123"));
    }

    @Override
    public ResponseEntity<?> logout(Authentication authentication , HttpServletRequest request , HttpServletResponse response) {
        return VsResponseUtil.ok(userService.logout(authentication , request , response));
    }
}
