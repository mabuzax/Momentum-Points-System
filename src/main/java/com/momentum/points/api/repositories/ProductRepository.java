package com.momentum.points.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.momentum.points.api.models.Product;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends MongoRepository<Product, String> {
	
	Product findByProductCode(@Param("productCode") String productCode);
	List<Product> findByProductName(@Param("name") String name);
	
	
}