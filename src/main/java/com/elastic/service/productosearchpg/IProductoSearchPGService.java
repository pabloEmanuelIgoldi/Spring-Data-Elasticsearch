package com.elastic.service.productosearchpg;

import java.time.LocalDate;
import java.util.List;

import com.elastic.dto.PagedDTO;
import com.elastic.dto.ProductoResponseDTO;

public interface IProductoSearchPGService {
	
	ProductoResponseDTO buscarPorCodigo(String codigo);
    
    List<ProductoResponseDTO> listarActivos();
    
    PagedDTO<List<ProductoResponseDTO>> paginar (int page, int size, String sortBy, String sortDir);

    List<ProductoResponseDTO> buscarPorRangoPrecio(Double precioMin, Double precioMax);
    
    List<ProductoResponseDTO> buscarConStockMayorA(Integer stock);
    
    List<ProductoResponseDTO> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin);
    
    List<ProductoResponseDTO> buscarPorNombreContiene(String nombre);
    
    List<ProductoResponseDTO> buscarConBajoStock(Integer stockMinimo);
}
