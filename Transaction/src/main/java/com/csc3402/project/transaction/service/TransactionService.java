package com.csc3402.project.transaction.service;

import com.csc3402.project.transaction.model.Budget;
import com.csc3402.project.transaction.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TransactionService {
    List<Transaction> listAllTransactions();

    Transaction addNewTransaction(Transaction transaction);

    List<Transaction> findTransactionsByBudget(Budget budget);

    Optional<Transaction> findTransactionById(Integer transaction);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(Transaction transaction);
}
