package com.csc3402.project.transaction.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trans_ID")
    private Integer transID;

    @ManyToOne
    @JoinColumn(name="budget_ID")
    private Budget budget;

    @Column(name="trans_date")
    private LocalDate date;

    @Column(name="amount")
    private Integer amount;

    @Column(name="note")
    private String note;

    public Integer getTransID() {
        return transID;
    }

    public void setTransID(Integer transID) {
        this.transID = transID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Transaction() {
    }

    public Transaction(Integer transID, LocalDate date, Integer amount,String note) {
        this.transID = transID;
        this.date = date;
        this.amount = amount;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transID=" + transID +
                ", date=" + date +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                '}';
    }
}
