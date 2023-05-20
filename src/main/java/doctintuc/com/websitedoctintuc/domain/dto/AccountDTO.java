package doctintuc.com.websitedoctintuc.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AccountDTO {
    private String accountName;

    private String fullName;

    private String password;

    private String email;

    private String birthday;

    private boolean gender;

    private String address;

    private MultipartFile avatar;
}
