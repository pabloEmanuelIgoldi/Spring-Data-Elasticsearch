package com.elastic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructorpublic class PagedDTO<T> {
	
	private T content;	
    private int page;
    private int size;
    private long totalElements;
    private int totalPages; 
    private boolean first;
    private boolean last;
    private boolean empty;

}
