package com.tujuhsembilan.pointtransaction.transaction.dao;

import com.tujuhsembilan.pointtransaction.transaction.model.mongo.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceMongoDao extends MongoRepository<Balance, String> {
    Optional<Balance> findByAccountId(Long accountId);
}
