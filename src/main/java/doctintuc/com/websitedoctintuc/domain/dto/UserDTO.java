package doctintuc.com.websitedoctintuc.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserDTO {
    private String fullName;

    private String username;

    private String password;

    private String email;

    private String birthday;

    private String gender;

    private String address;

    private MultipartFile avatar;
}
