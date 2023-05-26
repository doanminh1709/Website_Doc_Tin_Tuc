package doctintuc.com.websitedoctintuc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {
    private String newsName;
    private String content;
    private String title;
    private String author;
    private String phone;
    private String description;
    private MultipartFile thumbnail;
}
