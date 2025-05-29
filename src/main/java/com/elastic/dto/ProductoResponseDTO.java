package com.elastic.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {
    
	@Schema(description = "Id del producto.")
	private Long id;
	@Schema(description = "Nombre del producto.")
    private String nombre;
	@Schema(description = "Descripcion del producto.")
    private String descripcion;
	@Schema(description = "Codigo del producto.")
    private String codigo;
	@Schema(description = "Precio del producto.")
    private Double precio;
	@Schema(description = "Stock del producto.")
    private Integer stock;
	@Schema(description = "¿Esta activo el producto.")
    private Boolean activo;
	@Schema(description = "Fecha de ingreso del producto.")
    private LocalDate fechaRegistro;
}
