package doctintuc.com.websitedoctintuc.application.service;

import doctintuc.com.websitedoctintuc.domain.dto.CategoryDTO;
import doctintuc.com.websitedoctintuc.domain.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category create(CategoryDTO categoryDTO);
    Category getCategory(Integer id);
    List<Category> searchPageCategory(Integer page, Integer size);
    Category update(Integer id, CategoryDTO categoryDTO);
    String delete(Integer id);
    List<Category> searchAllCategory();

}
