package doctintuc.com.websitedoctintuc.application.service;

import doctintuc.com.websitedoctintuc.domain.dto.CategoryDTO;
import doctintuc.com.websitedoctintuc.domain.entity.Category;
import doctintuc.com.websitedoctintuc.domain.entity.User;
import doctintuc.com.websitedoctintuc.domain.pagine.PaginateDTO;

import java.util.List;

public interface ICategoryService {
    Category create(CategoryDTO categoryDTO);
    Category getCategory(Integer id);
    PaginateDTO<Category> searchPageCategory(Integer page, Integer size);
    Category update(Integer id, CategoryDTO categoryDTO);
    String delete(Integer id);
    List<Category> searchAllCategory();

}
