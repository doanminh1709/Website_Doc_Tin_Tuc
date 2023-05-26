package doctintuc.com.websitedoctintuc.domain.pagine;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginationDTO <T>{
    private T data;
    private Pagination pagination;
}
