package doctintuc.com.websitedoctintuc.application.service.impl;

import doctintuc.com.websitedoctintuc.application.constants.DevMessageConstant;
import doctintuc.com.websitedoctintuc.application.repository.NewsRepository;
import doctintuc.com.websitedoctintuc.application.service.INewsService;
import doctintuc.com.websitedoctintuc.application.utils.UploadCloudinary;
import doctintuc.com.websitedoctintuc.config.exception.VsException;
import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import doctintuc.com.websitedoctintuc.domain.entity.News;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements INewsService {

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;

    private final UploadCloudinary cloudinary;

    @Override
    public News create(NewsDTO newsDTO) {
        if (newsRepository.existsByNewsName(newsDTO.getNewsName())) {
            throw new VsException(DevMessageConstant.Common.DUPLICATE_NAME, newsDTO.getNewsName());
        }
        try {
            News news = modelMapper.map(newsDTO, News.class);
            news.setThumbnail(cloudinary.getUrlFromFile(newsDTO.getThumbnail()));
            return newsRepository.save(news);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public News get(Integer id) {
        if (!newsRepository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                    News.class.getName(), id));
        }
        return newsRepository.findById(id).get();
    }

    @Override
    public News update(Integer id, NewsDTO newsDTO) {
        if (!newsRepository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                    News.class.getName(), id));
        }
        if (newsRepository.existsByNewsName(newsDTO.getNewsName())) {
            throw new VsException(DevMessageConstant.Common.DUPLICATE_NAME, newsDTO.getNewsName());
        }
        try {
            News news = modelMapper.map(newsDTO, News.class);
            news.setThumbnail(cloudinary.getUrlFromFile(newsDTO.getThumbnail()));
            return newsRepository.save(news);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
                    News.class.getName(), id));
        }
        newsRepository.deleteById(id);
        return DevMessageConstant.Common.NOTIFICATION_DELETE_SUCCESS;
    }

    @Override
    public List<News> getFavoriteNews() {
        return newsRepository.favoriteNews();
    }

    @Override
    public List<News> getLeastNews() {
        return newsRepository.leastNews();
    }

    @Override
    public List<News> paginateHomePage(Integer page, Integer size) {
        return newsRepository.allNews(PageRequest.of(page, size));
    }

    @Override
    public News setView(Integer id) {
        if (!newsRepository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID,
                    News.class.getName(), id));
        }
        News news = newsRepository.getById(id);
        news.setView(news.getView() + 1);
        return newsRepository.save(news);
    }
}
