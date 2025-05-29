package com.elastic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
@Document(indexName = "productos")
public class ProductoEntity {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @Field(type = FieldType.Text, analyzer = "spanish")
    private String nombre;
    
    @Column(length = 1000)
    @Field(type = FieldType.Text, analyzer = "spanish")
    private String descripcion;
    
    @Column(unique = true, nullable = false)
    @Field(type = FieldType.Keyword)
    private String codigo;
    
    @Column(nullable = false)
    @Field(type = FieldType.Double)
    private Double precio;
    
    @Column(nullable = false)
    @Field(type = FieldType.Integer)
    private Integer stock;
    
    @Column(nullable = false)
    @Field(type = FieldType.Boolean)
    private Boolean activo;
    
    @Column(name = "fecha_registro", nullable = false)
    @Field(type = FieldType.Date)
    private LocalDate fechaRegistro;
}
