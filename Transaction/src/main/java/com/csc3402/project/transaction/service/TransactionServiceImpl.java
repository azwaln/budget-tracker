package com.csc3402.project.transaction.service;

import com.csc3402.project.transaction.model.Transaction;
import com.csc3402.project.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{
    //@Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    @Override
    public List<Transaction> listAllTransactions(){
        return transactionRepository.findAll();
    }

    @Override
    public Transaction addNewTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findTransactionById(Integer transID){
        return transactionRepository.findById(transID);
    }

    @Override
    public Transaction QueryTransactionById(Integer transID){
        return transactionRepository.findTransactionById(transID);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Override
    public void  deleteTransaction(Transaction transaction){
        transactionRepository.delete(transaction);
    }


}
