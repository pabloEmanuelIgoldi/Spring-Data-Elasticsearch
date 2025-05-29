package com.elastic.service.productosearches;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elastic.dto.PagedDTO;
import com.elastic.dto.ProductoResponseDTO;
import com.elastic.entity.ProductoEntity;
import com.elastic.repository.elasticsearch.IProductoElasticsearchRepository;
import com.elastic.repository.jpa.IProductoRepository;
import com.elastic.util.ProductoMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoSearchESServiceImpl implements IProductoSearchESService {
	
	@Autowired
	private IProductoElasticsearchRepository productoElasticsearchRepository;
	
	@Autowired
	private IProductoRepository productoRepository;

	@Override
	public List<ProductoResponseDTO> busquedaTextoCompleto(String texto) {
		log.info("INGRESO A ProductoSearchPGServiceImpl.busquedaTextoCompleto(). Texto: " + texto);
		return ProductoMapper.listEntityToListDTO(this.productoElasticsearchRepository.findByNombreOrDescripcion(texto));
	}

	@Override
	public List<ProductoResponseDTO> buscarPorNombreES(String nombre) {
		log.info("INGRESO A ProductoSearchPGServiceImpl.buscarPorNombreES(). Nombre {}", nombre);
		return ProductoMapper.listEntityToListDTO(this.productoElasticsearchRepository.findByNombreContaining(nombre));
	}

	@Override
	public List<ProductoResponseDTO> buscarPorDescripcionES(String descripcion) {
		log.info("INGRESO A ProductoSearchPGServiceImpl.buscarPorDescripcionES(). Descripcion {} " , descripcion);
		return ProductoMapper.listEntityToListDTO(this.productoElasticsearchRepository.findByDescripcionContaining(descripcion));
	}

	
	@Override
	public PagedDTO<List<ProductoResponseDTO>> buscarConPaginacion(String nombre, int page, int size) {
		log.info("INGRESO A ProductoSearchPGServiceImpl.buscarConPaginacion(). Nombre {} " + nombre);
		Pageable pageable = PageRequest.of(page, size);
		Page<ProductoEntity> entityPage = this.productoElasticsearchRepository.findByNombreContaining(nombre, pageable);
		return ProductoMapper.buildPagedDTO(entityPage);
	}

	@Override
	public List<ProductoResponseDTO> busquedaAvanzada(String nombre, Double precioMin, Double precioMax) {
		log.info("INGRESO A ProductoSearchPGServiceImpl.busquedaTextoCompleto(). Nombre {} - PrecioMin {} - PrecioMax {}", nombre, precioMin, precioMax);
		return ProductoMapper.listEntityToListDTO(this.productoElasticsearchRepository.findByNombreAndPrecioRange(nombre, precioMin, precioMax));
	}

	@Override
	public List<ProductoResponseDTO> busquedaFuzzy(String nombre) {
		log.info("INGRESO A ProductoSearchPGServiceImpl.busquedaFuzzy(). Nombre {}", nombre);
		return ProductoMapper.listEntityToListDTO(this.productoElasticsearchRepository.findByNombreFuzzy(nombre));
	}

	@Override
	public List<ProductoResponseDTO> busquedaMulticampo(String texto) {
		log.info("INGRESO A ProductoSearchPGServiceImpl.busquedaTextoCompleto(). Texto {} ", texto);
		return ProductoMapper.listEntityToListDTO(this.productoElasticsearchRepository.busquedaMulticampo(texto));
	}

	@Override
	public void sincronizarConElasticsearch() {
		log.info("INGRESO A ProductoSearchPGServiceImpl.sincronizarConElasticsearch()");
		this.productoElasticsearchRepository.saveAll(this.productoRepository.findAll());		
	}

	@Override
	public void limpiarIndiceElasticsearch() {
		log.info("INGRESO A ProductoSearchPGServiceImpl.limpiarIndiceElasticsearch()");
		this.productoElasticsearchRepository.deleteAll();		
	}

}
