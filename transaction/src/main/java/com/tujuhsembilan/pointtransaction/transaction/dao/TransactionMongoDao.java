package com.tujuhsembilan.pointtransaction.transaction.dao;

import com.tujuhsembilan.pointtransaction.transaction.model.mongo.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMongoDao extends MongoRepository<Transaction, String> {
}

