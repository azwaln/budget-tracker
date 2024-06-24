package com.csc3402.project.transaction.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionID")
    private Integer transactionID;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "budgetID")
    private Budget budget;

    public Transaction() {
    }

    public Transaction(LocalDate date, Double amount, Category category, Budget budget) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.budget = budget;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", date=" + date +
                ", amount=" + amount +
                ", category=" + category +
                ", budgetID=" + budget +
                '}';
    }
}
