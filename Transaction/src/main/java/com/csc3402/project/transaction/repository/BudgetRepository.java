package com.csc3402.project.transaction.repository;

import com.csc3402.project.transaction.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    Budget findByBudgetID(Integer id);

}

