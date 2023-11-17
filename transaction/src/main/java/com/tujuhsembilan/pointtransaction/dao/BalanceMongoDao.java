package com.tujuhsembilan.pointtransaction.dao;

import com.tujuhsembilan.pointtransaction.model.BalanceMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceMongoDao extends MongoRepository<BalanceMongo, String> {
    Optional<BalanceMongo> findByAccountId(Long accountId);
}
