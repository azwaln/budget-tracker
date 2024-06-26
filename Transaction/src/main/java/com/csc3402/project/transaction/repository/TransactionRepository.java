package com.csc3402.project.transaction.repository;

import com.csc3402.project.transaction.model.Budget;
import com.csc3402.project.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByBudget(Budget budget);
}
