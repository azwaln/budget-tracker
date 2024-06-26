package com.csc3402.project.transaction.service;

import com.csc3402.project.transaction.model.Budget;
import com.csc3402.project.transaction.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public List<Budget> listAllBudgets() {
        return budgetRepository.findAll();
    }

    @Override
    public Budget addNewBudget(Budget budget) {
        return budgetRepository.save(budget);
    }


    public Optional<Budget> findBudgetById(Integer budgetId) {
        return budgetRepository.findById(budgetId);
    }

    @Override
    public void updateBudget(Budget budget) {
        budgetRepository.save(budget);
    }

    @Override
    public void deleteBudget(Budget budget) {
        budgetRepository.delete(budget);
    }
}