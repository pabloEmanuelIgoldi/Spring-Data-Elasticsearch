package com.elastic.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elastic.dto.ApiResponseDTO;
import com.elastic.dto.ProductoResponseDTO;
import com.elastic.dto.ProductoRequestDTO;
import com.elastic.service.producto.IProductoService;
import com.elastic.util.CodeResponseUtil;
import com.elastic.util.MensajeResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/productos")
@Tag(name = "CRUD Producto", description = "Endpoints para gestionar las operaciones CRUD de productos.")
@Validated
public class ProductoCRUDController {
    
	@Autowired
    private IProductoService productoService;
	
	
	@Operation(summary = "Busqueda total.", description = "Busqueda de todos los productos sin filtro.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),	
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")}
					)
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> listarTodos() {
    	log.info("INGRESO A ProductoController.listarTodos().");
    	List<ProductoResponseDTO> productos =  this.productoService.getAll();
    	
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {   
	 		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(true, 
										   										  MensajeResponseUtil.SUCCESS, 
										   										  CodeResponseUtil.SUCCESS,
										   										  LocalDateTime.now(), 
										   										  productos));
	 	}
    	
    }
    
	@Operation(summary = "Busqueda por ID.", description = "Busca un producto filtrando por ID.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),	
					@ApiResponse(responseCode = "400", description = "Request erroneo."),
					@ApiResponse(responseCode = "404", description = "Entidad no encontrada."),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")}
					)
	@Parameters({@Parameter(name = "id", description = "ID del Producto"),})
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<ProductoResponseDTO>> obtenerPorId(@PathVariable Long id) {
    	log.info("INGRESO A ProductoController.obtenerPorId({}).", id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseDTO<>(true, 
										   MensajeResponseUtil.SUCCESS, 
										   CodeResponseUtil.SUCCESS,
										   LocalDateTime.now(), 
										   this.productoService.getById(id)));
    }

	@Operation(summary = "Creacion.", description = "Creacion de un producto mediante un objeto request.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),	
					@ApiResponse(responseCode = "400", description = "Request erroneo."),
					@ApiResponse(responseCode = "404", description = "Entidad no encontrada."),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")}
					)
    @PostMapping
    public ResponseEntity<ApiResponseDTO<ProductoResponseDTO>> crear(@Valid @RequestBody ProductoRequestDTO producto) {
    	log.info("INGRESO A ProductoController.crear().");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseDTO<>(true, 
										   MensajeResponseUtil.SUCCESS, 
										   CodeResponseUtil.SUCCESS,
										   LocalDateTime.now(), 
										   this.productoService.guardar(producto)));
    }

	@Operation(summary = "Actualizacion.", description = "Actualizacion de un producto mediante un objeto request.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),	
					@ApiResponse(responseCode = "400", description = "Request erroneo."),
					@ApiResponse(responseCode = "404", description = "Entidad no encontrada."),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")}
					)
	@Parameters({@Parameter(name = "id", description = "ID del Producto"),})
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<ProductoResponseDTO>> actualizar(@Valid @PathVariable Long id, @RequestBody ProductoRequestDTO producto) {
    	log.info("INGRESO A ProductoController.actualizar().");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseDTO<>(true, 
										   MensajeResponseUtil.SUCCESS, 
										   CodeResponseUtil.SUCCESS,
										   LocalDateTime.now(), 
										   this.productoService.actualizar(id, producto)));
    }
    
	@Operation(summary = "Eliminacion por ID.", description = "Elimina un producto filtrando por ID.")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Operacion exitosa."),	
					@ApiResponse(responseCode = "400", description = "Request erroneo."),
					@ApiResponse(responseCode = "404", description = "Entidad no encontrada."),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")}
					)
	@Parameters({@Parameter(name = "id", description = "ID del Producto"),})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable @Positive Long id) {
    	log.info("INGRESO A ProductoController.deleteById(). ID: " +id);
    	this.productoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@Operation(summary = "Eliminacion total.", description = "Elimina todos los productos.")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")}
					)
    @DeleteMapping
    public ResponseEntity<Void> eliminacionTotal() {
    	log.info("INGRESO A ProductoController.eliminacionTotal()");
    	this.productoService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
