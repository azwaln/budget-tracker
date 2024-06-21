package com.csc3402.project.transaction.service;

import com.csc3402.project.transaction.repository.TransactionRepository;
import com.csc3402.project.transaction.model.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> listAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction addNewTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }


    public Optional<Transaction> findTransactionById(Integer transactionId) {
        return transactionRepository.findById(transactionId);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

}
