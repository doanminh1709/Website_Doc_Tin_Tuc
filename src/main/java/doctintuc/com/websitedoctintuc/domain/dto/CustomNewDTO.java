package doctintuc.com.websitedoctintuc.domain.dto;

import doctintuc.com.websitedoctintuc.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomNewDTO {

    private int id;
    private String title;

    private String content;

    private String author;

    private String description;

    private String thumbnail;

    private int view;

    private Category category;
}
