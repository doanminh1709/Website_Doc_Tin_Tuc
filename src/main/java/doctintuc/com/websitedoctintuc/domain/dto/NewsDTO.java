package doctintuc.com.websitedoctintuc.domain.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class NewsDTO {
    private String newsName;

    private String title;

    private String content;

    private String tags;

    private MultipartFile video;

    private MultipartFile podcast;
}
