package com.csc3402.project.transaction.service;

import com.csc3402.project.transaction.model.Budget;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BudgetService {
    List<Budget> listAllBudgets();
    Budget addNewBudget(Budget budget);
    Optional<Budget> findBudgetById(Integer budgetId);
    void updateBudget(Budget budget);
    void deleteBudget(Budget budget);
}

