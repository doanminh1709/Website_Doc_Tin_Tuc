package doctintuc.com.websitedoctintuc.domain.pagine;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pagination {
    private Integer page;
    private Integer perPage;
    private Integer lastPage;
    private Long total;
}
