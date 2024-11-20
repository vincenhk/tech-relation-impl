package co.imp.tech.controller.base;

import co.imp.tech.dto.PaginationResponseDto;
import co.imp.tech.dto.ResponseDto;
import co.imp.tech.enums.ResponseStatusCode;
import org.springframework.http.ResponseEntity;

public class BaseController {
    /**
     * Response with parameter only data
     *
     * @param data , main content response from data build
     * @return , <T> ResponseEntity<ResponseDto<T>>
     */
    protected <T> ResponseEntity<ResponseDto<T>> buildResponse(T data) {
        var responseStatusCode = ResponseStatusCode.SUCCESS;
        return buildResponse(responseStatusCode, data);
    }

    /**
     * Response using Status Code and data response content
     *
     * @param responseStatusCode , response status from ResponseStatusCode.class
     * @param data               , main content response from data build
     * @return , ResponseEntity<ResponseDto<T>>
     */
    protected <T> ResponseEntity<ResponseDto<T>> buildResponse(ResponseStatusCode responseStatusCode, T data) {
        return buildResponse(responseStatusCode, data, "");
    }

    /**
     * Response using ResponseStatusCode, data and message without pagination
     *
     * @param responseStatusCode , response status from ResponseStatusCode.class
     * @param data               , main content response from data build
     * @param message            , input message from response. The default from ResponseStatusCode.getMessage()
     * @return <T> ResponseEntity<ResponseDto<T>>
     */
    protected <T> ResponseEntity<ResponseDto<T>> buildResponse(ResponseStatusCode responseStatusCode, T data, String message) {
        return buildPaginationResponse(responseStatusCode, data, new PaginationResponseDto(), message);
    }

    /**
     * Response Root builder using parameter responseStatusCode, data, paginationResponse and message
     *
     * @param responseStatusCode    , response status from ResponseStatusCode.class
     * @param data                  , main content response from data build
     * @param paginationResponseDto , if pagination needed rifer to PaginationResponseDto
     * @param message               , input message from response. The default from ResponseStatusCode.getMessage()
     * @return <T> ResponseEntity<ResponseDto<T>>
     */
    protected <T> ResponseEntity<ResponseDto<T>> buildPaginationResponse(ResponseStatusCode responseStatusCode, T data, PaginationResponseDto paginationResponseDto, String message) {
        ResponseDto<T> response = new ResponseDto<T>();
        response.setApiVersion("1.0");
        response.setOrganization("TeventID");
        response.setCode(responseStatusCode.getCode());
        response.setTitle(responseStatusCode.getTitle());
        response.setMessage(message == null || message.isEmpty() || message.isBlank() ? responseStatusCode.getDesc() : message);
        response.setData(data);
        response.setPageSize(paginationResponseDto.getPageSize());
        response.setCurrentPage(paginationResponseDto.getCurrentPage());
        response.setTotalPages(paginationResponseDto.getTotalPage());
//        response.setTotalRecords(paginationResponseDto.getTotalRecord());

        return ResponseEntity.status(responseStatusCode.getHttpCode()).body(response);
    }
}
