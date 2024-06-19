package com.csc3402.project.transaction.controller;

import com.csc3402.project.transaction.model.Transaction;
import com.csc3402.project.transaction.service.BudgetService;
import com.csc3402.project.transaction.service.TransactionService;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trans")
public class TransactionController {

    private final TransactionService transactionService;
    private final BudgetService budgetService;

    @Autowired
    public TransactionController(TransactionService transactionService, BudgetService budgetService) {
        this.transactionService = transactionService;
        this.budgetService = budgetService;
    }

    @GetMapping("/list2")
    public String listTransactions(Model model){
        List<Transaction> transactions = transactionService.listAllTransactions();
        model.addAttribute("transactions", transactions);
        return "list-transaction";
    }

    @GetMapping("/signup")
    public String showAddNewTransactionForm(Transaction transaction, Model model) {
        model.addAttribute("transactions", transactionService.listAllTransactions());
        return "add-transaction";
    }

    @PostMapping("/add")
    public String addTransaction(@Valid Transaction transaction, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "add-transaction";
        }
        transactionService.addNewTransaction(transaction);
        return "redirect:/trans/list2";
    }

    @GetMapping("/update")
    public String showUpdateMainForm(Model model){
        model.addAttribute("transactions", transactionService.listAllTransactions());
        return "choose-trans-to-update";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        Transaction transaction = transactionService.findTransactionById((int)id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transaction id: " + id));
        model.addAttribute("transaction", transaction);
        model.addAttribute("budgets",budgetService.listAllBudget());
        return "update-transaction";
    }

    @PostMapping("/update/{id}")
    public String updateTransaction(@PathVariable("id") long id, @Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("transaction", transaction);
            return "update-transaction";
        }
        transactionService.updateTransaction(transaction);
        return "redirect:/trans/list2";
    }

    @GetMapping("/delete")
    public String showDeleteMainForm(Model model){
        model.addAttribute("transactions", transactionService.listAllTransactions());
        return "choose-trans-to-delete";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable("id") int id, Model model){
        Transaction transaction = transactionService.findTransactionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transaction id: " + id));
        transactionService.deleteTransaction(transaction);
        model.addAttribute("transactions", transactionService.listAllTransactions());
        return "redirect:/trans/list2";
    }
}
