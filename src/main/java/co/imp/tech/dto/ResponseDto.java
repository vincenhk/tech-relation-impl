package co.imp.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
    private String apiVersion;
    private String organization;
    private String code;
    private String title;
    private String message;
    private T data;
    // Pagination
    private Integer pageSize;
    private Integer currentPage;
    private Integer totalPages;
    private Integer totalRecords;
}
