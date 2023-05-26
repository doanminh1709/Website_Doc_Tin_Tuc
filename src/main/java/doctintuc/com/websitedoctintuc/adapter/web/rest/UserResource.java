package doctintuc.com.websitedoctintuc.adapter.web.rest;

import doctintuc.com.websitedoctintuc.application.request.LoginRequest;
import doctintuc.com.websitedoctintuc.domain.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Validated
@Api(tags = "Users Resource")
public interface UserResource {

    @ApiOperation(value = "Create new user")
    @PostMapping("/both/create-user")
    ResponseEntity<?> create(@ModelAttribute UserDTO userDTO);

    @ApiOperation(value = "Get user by id")
    @GetMapping("/both/get-user/{id}")
    ResponseEntity<?> get(@PathVariable Integer id);

    @ApiOperation(value = "Update user")
    @PatchMapping("/both/update-user/{id}")
    ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserDTO userDTO);

    @ApiOperation(value = "Delete user by id")
    @DeleteMapping("/admin/delete-user/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id);

    @ApiOperation(value = "Search all user")
    @GetMapping("/admin/search-all")
    ResponseEntity<?> searchAll(@RequestParam(name = "page" , required = false, defaultValue = "0") Integer page,
                                @RequestParam(name = "size" , required = false, defaultValue = "10") Integer size);


    @ApiOperation(value = "Login")
    @PostMapping("/both/login")
    ResponseEntity<?> login(@RequestParam String username , @RequestParam String password);


    @ApiOperation(value = "logout")
    @GetMapping("/both/logout")
    ResponseEntity<?> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response);
}
