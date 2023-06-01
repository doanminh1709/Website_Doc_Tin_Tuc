package doctintuc.com.websitedoctintuc.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import doctintuc.com.websitedoctintuc.application.constants.CommonConstant;
import doctintuc.com.websitedoctintuc.domain.entity.base.AbstractBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AbstractBase {

    @Column(name = "full_name")
    private String fullName;

//    @NotEmpty(message = "Username must not be empty")
//    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters")
//    @Column(name = "username", nullable = false, unique = true)
    private String username;


//    @NotEmpty(message = "Password must not be empty")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+-=\\[\\]{};:'\"<>,.?/]).{8,}$",
//            message = "Password is not valid")
    @Column(name = "password", nullable = false)
    private String password;

//    @Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
//            message = "Email is not format")
//    @Column(name = "email", nullable = false)
    private String email;

    @JsonFormat(pattern = CommonConstant.FORMAT_DATE_PATTERN)
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    private String gender;
//    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 characters")
    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "avatar")
    private String avatar;

    @JsonIgnore
    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @JsonIgnoreProperties("users")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(fullName, user.fullName) && Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) && Objects.equals(email, user.email) &&
                Objects.equals(birthday, user.birthday) && Objects.equals(gender, user.gender) &&
                Objects.equals(phone, user.phone) && Objects.equals(address, user.address) &&
                Objects.equals(avatar, user.avatar) && Objects.equals(comments, user.comments) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, username, password, email, birthday, gender, phone, address, avatar, comments, role);
    }
}
