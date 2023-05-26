package doctintuc.com.websitedoctintuc.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private int id;
    private String fullName;
    private String email;
    private String birthday;
    private String gender;
    private String avatar;
    private String accessToken;
}
