package com.csc3402.project.transaction.controller;

import com.csc3402.project.transaction.model.Budget;
import com.csc3402.project.transaction.service.BudgetService;
import com.csc3402.project.transaction.service.CategoryService;
import com.csc3402.project.transaction.service.TransactionService;
import com.csc3402.project.transaction.model.Transaction;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.lang.StringTemplate.STR;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final CategoryService categoryService;
    private final BudgetService budgetService;

    public TransactionController(TransactionService transactionService, CategoryService categoryService, BudgetService budgetService) {
        this.transactionService = transactionService;
        this.categoryService = categoryService;
        this.budgetService = budgetService;
    }

    @GetMapping("/signup/{budgetID}")
    public String showAddNewTransactionForm(@PathVariable Integer budgetID, Model model) {
        model.addAttribute("categories", categoryService.listAllCategories());
        model.addAttribute("budgetID", budgetID);
        model.addAttribute("transaction", new Transaction());
        return "add-transaction";
    }


    @PostMapping("/add")
    public String addTransaction(@Valid Transaction transaction, BindingResult result, @RequestParam Integer budgetID, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.listAllCategories());
            model.addAttribute("budgetID", budgetID);
            return "add-transaction";
        }

        // Fetch the budget using the budgetID
        Optional<Budget> optionalBudget = budgetService.findBudgetById(budgetID);
        if (optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();
            transaction.setBudget(budget); // Associate the transaction with the budget
            transactionService.addNewTransaction(transaction);
        } else {
            // Handle the case where the budgetID is not found
            model.addAttribute("errorMessage", "Budget not found for ID: " + budgetID);
            return "add-transaction";
        }

        return "redirect:/transactions/list" + budgetID;
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
    public String updateTransaction(@PathVariable("id") long id, @Valid Transaction transaction, BindingResult result, Model model) {
        if (result.hasErrors()) {
            transaction.setTransactionID((int) id);
            model.addAttribute("categories", categoryService.listAllCategories());
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
    public String deleteTransaction(@PathVariable("id") long id) {
        Transaction transaction = transactionService.findTransactionById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transaction id:" + id));
        transactionService.deleteTransaction(transaction);
        return "redirect:/transactions/list";
    }

    @GetMapping("/list/{budgetID}")
    public String showAllTransactionForm(@PathVariable Integer budgetID, Model model) {
        Budget budget = budgetService.findBudgetById(budgetID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid budget id:" + budgetID));
        List<Transaction> transactions = transactionService.findTransactionsByBudget(budget);
        model.addAttribute("transactions", transactions);
        model.addAttribute("budgetID", budgetID);
        return "list-of-transaction";
    }
}
