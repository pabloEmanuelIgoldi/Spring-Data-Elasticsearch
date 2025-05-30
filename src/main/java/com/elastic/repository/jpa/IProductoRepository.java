package com.elastic.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.elastic.entity.ProductoEntity;

public interface IProductoRepository extends JpaRepository<ProductoEntity, Long> {

}
