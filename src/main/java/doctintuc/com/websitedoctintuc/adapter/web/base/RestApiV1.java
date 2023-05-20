package doctintuc.com.websitedoctintuc.adapter.web.base;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)//Apply to the element of type or class
@Retention(RetentionPolicy.RUNTIME)//keep while app running
@Documented//help app run automatic
@RestController
@RequestMapping("/api/v1")
public @interface RestApiV1 {
}
