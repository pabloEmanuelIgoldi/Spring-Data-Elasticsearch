#  **Spring Data + ElasticSearch**

# ÍNDICE
### Definición del proyecto
### Arquitectura del proyecto
### Tecnologías y dependencias
### Elasticsearch
### Integracion con Spring Boot
### Ejecución del proyecto
### Documentacion oficial

---

# Definición del proyecto

Este proyecto demuestra cómo integrar Spring Boot con ElasticSearch como motor de busqueda dentro de nuestras aplicaciones.


### Objetivo

El objetivo de integrar Spring Boot con Elasticsearch es aprovechar las capacidades de búsqueda y análisis en tiempo real de Elasticsearch dentro de una aplicación Spring Boot, permitiendo indexar, buscar y analizar grandes volúmenes de datos de manera eficiente.

---

# Arquitectura del proyecto

El proyecto está dividido en las siguientes capas:

### Controladores (Controller)
Manejan las peticiones HTTP. Realiza validaciones. Gestionas los errores globalmente. Solo opera con objetos DTO.

### Servicios (Service)
Actúan como intermediarios entre los controladores y los repositorios. Mapeando las entidades (Entities) a DTOs y viceversa, sin acoplarse a la implementación de los repositorios.

### Repositorios (Repository)
Capa encargada del acceso y gestión de los datos.
Esta capa se dividió en tres repositorios con el fin de definir interfaces específicas para cada tipo de operación:
* **JpaRepository:** Encargado de realizar operaciones CRUD sobre la base de datos relacional.
* **Repository:** Define operaciones de búsqueda personalizadas sobre la base de datos.
* **ElasticsearchRepository:** Permite ejecutar operaciones de búsqueda utilizando Elasticsearch.

---


# Tecnologías y Dependencias

La aplicación está construida con:

- **Java 17**
- **Spring Boot 3.3.11**
- **PostgreSQL**
- **Elasticsearch**

### Dependencias principales:

| Dependencia                             | Descripción                                     |
|-----------------------------------------|-------------------------------------------------|
| `spring-boot-starter-web`               | Construcción de aplicaciones web                |
| `spring-boot-starter-validation`        | Validación de datos                             |
| `spring-boot-starter-data-elasticsearch`| Integración con Elasticsearch                   |
| `spring-boot-starter-data-jpa`          | Acceso a bases de datos relacionales (JPA)      |
| `lombok`                                | Reducción de código repetitivo con anotaciones  |
| `springdoc-openapi-starter-webmvc-ui`   | Documentación y pruebas de API (Swagger)        |
| `postgresql`                            | Driver del motor de base de datos utilizado     |

---


# Elasticsearch
  Es un motor de búsqueda que se encarga de almacenar los datos y permitir búsquedas complejas y rápidas.  
	Es una base de datos NoSql. Se usa como auxiliar a la base de datos principal. 
	Se base en documentos(similares a un JSON), en base a estos documentos crea indices.

## Persistencia de Datos
  Es una base de datos persistente en disco, diseñada para almacenar grandes cantidades de datos.
  Se enfoca en la persistencia de los datos y su indexación para permitir consultas rápidas.	


## Casos de uso

- **Búsqueda frecuente:**
  Si una entidad se consulta con mucha más frecuencia de la que se modifica, es un buen candidato para ser indexado.

- **Datos estructurados o semiestructurados:**
  Elasticsearch es ideal para trabajar con texto, realizar búsquedas *full-text*, aplicar filtros complejos o ejecutar agregaciones.
  *Ejemplo: documentos.*

- **Datos que requieren análisis avanzado:**
  Cuando se necesitan filtros por rangos, agregaciones estadísticas o búsquedas difusas.

- **Volumen de datos:**
  Manejo de grandes volúmenes de registros, datos históricos extensos o información en constante crecimiento.

- **Replicación y alta disponibilidad:**
  En casos donde se requieren réplicas distribuidas para lograr búsquedas rápidas y garantizar resiliencia.
  Elasticsearch utiliza un modelo de consistencia eventual.


**Consideraciones técnicas importantes**

* Los índices aumentan el consumo de espacio en disco.
* Cada índice requiere recursos adicionales para su mantenimiento.
* Las actualizaciones frecuentes pueden afectar el rendimiento.



**¿Cuándo NO usar un índice en Elasticsearch?**

* Evitar índices innecesarios en campos que se consultan poco.
* En datos transaccionales (por ejemplo, saldos de cuentas bancarias).
* Para estructuras altamente normalizadas (es preferible una base de datos relacional).
* Cuando los datos solo se acceden mediante clave primaria.

---

# Integración con Spring Boot

Spring Data Elasticsearch es un módulo de Spring Data que proporciona una forma simplificada de interactuar con Elasticsearch mediante una abstracción basada en repositorios, con soporte para consultas.

**Pasos a tener en cuenta:**

