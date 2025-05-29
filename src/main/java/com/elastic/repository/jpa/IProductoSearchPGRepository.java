package com.elastic.repository.jpa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.elastic.entity.ProductoEntity;


public interface IProductoSearchPGRepository extends Repository<ProductoEntity, Long>, JpaSpecificationExecutor<ProductoEntity>{ 


	/**
	 * Buscar por codigo unico
	 * @param codigo
	 * @return
	 */
    Optional<ProductoEntity> findByCodigo(String codigo);
    

    /**
     * Buscar productos activos
     * @return
     */
    List<ProductoEntity> findByActivoTrue();
    
    /**
     * Buscar productos por rango de precios
     * @param precioMin
     * @param precioMax
     * @return
     */
    List<ProductoEntity> findByPrecioBetween(Double precioMin, Double precioMax);
    
    /**
     * Buscar productos con stock mayor a un valor
     * @param stock
     * @return
     */
    List<ProductoEntity> findByStockGreaterThan(Integer stock);
    

    /**
     * Buscar productos por fecha de registro
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    List<ProductoEntity> findByFechaRegistroBetween(LocalDate fechaInicio, LocalDate fechaFin);
    
    /**
     * Consulta personalizada para buscar por nombre que contenga un texto
     * @param nombre
     * @return
     */
    @Query("SELECT p FROM ProductoEntity p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND p.activo = true")
    List<ProductoEntity> findByNombreContainingIgnoreCaseAndActivoTrue(@Param("nombre") String nombre);
    
    /**
     * Consulta para obtener productos con bajo stock
     * @param stockMinimo
     * @return
     */
    @Query("SELECT p FROM ProductoEntity p WHERE p.stock <= :stockMinimo AND p.activo = true")
    List<ProductoEntity> findProductosConBajoStock(@Param("stockMinimo") Integer stockMinimo);

}
