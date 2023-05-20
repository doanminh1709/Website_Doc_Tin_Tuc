package doctintuc.com.websitedoctintuc.application.repository;

import doctintuc.com.websitedoctintuc.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account , Integer> {
}
