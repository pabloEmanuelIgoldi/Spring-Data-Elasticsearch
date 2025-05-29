package com.elastic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoDTO {
	
	@Schema(description = "Pagina actual (1-based).")
    private int page; 
	@Schema(description = "Elementos de  la pagina.")
    private int size;
	@Schema(description = "Total de elementos de  la pagina.")
    private long totalElements;
	@Schema(description = "Total de paginas.")
    private int totalPages;
	@Schema(description = "¿Es primera pagina?")
    private boolean first;
	@Schema(description = "¿Es ultima pagina?")
    private boolean last;
	@Schema(description = "Pagina vacia.")
    private boolean empty;
}
