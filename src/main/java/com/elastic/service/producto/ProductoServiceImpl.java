package com.elastic.service.producto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.elastic.dto.ProductoResponseDTO;
import com.elastic.entity.ProductoEntity;
import com.elastic.exception.EntityNotFoundException;
import com.elastic.repository.jpa.IProductoRepository;
import com.elastic.util.ProductoMapper;
import com.elastic.dto.ProductoRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoServiceImpl implements IProductoService {

	private final IProductoRepository productoRepository;

	public ProductoServiceImpl(IProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}
	
	@Override
	public List<ProductoResponseDTO> getAll() {
		log.info("INGRESA A  ProductoServiceImpl.findAll()");
		return ProductoMapper.listEntityToListDTO(productoRepository.findAll());
	}

	@Override
	public ProductoResponseDTO getById(Long id) {
		log.info("INGRESO A ProductoServiceImpl.findById(). ID {}  ", id);
		Optional<ProductoEntity> optionalProducto = this.productoRepository.findById(id);
		if (optionalProducto.isPresent()) {
			return ProductoMapper.entityToDTO(optionalProducto.get());
		} else {
			log.error("ERROR EN ProductoDaoImpl.findById(). Producto no encontrado con id {}  ", id);
			throw new EntityNotFoundException("Producto no encontrado con id: " + id);
		}
	}

	@Override
	public ProductoResponseDTO guardar(ProductoRequestDTO p) {
		log.info("INGRESO A ProductoServiceImpl.actualizar().");
		ProductoEntity newProducto =  ProductoEntity.builder().nombre(p.getNombre())
															  .descripcion(p.getDescripcion())
															  .codigo(p.getCodigo())
															  .precio(p.getPrecio())
															  .stock(p.getStock())
															  .activo(p.getActivo())
															  .fechaRegistro(LocalDate.now())
															  .build();
			
		ProductoEntity newProductoEntity = this.productoRepository.save(newProducto);		
		return ProductoMapper.entityToDTO(newProductoEntity);
	}

	@Override
	public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO p) {
		log.info("INGRESO A ProductoServiceImpl.actualizar().");
		Optional<ProductoEntity> optionalProducto = this.productoRepository.findById(id);
		if (optionalProducto.isPresent()) {
			ProductoEntity updatedProducto = new ProductoEntity(id,
																p.getNombre(),
																p.getDescripcion(),
																p.getCodigo(),	
																p.getPrecio(), 		
																p.getStock(),
																p.getActivo(),
																LocalDate.now());			
			ProductoEntity savedProductoEntity= this.productoRepository.save(updatedProducto);
			return ProductoMapper.entityToDTO(savedProductoEntity);
		} else {
			log.error("ERROR EN ProductoDaoImpl.update(). Producto no encontrado con id {} ", id);
			throw new EntityNotFoundException("Producto no encontrado con id : " + id);
		}
	}

	@Override
	public void deleteById(Long id) {
		log.info("INGRESO A ProductoServiceImpl.deleteById().");
		Optional<ProductoEntity> optionalProducto = this.productoRepository.findById(id);
		if (optionalProducto.isPresent()) {
			this.productoRepository.deleteById(id);
		} else {
			log.error("ERROR EN ProductoServiceImpl.deleteById(). Producto no encontrado con id: " + id);
			throw new EntityNotFoundException("Producto no encontrado con id: " + id);
		}	
	}

	@Override
	public void deleteAll() {
		log.info("INGRESO A ProductoServiceImpl.deleteAll().");
		this.productoRepository.deleteAll();		
	}

}
