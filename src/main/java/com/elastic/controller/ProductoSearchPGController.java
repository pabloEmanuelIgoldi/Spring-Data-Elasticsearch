package com.elastic.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.elastic.service.productosearchpg.IProductoSearchPGService;
import com.elastic.util.CodeResponseUtil;
import com.elastic.util.MensajeResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/search-pg")
@Tag(name = "Busqueda PostgreSql", description = "Endpoints para gestionar las busquedas de productos con PostgreSql.")
@Validated
public class ProductoSearchPGController {
	
	@Autowired
	private IProductoSearchPGService productoSearchPGService;
	
	
	@Operation(summary = "Busqueda codigo.", description = "Busquedas por codigo del producto.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@Parameters({@Parameter(name = "codigo", description = "Codigo del Producto."),})	
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<ApiResponseDTO<ProductoResponseDTO>> buscarPorCodigo(@PathVariable @NotEmpty String codigo) {
		log.info("INGRESA A ProductoSearchPGController.buscarPorCodigo(). codigo {}.");
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchPGService.buscarPorCodigo(codigo)));
	}
	
	
	@Operation(summary = "Paginacion.", description = "Busqueda paginada de productos.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
    @GetMapping("/paginado")
    public ResponseEntity<PagedResponseDTO<List<ProductoResponseDTO>>> 
												buscarConPaginacion(@RequestParam(defaultValue = "0") @Parameter(name = "page", description = "Numero de pagina.") int page,
            													    @RequestParam(defaultValue = "10") @Parameter(name = "size", description = "Cantidad por pagina.") int size,
            														@RequestParam(defaultValue = "id") @Parameter(name = "sortBy", description = "Campo del Producto que ordena la busqueda.") String sortBy,
            														@RequestParam(defaultValue = "asc")  @Parameter(name = "sortDir", description = "Ordena ascendente o descendente de la busqueda.") String sortDir) {    	
    	log.info("INGRESA A ProductoSearchPGController.buscarConPaginacion(). Size {} - Page {} - sortBy {} - sortDir {} .", size, page, sortBy, sortDir);
        PagedDTO<List<ProductoResponseDTO>> pagina = this.productoSearchPGService.paginar(page, size, sortBy, sortDir);
        PageInfoDTO pageInfoDTO = PageInfoDTO.builder().page(pagina.getPage())
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
	
	@Operation(summary = "Busqueda de activos.", description = "Busquedas de productos activos.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),				
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@GetMapping("/activos")
	public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> listarActivos() {
		log.info("INGRESA A ProductoSearchPGController.listarActivos()");
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchPGService.listarActivos()));
	}
	
	@Operation(summary = "Busqueda por precios.", description = "Busqueda por rango de precios de productos.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@GetMapping("/precio")
	public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> buscarPorRangoPrecio(@RequestParam @Parameter(name = "min", description = "Precio minimo.") Double min, 
																						  @RequestParam @Parameter(name = "max", description = "Precio maximo.") Double max) {
		log.info("INGRESA A ProductoSearchPGController.buscarConStockMayorA(). min: {} - max: {} .", min, max);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchPGService.buscarPorRangoPrecio(min, max)));
	}
	
	@Operation(summary = "Busqueda stock mayor.", description = "Busquedas de productos con stock mayor a un valor.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@Parameters({@Parameter(name = "minStock", description = "Stock minimo de los productos que se tiene que buscar."),})
	@GetMapping("/stock/{minStock}")
	public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> buscarConStockMayorA(@PathVariable @Positive Integer minStock) {
		log.info("INGRESA A ProductoSearchPGController.buscarConStockMayorA(). minStock:  {}.", minStock);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchPGService.buscarConStockMayorA(minStock)));
	}
	
	@Operation(summary = "Busqueda stock bajo.", description = "Busquedas de productos con stock menor a un valor.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@Parameters({@Parameter(name = "stockMinimo", description = "Stock maximo de los productos que se tiene que buscar."),})
	@GetMapping("/bajo-stock/{stockMinimo}")
	public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> buscarConBajoStock(@PathVariable @Positive Integer stockMinimo) {
		log.info("INGRESA A ProductoSearchPGController.buscarConBajoStock(). StockMinimo: {}.", stockMinimo);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchPGService.buscarConBajoStock(stockMinimo)));
	}
	
	
	@Operation(summary = "Busqueda nombre.", description = "Busquedas por nombre del producto.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
					@ApiResponse(responseCode = "400", description = "Request erroneo."),					
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")})
	@Parameters({@Parameter(name = "nombre", description = "Nombre del Producto."),})
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<ApiResponseDTO<List<ProductoResponseDTO>>> buscarPorNombre(@PathVariable @NotBlank String nombre) {
		log.info("INGRESA A ProductoSearchPGController.buscarPorNombre(). Nombre: {}.", nombre);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(new ApiResponseDTO<>(true, 
									 					MensajeResponseUtil.SUCCESS,
									 					CodeResponseUtil.SUCCESS, 
									 					LocalDateTime.now(),
									 					this.productoSearchPGService.buscarPorNombreContiene(nombre)));
	}

}
