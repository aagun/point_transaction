package com.tujuhsembilan.pointtransaction.point.dao;

import com.tujuhsembilan.pointtransaction.point.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointDao extends JpaRepository<Point, Long> {
    Optional<Point> findByCustomerAccountId(Long accountId);

}
