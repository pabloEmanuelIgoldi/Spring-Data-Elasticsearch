package com.elastic.service.productosearchpg;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.elastic.dto.PagedDTO;
import com.elastic.dto.ProductoResponseDTO;
import com.elastic.entity.ProductoEntity;
import com.elastic.exception.EntityNotFoundException;
import com.elastic.repository.jpa.IProductoSearchPGRepository;
import com.elastic.util.ProductoMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoSearchPGServiceImpl implements IProductoSearchPGService {
	
	@Autowired
	private IProductoSearchPGRepository productoSearchPGRepository;
	
	@Override
	public ProductoResponseDTO buscarPorCodigo(String codigo) {
		log.info("INGRESO A ProductoSearchPGServiceImpl.buscarPorCodigo(). Codigo: " + codigo);
		Optional<ProductoEntity> optionalProducto = this.productoSearchPGRepository.findByCodigo(codigo);
		if (optionalProducto.isPresent()) {
			return ProductoMapper.entityToDTO(optionalProducto.get());
		} else {
			log.error("ERROR EN ProductoSearchPGServiceImpl.buscarPorCodigo(). Producto no encontrado con id: " + codigo);
			throw new EntityNotFoundException("Producto no encontrado con id: " + codigo);
		}
	}

	@Override
	public List<ProductoResponseDTO> listarActivos() {
		log.info("INGRESA A ProductoSearchPGServiceImpl.listarActivos()");
		return ProductoMapper.listEntityToListDTO(productoSearchPGRepository.findByActivoTrue());
	}
	
	@Override
	public PagedDTO<List<ProductoResponseDTO>> paginar(int page, int size, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductoEntity> entityPage = productoSearchPGRepository.findAll(null, pageable);
        return ProductoMapper.buildPagedDTO(entityPage);
	}

	@Override
	public List<ProductoResponseDTO> buscarPorRangoPrecio(Double precioMin, Double precioMax) {
		log.info("INGRESA A  ProductoSearchPGServiceImpl.buscarPorRangoPrecio()");
		return ProductoMapper.listEntityToListDTO(productoSearchPGRepository.findByPrecioBetween(precioMin, precioMax));
	}

	@Override
	public List<ProductoResponseDTO> buscarConStockMayorA(Integer stock) {
		log.info("INGRESA A ProductoSearchPGServiceImpl.buscarConStockMayorA()");
		return ProductoMapper.listEntityToListDTO(productoSearchPGRepository.findByStockGreaterThan(stock));
	}

	@Override
	public List<ProductoResponseDTO> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) {
		log.info("INGRESA A ProductoSearchPGServiceImpl.buscarPorRangoFecha()");
		return ProductoMapper.listEntityToListDTO(productoSearchPGRepository.findByFechaRegistroBetween(fechaInicio, fechaFin));
	}

	@Override
	public List<ProductoResponseDTO> buscarPorNombreContiene(String nombre) {
		log.info("INGRESA A ProductoSearchPGServiceImpl.buscarPorNombreContiene()");
		return ProductoMapper.listEntityToListDTO(productoSearchPGRepository.findByNombreContainingIgnoreCaseAndActivoTrue(nombre));
	}

	@Override
	public List<ProductoResponseDTO> buscarConBajoStock(Integer stockMinimo) {
		log.info("INGRESA A ProductoSearchPGServiceImpl.buscarConBajoStock()");
		return ProductoMapper.listEntityToListDTO(productoSearchPGRepository.findProductosConBajoStock(stockMinimo));
	}


}
