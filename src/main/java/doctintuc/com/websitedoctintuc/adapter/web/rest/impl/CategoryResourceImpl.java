package doctintuc.com.websitedoctintuc.adapter.web.rest.impl;

import doctintuc.com.websitedoctintuc.adapter.web.base.RestApiV1;
import doctintuc.com.websitedoctintuc.adapter.web.base.VsResponseUtil;
import doctintuc.com.websitedoctintuc.adapter.web.rest.CategoryResource;
import doctintuc.com.websitedoctintuc.application.service.ICategoryService;
import doctintuc.com.websitedoctintuc.domain.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RestApiV1
@RequiredArgsConstructor
public class CategoryResourceImpl implements CategoryResource {

    private final ICategoryService categoryService;

    @Override
    public ResponseEntity<?> searchPageCategory(Integer page, Integer size) {
        return VsResponseUtil.ok(categoryService.searchPageCategory(page, size));
    }

    @Override
    public ResponseEntity<?> createCategory(CategoryDTO request) {
        return VsResponseUtil.ok(categoryService.create(request));
    }

    @Override
    public ResponseEntity<?> getCategory(Integer id) {
        return VsResponseUtil.ok(categoryService.getCategory(id));
    }

    @Override
    public ResponseEntity<?> updateCategory(Integer id, CategoryDTO request) {
        return VsResponseUtil.ok(categoryService.update(id, request));
    }

    @Override
    public ResponseEntity<?> deleteCategory(Integer id) {
        return VsResponseUtil.ok(categoryService.delete(id));
    }

    @Override
    public ResponseEntity<?> getAllCategory() {
        return VsResponseUtil.ok(categoryService.searchAllCategory());
    }
}
