package com.elastic.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.elastic.dto.PagedDTO;
import com.elastic.dto.ProductoResponseDTO;
import com.elastic.entity.ProductoEntity;

public class ProductoMapper {

	
	public static ProductoResponseDTO entityToDTO(ProductoEntity p) {
		return new ProductoResponseDTO(p.getId(), 
					 				   p.getNombre(),
					 				   p.getDescripcion(),
					 				   p.getCodigo(),
					 				   p.getPrecio(), 		
					 				   p.getStock(),
					 				   p.getActivo(),
					 				   p.getFechaRegistro());
	}
	
	public static List<ProductoResponseDTO> listEntityToListDTO(List<ProductoEntity> list) {
		return list.stream()
				   .map(p -> new ProductoResponseDTO(p.getId(), 
						   							 p.getNombre(),
						   							 p.getDescripcion(),
						   							 p.getCodigo(),
						   							 p.getPrecio(), 
						   							 p.getStock(),
						   							 p.getActivo(),
						   							 p.getFechaRegistro()						   							 
						   							 ))
				   .collect(Collectors.toList());
	}
	
	public static <E, D> Page<D> mapPage(Page<E> entityPage, Function<E, D> converter) {
        return entityPage.map(converter);
    }
		
    public static PagedDTO<List<ProductoResponseDTO>> buildPagedDTO(Page<ProductoEntity> page) {
        PagedDTO<List<ProductoResponseDTO>> pageInfo = new PagedDTO<>();
        pageInfo.setContent(listEntityToListDTO(page.getContent()));
        pageInfo.setPage(page.getNumber() + 1);
        pageInfo.setSize(page.getSize());
        pageInfo.setTotalElements(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setFirst(page.isFirst());
        pageInfo.setLast(page.isLast());
        pageInfo.setEmpty(page.isEmpty());
        return pageInfo;
    }

}