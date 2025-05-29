package com.elastic.repository.elasticsearch;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elastic.entity.ProductoEntity;

@Repository
public interface IProductoElasticsearchRepository extends ElasticsearchRepository<ProductoEntity, Long> {

	/**
	 * Busqueda por nombre (text search)
	 * @param nombre
	 * @return
	 */
    List<ProductoEntity> findByNombre(String nombre);   

    /**
     * Busqueda por nombre que contenga texto (full-text search)
     * @param nombre
     * @return
     */
    List<ProductoEntity> findByNombreContaining(String nombre);    

    /**
     * Busqueda por descripcion que contenga texto
     * @param descripcion
     * @return
     */
    List<ProductoEntity> findByDescripcionContaining(String descripcion);
    
    /**
     * Busqueda combinada en nombre y descripcion
     * @param texto
     * @return
     */
    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"nombre^2\", \"descripcion\"]}}")
    List<ProductoEntity> findByNombreOrDescripcion(String texto);
    
    /**
     * Búsqueda por codigo exacto (keyword search)
     * @param codigo
     * @return
     */
    List<ProductoEntity> findByCodigo(String codigo);    

    /**
     * Busqueda por rango de precios
     * @param precioMin
     * @param precioMax
     * @return
     */
    List<ProductoEntity> findByPrecioBetween(Double precioMin, Double precioMax);
    
    /**
     * Busqueda por productos activos
     * @return
     */
    List<ProductoEntity> findByActivoTrue();
    
    /**
     * Busqueda con paginacion
     * @param nombre
     * @param pageable
     * @return
     */
    Page<ProductoEntity> findByNombreContaining(String nombre, Pageable pageable);
    
    /**
     * Busqueda booleana personalizada
     * @param nombre
     * @param precioMin
     * @param precioMax
     * @return
     */
    @Query("{\"bool\": {\"must\": [{\"match\": {\"nombre\": \"?0\"}}], \"filter\": [{\"term\": {\"activo\": true}}, {\"range\": {\"precio\": {\"gte\": ?1, \"lte\": ?2}}}]}}")
    List<ProductoEntity> findByNombreAndPrecioRange(String nombre, Double precioMin, Double precioMax);
    
    /**
     * Busqueda fuzzy (tolerante a errores tipograficos)
     * @param nombre
     * @return
     */
    @Query("{\"fuzzy\": {\"nombre\": {\"value\": \"?0\", \"fuzziness\": \"AUTO\"}}}")
    List<ProductoEntity> findByNombreFuzzy(String nombre);
    
    /**
     * Busqueda por multiples campos con boost
     * @param texto
     * @return
     */
    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"nombre^3\", \"descripcion^1\", \"codigo^2\"], \"type\": \"best_fields\"}}")
    List<ProductoEntity> busquedaMulticampo(String texto);

}
