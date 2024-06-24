package com.csc3402.project.transaction.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budgetID")
    private Integer budgetID;

    @Column(name = "budgetName")
    private String budgetName;

    @Column(name = "period")
    private String period;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "budgetAmount")
    private Integer budgetAmount;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private Set<Transaction> transactions;


    // Constructors
    public Budget() {
    }

    public Budget(String budgetName, String period, LocalDate startDate, LocalDate endDate, Integer budgetAmount) {
        this.budgetName = budgetName;
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetAmount = budgetAmount;
    }

    // Getters and Setters

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

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

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budgetID=" + budgetID +
                ", budgetName='" + budgetName + '\'' +
                ", period='" + period + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", budgetAmount=" + budgetAmount +
                '}';
    }
}
