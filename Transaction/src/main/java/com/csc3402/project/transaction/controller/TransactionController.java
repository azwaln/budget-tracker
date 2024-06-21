package com.csc3402.project.transaction.controller;

import com.csc3402.project.transaction.model.Budget;
import com.csc3402.project.transaction.service.CategoryService;
import com.csc3402.project.transaction.service.TransactionService;
import com.csc3402.project.transaction.model.Transaction;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final CategoryService categoryService;

    public TransactionController(TransactionService transactionService, CategoryService categoryService) {
        this.transactionService = transactionService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String showAllTransactionForm(Model model) {
        model.addAttribute("transactions", transactionService.listAllTransactions());
        return "list-of-transaction";
    }

    @GetMapping("/signup")
    public String showAddNewTransactionForm(Transaction transaction, Model model) {
        model.addAttribute("categories", categoryService.listAllCategories());
        return "add-transaction";
    }

    @PostMapping("/add")
    public String addTransaction(@Valid Transaction transaction, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-transaction";
        }
        transactionService.addNewTransaction(transaction);
        return "redirect:/transactions/list";
    }

    @GetMapping("/update")
    public String showUpdateMainForm(Model model) {
        model.addAttribute("transactions", transactionService.listAllTransactions());
        return "choose-transaction-to-update";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Transaction transaction = transactionService.findTransactionById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transaction id:" + id));
        model.addAttribute("transaction", transaction);
        model.addAttribute("categories", categoryService.listAllCategories());
        return "update-transaction";
    }

    @PostMapping("/update/{id}")
    public String updateTransaction(@PathVariable("id") long id, @Valid Transaction transaction, BindingResult result) {
        if (result.hasErrors()) {
            transaction.setTransactionID((int) id);
            return "update-transaction";
        }

        transactionService.updateTransaction(transaction);
        return "redirect:/transactions/list";
    }

    @GetMapping("/delete")
    public String showDeleteMainForm(Model model) {
        model.addAttribute("transactions", transactionService.listAllTransactions());
        return "choose-transaction-to-delete";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable("id") long id, Model model) {
        Transaction transaction = transactionService.findTransactionById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transaction id:" + id));
        transactionService.deleteTransaction(transaction);
        model.addAttribute("transactions", transactionService.listAllTransactions());
        return "redirect:/transactions/list";
    }
}
