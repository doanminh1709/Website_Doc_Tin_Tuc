package doctintuc.com.websitedoctintuc.application.repository;

import doctintuc.com.websitedoctintuc.domain.dto.NewsHomeDTO;
import doctintuc.com.websitedoctintuc.domain.entity.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    Boolean existsByTitle(String title);

    @Query(value = "SELECT  u.id , u.title , u.description , u.thumbnail FROM news u ORDER BY u.view DESC LIMIT 5" , nativeQuery = true)
    List<News> favoriteNews();

    @Query(value = "SELECT u.id , u.title , u.description , u.thumbnail FROM news u ORDER BY u.create_date DESC LIMIT 7" , nativeQuery = true)
    List<News> leastNews();

    @Query(value = "SELECT u.id , u.title , u.description , u.thumbnail FROM  news  u ORDER BY u.create_date" , nativeQuery = true)
    List<News> allNews(Pageable pageable);


}
