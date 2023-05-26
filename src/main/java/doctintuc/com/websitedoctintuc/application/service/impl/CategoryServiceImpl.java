package doctintuc.com.websitedoctintuc.application.service.impl;

import doctintuc.com.websitedoctintuc.application.constants.CommonConstant;
import doctintuc.com.websitedoctintuc.application.constants.DevMessageConstant;
import doctintuc.com.websitedoctintuc.application.repository.CategoryRepository;
import doctintuc.com.websitedoctintuc.application.service.ICategoryService;
import doctintuc.com.websitedoctintuc.config.exception.VsException;
import doctintuc.com.websitedoctintuc.domain.dto.CategoryDTO;
import doctintuc.com.websitedoctintuc.domain.entity.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public Category create(CategoryDTO categoryDTO) {
        if (repository.existsByCategoryName(categoryDTO.getCategoryName())) {
            throw new VsException(String.format(DevMessageConstant.Common.EXITS_USERNAME, categoryDTO.getCategoryName()));
        }
        return repository.save(modelMapper.map(categoryDTO, Category.class));
    }

    @Override
    public Category getCategory(Integer id) {
        if (!repository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID, CommonConstant.ClassName.CATEGORY_CLASS_NAME, id));
        }
        return repository.findById(id).get();
    }

    @Override
    public List<Category> searchAll(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public Category update(Integer id, CategoryDTO categoryDTO) {

        Optional<Category> found_category = repository.findById(id);
        if (found_category.isPresent()) {
            if (repository.existsByCategoryName(categoryDTO.getCategoryName())) {
                throw new VsException(String.format(DevMessageConstant.Common.EXITS_USERNAME, categoryDTO.getCategoryName()));
            }
            Optional<Category> category = Optional.ofNullable(modelMapper.map(categoryDTO, Category.class));
            category.get().setId(id);
            category.get().setCreateBy(found_category.get().getCreateBy());
            return repository.save(category.get());
        } else {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID, CommonConstant.ClassName.CATEGORY_CLASS_NAME, id));
        }
    }

    @Override
    public String delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new VsException(String.format(DevMessageConstant.Common.NOT_FOUND_OBJECT_BY_ID, CommonConstant.ClassName.CATEGORY_CLASS_NAME, id));
        }
        repository.deleteById(id);
        return DevMessageConstant.Common.NOTIFICATION_DELETE_SUCCESS;
    }

    @Override
    public List<Category> searchCategory() {
        return repository.findAll();
    }
}
