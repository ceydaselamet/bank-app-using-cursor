package com.banking.core.utils.paging;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    @Parameter(description = "Page number (1-based)", example = "1")
    private int page = 1;

    @Parameter(description = "Number of items per page", example = "10")
    private int size = 10;

    @Parameter(description = "Sort field", example = "id")
    private String sortField = "id";

    @Parameter(description = "Sort direction (asc or desc)", example = "desc")
    private String sortDirection = "desc";

    public Pageable toPageable() {
        Sort sort = Sort.by(
            Sort.Direction.fromString(sortDirection),
            sortField
        );
        // Convert 1-based page number to 0-based
        return org.springframework.data.domain.PageRequest.of(page - 1, size, sort);
    }
} 