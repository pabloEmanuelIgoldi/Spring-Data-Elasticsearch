package com.elastic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class PagedResponseDTO<T> extends ApiResponseDTO<T>  {
	
	@Schema(description = "Informacion del paginado.")
    private PageInfoDTO pageInfo;
}
