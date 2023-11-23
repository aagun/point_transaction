package com.tujuhsembilan.pointtransaction.customer.dao;

import com.tujuhsembilan.pointtransaction.clients.model.pg.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
    Optional<Customer> findByAccountId(Long accountId);

    boolean existsByAccountId(Long accountId);

}
