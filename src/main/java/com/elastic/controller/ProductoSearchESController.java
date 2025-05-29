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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elastic.dto.ApiResponseDTO;
import com.elastic.dto.PageInfoDTO;
import com.elastic.dto.PagedDTO;
import com.elastic.dto.PagedResponseDTO;
import com.elastic.dto.ProductoResponseDTO;
import com.elastic.service.productosearches.IProductoSearchESService;
import com.elastic.util.CodeResponseUtil;
import com.elastic.util.MensajeResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@RestController
@RequestMapping("/api/search-es")
@Tag(name = "Busqueda ElasticSearch", description = "Endpoints para gestionar las busquedas de productos con ElasticSearch.")
@Validated
public class ProductoSearchESController {
	
	@Autowired
	private IProductoSearchESService productoSearchESService;
	
	@Operation(summary = "Busqueda textual.", description = "Busquedas por texto completo del producto.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@GetMapping("/texto")
	public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> 
								busquedaTextoCompleto(@RequestParam @Parameter(name = "texto", description = "Texto del Producto.") String texto){
		log.info("INGRESA A ProductoSearchESController.busquedaTextoCompleto(). Texto {} .", texto);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchESService.busquedaTextoCompleto(texto)));
	}
	
	@Operation(summary = "Busqueda nombre.", description = "Busquedas por nombre del producto.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@Parameters({@Parameter(name = "nombre", description = "Nombre del Producto."),})
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> buscarPorNombre(@PathVariable @NotEmpty String nombre) {
		log.info("INGRESA A ProductoSearchESController.buscarPorNombre(). Nombre {} .", nombre);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchESService.buscarPorNombreES(nombre)));
	}
	
	@Operation(summary = "Busqueda descripcion.", description = "Busquedas por descripcion del producto.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@Parameters({@Parameter(name = "descripcion", description = "Descripcion del Producto."),})
	@GetMapping("/descripcion/{descripcion}")
	public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> buscarPorDescripcionES(@PathVariable @NotEmpty String descripcion) {
		log.info("INGRESA A ProductoSearchESController.buscarPorDescripcionES(). Descripcion {} .", descripcion);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchESService.buscarPorDescripcionES(descripcion)));
	}

	@Operation(summary = "Busqueda Avanzada.", description = "Busquedas por nombre, precio minimo y precio maximo del producto.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@GetMapping("/avanzada")
	public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> busquedaAvanzada(@RequestParam  @Parameter(name = "nombre", description = "Nombre del Producto.") String nombre, 
																					  @RequestParam  @Parameter(name = "precioMin", description = "Precio minimo del Producto.") Double precioMin, 
																					  @RequestParam  @Parameter(name = "precioMax", description = "Precio maximo del Producto.") Double precioMax) {
		log.info("INGRESA A ProductoSearchESController.busquedaAvanzada(). Nombre {} - PrecioMin {} - PrecioMax {} ", nombre, precioMin, precioMax);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchESService.busquedaAvanzada(nombre, precioMin, precioMax)));
	}
	
	@Operation(summary = "Busqueda Fuzzy.", description = "Busquedas fuzzy (tolerante a errores).")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@GetMapping("/fuzzy")
	public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> busquedaFuzzy(@RequestParam @Parameter(name = "nombre", description = "Nombre del Producto.") String nombre) {
		log.info("INGRESA A ProductoSearchESController.busquedaFuzzy(). Nombre {} .", nombre);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchESService.busquedaFuzzy(nombre)));
	}
	
	@Operation(summary = "Busqueda multicampo.", description = "Busquedas multicampo con boost.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@GetMapping("/multicampo")
	public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> 
											busquedaMulticampo(@RequestParam @Parameter(name = "texto", description = "Texto del Producto.") String texto) {
		log.info("INGRESA A ProductoSearchESController.busquedaMulticampo(). Texto {} .", texto);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchESService.busquedaMulticampo(texto)));
	}
	
	@Operation(summary = "Paginacion.", description = "Busqueda paginada de productos.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
    @GetMapping("/paginado")
    public ResponseEntity<PagedResponseDTO<List<ProductoResponseDTO>>> buscarConPaginacion(
            @RequestParam @Parameter(name = "nombre", description = "Nombre del Producto.") String nombre,
            @RequestParam(defaultValue = "0") @Parameter(name = "page", description = "Numero de pagina.") int page,
            @RequestParam(defaultValue = "10") @Parameter(name = "size", description = "Cantidad por pagina.") int size) {
    	
    	log.info("INGRESA A ProductoSearchESController.buscarConPaginacion(). Nombre {} - Page {} - Size {} . ", nombre, page, size);
        PagedDTO<List<ProductoResponseDTO>> pagina = this.productoSearchESService.buscarConPaginacion(nombre, page, size);
        PageInfoDTO pageInfoDTO = PageInfoDTO.builder()
        									 .page(pagina.getPage())
        									 .size(pagina.getSize())
        									 .totalElements(pagina.getTotalElements())
        									 .totalPages(pagina.getTotalPages())
        									 .first(pagina.isFirst())
									 		 .last(pagina.isLast())
									 		 .empty(pagina.isEmpty())
									 		 .build();
        									 
        									 
        PagedResponseDTO<List<ProductoResponseDTO>> response = 
        						PagedResponseDTO.<List<ProductoResponseDTO>>builder().success(true)
        																			 .message(MensajeResponseUtil.SUCCESS)
        																			 .code(CodeResponseUtil.SUCCESS)
        																			 .timestamp(LocalDateTime.now())
        																			 .data(pagina.getContent())
        																			 .pageInfo(pageInfoDTO)
        																			 .build();
       
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    
	@Operation(summary = "Sincronizacion.", description = "Sincroniza con los datos de la base.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@PostMapping("/sincronizar")
	public ResponseEntity<Void> sincronizarElasticsearch() {
		log.info("INGRESA A ProductoSearchESController.sincronizarElasticsearch().");
		this.productoSearchESService.sincronizarConElasticsearch();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}
	
	@Operation(summary = "Limpia.", description = "Limpia el indice de la entidad.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@DeleteMapping("/limpiar")
	public ResponseEntity<Void> limpiarIndiceElasticsearch() {
		log.info("INGRESA A ProductoSearchESController.limpiarIndiceElasticsearch().");
		this.productoSearchESService.limpiarIndiceElasticsearch();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
