package doctintuc.com.websitedoctintuc.adapter.web.rest.impl;

import doctintuc.com.websitedoctintuc.adapter.web.base.RestApiV1;
import doctintuc.com.websitedoctintuc.adapter.web.base.VsResponseUtil;
import doctintuc.com.websitedoctintuc.adapter.web.rest.NewsResource;
import doctintuc.com.websitedoctintuc.application.service.INewsService;
import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RestApiV1
@RequiredArgsConstructor
public class NewsResourceImpl implements NewsResource {

    private final INewsService newsService;

    @Override
    public ResponseEntity<?> create(NewsDTO newsDTO) {
        return VsResponseUtil.ok(newsService.create(newsDTO));
    }

    @Override
    public ResponseEntity<?> get(Integer id) {
        return VsResponseUtil.ok(newsService.get(id));
    }

    @Override
    public ResponseEntity<?> update(Integer id, NewsDTO newsDTO) {
        return VsResponseUtil.ok(newsService.update(id, newsDTO));
    }

    @Override
    public ResponseEntity<?> searchAll(Integer page, Integer size) {
        return VsResponseUtil.ok(newsService.searchAll(page, size));
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        return VsResponseUtil.ok(newsService.delete(id));
    }

    @Override
    public ResponseEntity<?> getFavoriteNews() {
        return VsResponseUtil.ok(newsService.getFavoriteNews());
    }

    @Override
    public ResponseEntity<?> getLeastNews() {
        return VsResponseUtil.ok(newsService.getLeastNews());
    }

    @Override
    public ResponseEntity<?> paginateHomePage(Integer page, Integer size) {
        return VsResponseUtil.ok(newsService.paginateHomePage(page, size));
    }

    @Override
    public ResponseEntity<?> setView(Integer id) {
        return VsResponseUtil.ok(newsService.setView(id));
    }
}
