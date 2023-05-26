package doctintuc.com.websitedoctintuc.adapter.web.rest;

import doctintuc.com.websitedoctintuc.domain.dto.CategoryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Api(tags = "Category Resource")
public interface CategoryResource {

    @ApiOperation(value = "Get all category")
    @GetMapping("/both/search-category")
    ResponseEntity<?> searchCategory(@RequestParam Integer page,
                                     @RequestParam(name = "size", defaultValue = "10") Integer size);

    @ApiOperation(value = "Create new category")
    @PostMapping("/admin/create-category")
    ResponseEntity<?> createCategory(@RequestBody(required = false) CategoryDTO request);


    @ApiOperation(value = "Get category by id")
    @GetMapping("/admin/get-category/{id}")
    ResponseEntity<?> getCategory(@PathVariable("id") Integer id);

    @ApiOperation(value = "Edit category by id")
    @PatchMapping("/admin/update-category/{id}")
    ResponseEntity<?> updateCategory(@PathVariable("id") Integer id,
                                     @RequestBody(required = false) CategoryDTO request);


    @ApiOperation(value = "Get category by id")
    @DeleteMapping("/admin/delete-category/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id);

}
