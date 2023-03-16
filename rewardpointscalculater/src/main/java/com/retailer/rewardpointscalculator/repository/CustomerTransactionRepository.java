package com.retailer.rewardpointscalculator.repository;

import com.retailer.rewardpointscalculator.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public interface CustomerTransactionRepository extends CrudRepository<CustomerTransaction,Long> {

    public List<CustomerTransaction> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp from, Timestamp to);
}
