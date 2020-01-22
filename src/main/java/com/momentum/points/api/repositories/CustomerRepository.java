package com.momentum.points.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.momentum.points.api.models.Customer;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	Customer findByCustomerId(@Param("customerId") String customerId);
	List<Customer> findByCustomerName(@Param("name") String name);	
}
