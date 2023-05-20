package doctintuc.com.websitedoctintuc.application.repository;

import doctintuc.com.websitedoctintuc.domain.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
}
