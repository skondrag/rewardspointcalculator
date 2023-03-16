package com.retailer.rewardpointscalculator.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class NativeTableInsertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery (String nativeQuery) {
        entityManager.createNativeQuery(nativeQuery)
                .executeUpdate();
    }
}
