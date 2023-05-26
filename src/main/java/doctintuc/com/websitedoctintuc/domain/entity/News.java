package doctintuc.com.websitedoctintuc.domain.entity;

import doctintuc.com.websitedoctintuc.domain.entity.base.AbstractBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
@Entity
public class News extends AbstractBase {

    @NotBlank(message = "News name is not blank")
    @Column(name = "news_name", nullable = false)
    private String newsName;

    @Column(name = "title")
    private String title;

    @NotBlank(message = "Content is not blank")
    @Column(name = "content", nullable = false, length = 100000)
    private String content;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;


    @Min(value = 0)
    @Column(name = "view")
    private int view;

    @OneToMany(mappedBy = "news", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "news_id" , referencedColumnName = "id")
    private News news;

    public News(int id, String title, String description, String thumbnail) {
        this.setId(id);
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
    }

}
