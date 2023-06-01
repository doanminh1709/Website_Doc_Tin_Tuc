package doctintuc.com.websitedoctintuc.application.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserNewsRequest {
    private int userId;
    private int newsId;
}
