package doctintuc.com.websitedoctintuc.adapter.web.rest;

import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@Api(tags = "News Resource")
public interface NewsResource {

    @ApiOperation(value = "Create news")
    @PostMapping("/admin/create-new")
    ResponseEntity<?>create(@ModelAttribute NewsDTO newsDTO);


}
