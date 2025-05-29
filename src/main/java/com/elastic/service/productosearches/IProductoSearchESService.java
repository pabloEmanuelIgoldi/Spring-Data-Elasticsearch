package com.elastic.service.productosearches;

import java.util.List;

import com.elastic.dto.PagedDTO;
import com.elastic.dto.ProductoResponseDTO;

public interface IProductoSearchESService {
	
    List<ProductoResponseDTO> busquedaTextoCompleto(String texto);
    
    List<ProductoResponseDTO> buscarPorNombreES(String nombre);
    
    List<ProductoResponseDTO> buscarPorDescripcionES(String descripcion);
    
    PagedDTO<List<ProductoResponseDTO>> buscarConPaginacion(String nombre, int page, int size);
    
    List<ProductoResponseDTO> busquedaAvanzada(String nombre, Double precioMin, Double precioMax);
    
    List<ProductoResponseDTO> busquedaFuzzy(String nombre);
    
    List<ProductoResponseDTO> busquedaMulticampo(String texto);

    void sincronizarConElasticsearch() ;
    
    void limpiarIndiceElasticsearch() ;
}
