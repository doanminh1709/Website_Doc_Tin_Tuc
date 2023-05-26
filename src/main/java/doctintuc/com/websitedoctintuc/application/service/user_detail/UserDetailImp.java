package doctintuc.com.websitedoctintuc.application.service.user_detail;

import com.fasterxml.jackson.annotation.JsonIgnore;
import doctintuc.com.websitedoctintuc.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserDetailImp implements UserDetails {
    private int id;
    private String fullName;
    private String email;
    private String birthday;
    private String gender;
    private String avatar;
    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailImp(int id, String fullName, String email, String birthday, String gender,
                         String avatar, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.avatar = avatar;
        this.authorities = authorities;
    }

    public static UserDetailImp map(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName().name()));
        return new UserDetailImp(user.getId(), user.getFullName(),
                user.getEmail(), user.getBirthday().toString(),
                user.getGender(), user.getAvatar(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
