package model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseList {
    private List<Expense> allExpenses;
    private Budgeting budget;

    public ExpenseList() {
        allExpenses = new ArrayList<>();
        budget = new Budgeting(0);
    }


    public List<Expense> getAllExpenses() {
        return allExpenses;
    }

    public double getMonthlyBudget() {
        return budget.getMonthlyBudget();
    }

    private double getTotalSpent() {
        double spent = 0;
        for (int i = 0; i < allExpenses.size(); i++) {
            spent += allExpenses.get(i).getExpenseCost();
        }
        return spent;
    }

    public boolean updateBudget(double budget) {
        if ((budget - getTotalSpent()) >= 0) {
            this.budget.setMonthlyBudget(budget);
            return true;
        } else {
            return false;
        }
    }

    public boolean addExpense(Expense expense) {
        if ((budget.getMonthlyBudget() - getTotalSpent() - expense.getExpenseCost()) >= 0) {
            allExpenses.add(expense);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeExpense(String name) {
        boolean isExpenseInList = false;
        for (int i = 0; i < allExpenses.size(); i++) {
            if (allExpenses.get(i).getExpenseName().equals(name)) {
                allExpenses.remove(allExpenses.get(i));
                isExpenseInList = true;
            }
        }
        return isExpenseInList;
    }

    public double returnSavings() {
        return (budget.getMonthlyBudget() - getTotalSpent());
    }
}


