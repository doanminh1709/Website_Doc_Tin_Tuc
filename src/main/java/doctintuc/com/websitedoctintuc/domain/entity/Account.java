package doctintuc.com.websitedoctintuc.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import doctintuc.com.websitedoctintuc.domain.entity.base.AbstractBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Entity
public class Account extends AbstractBase {

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "address")
    private String address;

    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "account" ,
            fetch = FetchType.EAGER ,
            cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "account_id" , referencedColumnName = "id")
    @JsonIgnoreProperties("accounts")//accounts property will ignore when convert to Json or reserve
    private Role role;
}
