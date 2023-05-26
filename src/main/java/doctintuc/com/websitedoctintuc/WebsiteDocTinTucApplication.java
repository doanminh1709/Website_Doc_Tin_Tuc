package doctintuc.com.websitedoctintuc;

import com.github.slugify.Slugify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebsiteDocTinTucApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteDocTinTucApplication.class, args);
    }

    @Bean
    public Slugify slugify() {
        return new Slugify();
    }
}
