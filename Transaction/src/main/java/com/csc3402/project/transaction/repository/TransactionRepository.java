package com.csc3402.project.transaction.repository;

import com.csc3402.project.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT * FROM transaction WHERE trans_id = :id", nativeQuery = true)
    Transaction findTransactionById(@Param("id") int id);

}
