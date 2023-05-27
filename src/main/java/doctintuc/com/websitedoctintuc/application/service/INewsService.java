package doctintuc.com.websitedoctintuc.application.service;

import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import doctintuc.com.websitedoctintuc.domain.entity.News;

import java.awt.print.Pageable;
import java.util.List;

public interface INewsService {
    News create(NewsDTO newsDTO);
    News get(Integer id);
    News update(Integer id, NewsDTO newsDTO);
    List<News> searchAll(Integer page, Integer size);
    List<News> searAllNotPaginate();
    String delete(Integer id);
    List<News> getFavoriteNews();
    List<News> getLeastNews();
    List<News> paginateHomePage(Integer page , Integer size);
    News setView(Integer id);
    List<News> filterNewsByCategory(Integer page , Integer size , String author , String title , Integer categoryId , String filter );
    List<News> searchNews(Integer page , Integer size , String key);
}
