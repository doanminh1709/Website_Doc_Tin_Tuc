package doctintuc.com.websitedoctintuc.application.service.impl;

import doctintuc.com.websitedoctintuc.application.constants.CommonConstant;
import doctintuc.com.websitedoctintuc.application.constants.DevMessageConstant;
import doctintuc.com.websitedoctintuc.application.repository.CategoryRepository;
import doctintuc.com.websitedoctintuc.application.repository.NewsRepository;
import doctintuc.com.websitedoctintuc.application.service.INewsService;
import doctintuc.com.websitedoctintuc.config.exception.VsException;
import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import doctintuc.com.websitedoctintuc.domain.entity.Category;
import doctintuc.com.websitedoctintuc.domain.entity.News;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements INewsService {

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    @Override
    public News create(NewsDTO newsDTO) {
        if (newsRepository.existsByTitle(newsDTO.getTitle())) {
            throw new VsException(DevMessageConstant.Common.DUPLICATE_NAME, newsDTO.getTitle());
        }
        Optional<Category> category = categoryRepository.findById(newsDTO.getCategoryId());
        if (category.isEmpty()) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                    CommonConstant.ClassName.CATEGORY_CLASS_NAME, newsDTO.getCategoryId()));
        }
        News news = new News(
                newsDTO.getTitle(),
                newsDTO.getContent(),
                newsDTO.getAuthor(),
                newsDTO.getDescription(),
                newsDTO.getThumbnail());
        news.setCategory(category.get());
        return newsRepository.save(news);
    }

    @Override
    public News get(Integer id) {
        if (!newsRepository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                    CommonConstant.ClassName.NEWS_CLASS_NAME, id));
        }
        return newsRepository.findById(id).get();
    }

    @Override
    public News update(Integer id, NewsDTO newsDTO) {

        Optional<News> foundNews = newsRepository.findById(id);
        if (foundNews.isPresent()) {
            if (newsRepository.existsByTitle(newsDTO.getTitle())) {
                throw new VsException(DevMessageConstant.Common.DUPLICATE_NAME, newsDTO.getTitle());
            }
            Optional<Category> category = categoryRepository.findById(newsDTO.getCategoryId());
            if (category.isEmpty()) {
                throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                        CommonConstant.ClassName.CATEGORY_CLASS_NAME, newsDTO.getCategoryId()));
            }
            News news = new News(
                    newsDTO.getTitle(),
                    newsDTO.getContent(),
                    newsDTO.getAuthor(),
                    newsDTO.getDescription(),
                    newsDTO.getThumbnail()
            );
            news.setId(id);
            news.setCategory(category.get());
            return newsRepository.save(news);
        } else {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                    CommonConstant.ClassName.NEWS_CLASS_NAME, id));
        }
    }

    @Override
    public List<News> searchAll(Integer page, Integer size) {
        return newsRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public String delete(Integer id) {
        if (!newsRepository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                    CommonConstant.ClassName.NEWS_CLASS_NAME, id));
        }
        newsRepository.deleteById(id);
        return DevMessageConstant.Common.NOTIFICATION_DELETE_SUCCESS;
    }

    @Override
    public List<News> getFavoriteNews() {
        if (ObjectUtils.isEmpty(newsRepository.findAll())) {
            throw new VsException(DevMessageConstant.Common.NO_DATA_SELECTED);
        }
        return newsRepository.favoriteNews();
    }

    @Override
    public List<News> getLeastNews() {
        if (ObjectUtils.isEmpty(newsRepository.findAll())) {
            throw new VsException(DevMessageConstant.Common.NO_DATA_SELECTED);
        }
        return newsRepository.leastNews();
    }

    @Override
    public List<News> paginateHomePage(Integer page, Integer size) {
        if (ObjectUtils.isEmpty(newsRepository.findAll())) {
            throw new VsException(DevMessageConstant.Common.NO_DATA_SELECTED);
        }
        return newsRepository.allNews(PageRequest.of(page, size));
    }

    @Override
    public News setView(Integer id) {
        if (!newsRepository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                    CommonConstant.ClassName.NEWS_CLASS_NAME, id));
        }
        News news = newsRepository.getById(id);
        news.setView(news.getView() + 1);
        return newsRepository.save(news);
    }
}