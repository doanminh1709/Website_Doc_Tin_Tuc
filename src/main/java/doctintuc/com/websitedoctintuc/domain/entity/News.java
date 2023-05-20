package doctintuc.com.websitedoctintuc.domain.entity;

import doctintuc.com.websitedoctintuc.domain.entity.base.AbstractBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
@Entity
public class News extends AbstractBase {

    @Column(name = "news_name")
    private String newsName;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "tags")
    private String tags;

    @Column(name = "video")
    private String video;

    @Column(name = "podcast")
    private String podcast;

    @OneToMany(mappedBy = "news", fetch = FetchType.EAGER,
            cascade = {CascadeType.REMOVE,
            CascadeType.PERSIST})
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "type_id" , referencedColumnName = "id")
    private Type type;
}
