package co.imp.tech.utils;

import co.imp.tech.dto.PaginationResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public class BuilderPageUtil {
    public static <D, U> PaginationResponseDto<U> builderPageResponse (Page<D> data, Class<U> clazz ){
        List<U> content = GenericMapperUtil.toDtoList(data.getContent(), clazz);
        return PaginationResponseDto.<U>builder()
                .content(content)
                .currentPage(data.getNumber())
                .pageSize(data.getSize())
                .totalRecord(data.getTotalElements())
                .totalPage(data.getTotalPages())
                .build();
    }
}
