package doctintuc.com.websitedoctintuc.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import doctintuc.com.websitedoctintuc.domain.entity.base.AbstractBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Entity
public class Comment extends AbstractBase {

    @Column(name = "content")
    private String content;
//    private String image;

    @ManyToOne
    @JoinColumn(name = "account_id" , referencedColumnName = "id")
    @JsonIgnoreProperties("comments")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "news_id" , referencedColumnName = "id")
    private News news;
}
