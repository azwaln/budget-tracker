package com.csc3402.project.transaction.controller;

import com.csc3402.project.transaction.model.Budget;
import com.csc3402.project.transaction.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/budgets")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/list")
    public String showAllBudgetForm(Model model) {
        model.addAttribute("budgets", budgetService.listAllBudgets());
        return "list-of-budget";
    }

    @GetMapping("/signup")
    public String showAddNewBudgetForm(Budget budget) {
        return "add-budget";
    }

    @PostMapping("/add")
    public String addBudget(@Valid Budget budget, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-budget";
        }
        budgetService.addNewBudget(budget);
        return "redirect:/budgets/list";
    }

    @GetMapping("/update")
    public String showUpdateMainForm(Model model) {
        model.addAttribute("budgets", budgetService.listAllBudgets());
        return "choose-budget-to-update";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Budget budget = budgetService.findBudgetById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid budget id:" + id));
        model.addAttribute("budget", budget);
        return "update-budget";
    }

    @PostMapping("/update/{id}")
    public String updateBudget(@PathVariable("id") long id, @Valid Budget budget, BindingResult result) {
        if (result.hasErrors()) {
            budget.setBudgetID((int) id);
            return "update-budget";
        }

        budgetService.updateBudget(budget);
        return "redirect:/budgets/list";
    }

    @GetMapping("/delete")
    public String showDeleteMainForm(Model model) {
        model.addAttribute("budgets", budgetService.listAllBudgets());
        return "choose-budget-to-delete";
    }

    @GetMapping("/delete/{id}")
    public String deleteBudget(@PathVariable("id") long id, Model model) {
        Budget budget = budgetService.findBudgetById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid budget id:" + id));
        budgetService.deleteBudget(budget);
        model.addAttribute("budgets", budgetService.listAllBudgets());
        return "redirect:/budgets/list";
    }
}
