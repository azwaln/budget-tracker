package com.csc3402.project.transaction.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_ID")
    private Integer budgetID;

    @OneToMany(mappedBy = "budget",cascade = CascadeType.ALL)
    private Set<com.csc3402.project.budget3.model.Transaction> transactions;

    @Column(name = "period")
    private String period; // Changed to String

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "budget_amount")
    private Integer budgetAmount;
    //@OneToMany(mappedBy = "budget",cascade = CascadeType.ALL)
    //private List<Expense> expenses;

    // Constructor
    public Budget() {
    }

    public Budget(String period, LocalDate startDate, LocalDate endDate, Integer budgetAmount) {
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetAmount = budgetAmount;
    }

    // Getters and Setters
    public Integer getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(Integer budgetID) {
        this.budgetID = budgetID;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Integer budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budgetID=" + budgetID +
                ", period='" + period + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", budgetAmount=" + budgetAmount +
                '}';
    }
}
