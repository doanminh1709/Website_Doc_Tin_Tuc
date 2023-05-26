package doctintuc.com.websitedoctintuc.adapter.web.rest;

import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Api(tags = "News Resource")
public interface NewsResource {

    @ApiOperation(value = "Create news")
    @PostMapping("/admin/create-news")
    ResponseEntity<?> create(@RequestBody NewsDTO newsDTO);

    @ApiOperation(value = "Get news by id")
    @GetMapping("/no-auth/get-news/{id}")
    ResponseEntity<?> get(@PathVariable("id") Integer id);

    @ApiOperation(value = "Get news by id")
    @PatchMapping("/admin/update/{id}")
    ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody NewsDTO newsDTO);

    @ApiOperation(value = "Search all news")
    @GetMapping("/no-auth/search-all")
    ResponseEntity<?> searchAll(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                @RequestParam(name = "page", required = false, defaultValue = "10") Integer size);

    @ApiOperation(value = "Delete news by id")
    @GetMapping("/admin/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Integer id);


    @ApiOperation(value = "Get favorite news")
    @GetMapping("/no-auth/favorite-new")
    ResponseEntity<?> getFavoriteNews();

    @ApiOperation(value = "Get least news")
    @GetMapping("/no-auth/least-new")
    ResponseEntity<?> getLeastNews();

    @ApiOperation(value = "Paginate home page")
    @GetMapping("/no-auth/paginate-home")
    ResponseEntity<?> paginateHomePage(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                       @RequestParam(name = "page-size", required = false, defaultValue = "10") Integer size);

    @ApiOperation(value = "Update view count")
    @GetMapping("/no-auth/set-view/{id}")
    ResponseEntity<?> setView(@PathVariable("id") Integer id);

}