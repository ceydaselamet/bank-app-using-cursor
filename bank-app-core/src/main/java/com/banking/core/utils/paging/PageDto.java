package com.banking.core.utils.paging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageDto<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private boolean hasNext;
    private boolean hasPrevious;

    public static <T> PageDto<T> of(Page<T> page) {
        PageDto<T> pageDto = new PageDto<>();
        pageDto.setContent(page.getContent());
        pageDto.setPageNumber(page.getNumber() + 1);
        pageDto.setPageSize(page.getSize());
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setTotalPages(page.getTotalPages());
        pageDto.setFirst(page.isFirst());
        pageDto.setLast(page.isLast());
        pageDto.setHasNext(page.hasNext());
        pageDto.setHasPrevious(page.hasPrevious());
        return pageDto;
    }
} 