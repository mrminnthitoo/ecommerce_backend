package com.minthitoo.ecommerce_backend.models.dtos;

import com.minthitoo.ecommerce_backend.utils.enums.SortType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDto {

    private Integer pageNo = 0;
    private Integer pageSize = 0;
    private String sortColumn = "id";
    private SortType sortType = SortType.ASC;

    public Pageable getPage(PageRequestDto pageDto){

        Integer page = Objects.nonNull(pageDto.getPageNo()) ? pageDto.getPageNo() : this.pageNo;
        Integer size = Objects.nonNull(pageDto.getPageSize()) ? pageDto.getPageSize() : this.pageSize;
        String column = Objects.nonNull(pageDto.getSortColumn()) ? pageDto.getSortColumn() : this.sortColumn;
        SortType sortMethod = Objects.nonNull(pageDto.getSortType()) ? pageDto.getSortType() : this.sortType;

        Sort.Order order = sortMethod.equals(SortType.ASC) ? Sort.Order.asc(column) : Sort.Order.desc(column);

        return PageRequest.of(page, size, Sort.by(order));

    }

}
