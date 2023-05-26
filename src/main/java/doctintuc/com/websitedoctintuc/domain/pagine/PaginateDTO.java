package doctintuc.com.websitedoctintuc.domain.pagine;

import lombok.*;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginateDTO<T> {
    private Page<T> pageData;
    private Pagination pagination;
}
