package com.elastic.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {
	
	@Schema(description = "Nombre del producto.")
	@NotBlank
    private String nombre;
	@Schema(description = "Descripcion del producto.")
	@NotBlank
    private String descripcion;
	@Schema(description = "Codigo del producto.")
	@NotBlank
    private String codigo;
	@Schema(description = "Precio del producto.")
	@Positive
    private Double precio;
	@Schema(description = "Stock del producto.")
	@Positive
    private Integer stock;
	@Schema(description = "¿Esta activo el producto.")
	@NotNull
    private Boolean activo;    
}
