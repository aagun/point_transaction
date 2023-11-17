package com.tujuhsembilan.pointtransaction.dao;

import com.tujuhsembilan.pointtransaction.model.TransactionMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMongoDao extends MongoRepository<TransactionMongo, String> {
}

