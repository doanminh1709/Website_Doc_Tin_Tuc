package doctintuc.com.websitedoctintuc.domain.pagine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaginationResponseDTO<T> {
    private Integer status;
    private String message;
    private PaginationDTO<T> result;
}