* Agregar la dependencia correspondiente y configurar adecuadamente la conexión con Elasticsearch.
* Crear los índices específicos para las entidades seleccionadas dentro de la capa de datos (considerar el uso de anotaciones como `@Document`, `@Field`, etc.).
* Implementar algún mecanismo de búsqueda para realizar las consultas.
  En esta aplicación se utilizaron `ElasticsearchRepository` y anotaciones como `@Query`.

**Nota:**

Existen otras formas de implementar búsquedas en Elasticsearch, entre ellas:

* `ElasticsearchOperations`
* `RestHighLevelClient` *(cliente legacy)*
* `ElasticsearchClient` *(nuevo cliente recomendado)*
* `QueryBuilders`
* `Criteria API`

## Anotaciones

Las anotaciones principales en Spring Data Elasticsearch son:

### Anotaciones de Entidades (Documentos)

- `@Document`: Define una clase como un documento de Elasticsearch (índice, tipo, configuración).
- `@Id`: Marca el campo como identificador único del documento.
- `@Field`: Personaliza el mapeo del campo (tipo, analizador, formato).
- `@Mapping`: Define el mapeo de campos del documento (similar al esquema de Elasticsearch).
- `@Setting`: Configuración del índice (shards, réplicas, analizadores).

### Anotaciones de Operaciones

- `@EnableElasticsearchRepositories`: Habilita la búsqueda de repositorios de Elasticsearch.

### Anotaciones de Repositorios

- `@Repository`: (Spring) Identifica interfaces como repositorios de Spring Data.
- `@Query`: Permite definir consultas personalizadas en Elasticsearch (JSON o DSL).


---

# Ejecución del proyecto

### Requisitos:
- Java 17
- Docker (PostgreSQL, ElasticSearch y Kibana)
- IDE o herramienta de compilación (Eclipse, Maven)

### Servicio EslaticSearch
Podes levantar el servidor de EslasticSearch (con Kibana) con el siguiente `docker-compose.yml` ejecutandolo en docker:
```
version: '3.8'
services:
  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.11.0
    container_name: elasticsearch
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false
      - xpack.security.enrollment.enabled=false
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
    ports:
      - "9200:9200"
      - "9300:9300"
    volumes:
      - elasticsearch_data:/usr/share/elasticsearch/data
    networks:
      - elastic

  kibana:
    image: docker.elastic.co/kibana/kibana:8.11.0
    container_name: kibana
    environment:
      - ELASTICSEARCH_HOSTS=http://elasticsearch:9200
    ports:
      - "5601:5601"
    depends_on:
      - elasticsearch
    networks:
      - elastic

volumes:
  elasticsearch_data:
    driver: local

networks:
  elastic:
    driver: bridge
```

### Pasos:
- Clona el repositorio.
- Configura las propiedades en application.properties.
- Levanta ElasticSearch (puedes usar Docker) y PostgreSQL.
- Levanta la aplicación.

### Pruebas

Para probar la aplicación se puede usar Swagger:

**Swagger:**

	http://localhost:8080/api-elastic/swagger-ui/index.html
 
	http://localhost:8080/api-elastic-docs


### Opciones para ver los indices
**1 - Kibana**

	Una vez levantado el servidor de kibana:
 
	Kibana: http://localhost:5601
 
	Opciones:
 
	- Ve a Stack Management --> Index Management (Aquí verás todos los índices)
 
	- Ve a Discover (La primera vez necesitarás crear un Data View "productos"). 
 
		Luego de crearlo, podras ingresar a: Discover--> Selecciona el data view "productos".


**2 - También podes verificar directamente desde terminal:**

* Ver todos los índices
  
    curl http://localhost:9200/_cat/indices?v

* Ver documentos del índice productos

    curl http://localhost:9200/productos/_search?pretty

* Ver mapping del índice

    curl http://localhost:9200/productos/_mapping?pretty

* Ver configuración del índice

    curl http://localhost:9200/productos/_settings?pretty

---

# Documentación oficial

https://spring.io/projects/spring-data-elasticsearch

https://www.elastic.co/es/

---
#  **Índice de proyectos Spring**
##  **Proyectos Spring Boot**
- [Response Uniforme](https://github.com/pabloEmanuelIgoldi/Spring-Boot-Response-Wrapper)
- [LogBack](https://github.com/pabloEmanuelIgoldi/Spring-Boot-Logback)
- [Profile](https://github.com/pabloEmanuelIgoldi/Spring-Boot-Profile)
- [Spring Doc](https://github.com/pabloEmanuelIgoldi/Spring-Boot-Swagger)
- [Validate](https://github.com/pabloEmanuelIgoldi/Spring-Boot-Validate)
- [Inter-Service Communication](https://github.com/pabloEmanuelIgoldi/Spring-Boot-Inter-Service-Communication)
##  **Proyectos Spring Data**
- [Spring Data + Redis(Cache)](https://github.com/pabloEmanuelIgoldi/Spring-Data-Redis)
- [Spring Data + Mongo DB (NoSQL)](https://github.com/pabloEmanuelIgoldi/Spring-Data-Mongo)
- [Spring Data + ElasticSearch](https://github.com/pabloEmanuelIgoldi/Spring-Data-Elasticsearch)
