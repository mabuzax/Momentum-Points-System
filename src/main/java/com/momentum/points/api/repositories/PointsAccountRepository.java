package com.momentum.points.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.momentum.points.api.models.PointsAccount;

@RepositoryRestResource(collectionResourceRel = "points", path = "points")
public interface PointsAccountRepository extends MongoRepository<PointsAccount, String> {
	
	PointsAccount findByAccountId(@Param("accountId") String accountId);
	PointsAccount findByCustomerId(@Param("custId") String custId);
}
