package doctintuc.com.websitedoctintuc.domain.pagine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaginationDTO <T>{
    private T data;
    private Pagination pagination;
}
