package doctintuc.com.websitedoctintuc.adapter.web.base;

import doctintuc.com.websitedoctintuc.domain.pagine.PaginateDTO;
import doctintuc.com.websitedoctintuc.domain.pagine.Pagination;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class BasePagination<E, R extends JpaRepository<E, ?>> {

    private R repository;

    public PaginateDTO<?> paginate(Integer page, Integer perpage) {
        if (page == null) page = 0;
        if (perpage == null) perpage = 10;
        Page<E> pageData = repository.findAll(PageRequest.of(page, perpage, Sort.by("createDate").descending()));
        Pagination pagination = new Pagination(page, perpage, pageData.getTotalPages() - 1, pageData.getTotalElements());
        return new PaginateDTO<>(pageData, pagination);
    }
    public PaginateDTO<E> paginate(Integer page, Integer perPage, Page<E> pageData) {
        Pagination pagination = new Pagination(page, perPage, pageData.getTotalPages() - 1, pageData.getTotalElements());
        return new PaginateDTO<>(pageData, pagination);
    }
}
