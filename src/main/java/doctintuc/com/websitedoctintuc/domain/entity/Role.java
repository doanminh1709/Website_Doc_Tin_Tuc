package doctintuc.com.websitedoctintuc.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import doctintuc.com.websitedoctintuc.domain.entity.base.AbstractBase;
import doctintuc.com.websitedoctintuc.enums.EnumRole;
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
@Table(name = "roles")
@Entity
public class Role extends AbstractBase {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private EnumRole roleName;

    @JsonIgnore
    @OneToMany(mappedBy = "role" , cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("role")
    private List<Account> accounts;
}
