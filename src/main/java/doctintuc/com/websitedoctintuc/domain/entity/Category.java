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
@Table(name = "categories")
@Entity
public class Category extends AbstractBase {

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private List<Type> types;
}
