package doctintuc.com.websitedoctintuc.application.repository;

import doctintuc.com.websitedoctintuc.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
}
