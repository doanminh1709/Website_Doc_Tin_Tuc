package doctintuc.com.websitedoctintuc.adapter.web.rest.impl;

import doctintuc.com.websitedoctintuc.adapter.web.base.RestApiV1;
import doctintuc.com.websitedoctintuc.adapter.web.rest.CategoryResource;
import doctintuc.com.websitedoctintuc.adapter.web.rest.NewsResource;
import doctintuc.com.websitedoctintuc.domain.dto.CategoryDTO;
import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

@RestApiV1
public class NewsResourceImpl implements NewsResource {

    @Override
    public ResponseEntity<?> create(NewsDTO newsDTO) {
        return null;
    }
}
