package com.elastic.service.producto;

import java.util.List;
import com.elastic.dto.ProductoResponseDTO;
import com.elastic.dto.ProductoRequestDTO;

public interface IProductoService {
	
	List<ProductoResponseDTO> getAll();
	
	ProductoResponseDTO getById(Long id);
	
	ProductoResponseDTO guardar(ProductoRequestDTO req);
	
	ProductoResponseDTO actualizar(Long id, ProductoRequestDTO req);
	
	void deleteById(Long id);
	
	void deleteAll();

}
