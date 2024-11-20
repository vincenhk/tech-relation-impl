package co.imp.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationResponseDto<U> {
    private List<U> content;
    private Integer pageSize;
    private Integer currentPage;
    private Integer totalPage;
    private long totalRecord;
}
