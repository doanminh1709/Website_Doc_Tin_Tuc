package doctintuc.com.websitedoctintuc.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import doctintuc.com.websitedoctintuc.domain.entity.base.AbstractBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class News extends AbstractBase {

    @NotBlank(message = "Title is not blank")
    @Column(name = "title", nullable = false, unique = true , length = 100000)
    private String title;

    @NotBlank(message = "Content is not blank")
    @Column(name = "content", nullable = false, length = 100000)
    private String content;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @NotBlank(message = "Thumbnail is not blank")
    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Min(value = 0)
    @Column(name = "view")
    private int view;


    @JsonIgnore
    @OneToMany(mappedBy = "news", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Comment> comments;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public News(String title, String content, String author, String description, String thumbnail) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.description = description;
        this.thumbnail = thumbnail;
        this.setView(0);
    }

    public News(int id, String title, String content, String author, String description, String thumbnail,int view) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.description = description;
        this.thumbnail = thumbnail;
        this.view = view;
    }
}
