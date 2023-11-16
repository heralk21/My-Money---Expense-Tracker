package model;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of all expenses
public class ExpenseList implements Writable {
    private List<Expense> allExpenses;
    private Budgeting budget;

    // EFFECTS: constructs a list of expenses with no expenses
    public ExpenseList() {
        allExpenses = new ArrayList<>();
        budget = new Budgeting(0);
    }


    //getters//

    // EFFECTS: returns list of all expenses
    public List<Expense> getAllExpenses() {
        return allExpenses;
    }

    // EFFECTS: returns monthlyBudget (in $)
    public double getMonthlyBudget() {
        return  budget.getMonthlyBudget();
    }

    // EFFECTS: returns total amount spent (in $)
    //          from all the expenses in the list
    public double getTotalSpent() {
        double spent = 0;
        for (int i = 0; i < allExpenses.size(); i++) {
            spent += allExpenses.get(i).getExpenseCost();
        }
        return spent;
    }

    // REQUIRES : budget > 0
    // MODIFIES : this
    // EFFECTS: returns true if budget >= total money spent (in $)
    //          else returns false
    public boolean updateBudget(double budget) {
        if ((budget - getTotalSpent()) >= 0) {
            this.budget.setMonthlyBudget(budget);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES : non-empty expense
    // MODIFIES : this
    // EFFECTS: add the new expense to allExpenses
    //            if budget >= (total money spent + cost of new expense) (in $)
    //            and returns true
    //            else returns false
    public boolean addExpense(Expense expense) {
        if ((budget.getMonthlyBudget() - getTotalSpent() - expense.getExpenseCost()) >= 0) {
            allExpenses.add(expense);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES : name is not already in expenses
    // MODIFIES : this
    // EFFECTS: remove it from the allExpenses if name matches with the expenseName
    //          of any expenses in the expense list and returns true
    //          else returns false
    public boolean removeExpense(String name) {
        boolean isExpenseInList = false;
        for (int i = 0; i < allExpenses.size(); i++) {
            if (allExpenses.get(i).getExpenseName().equals(name)) {
                allExpenses.remove(allExpenses.get(i));
                isExpenseInList = true;
            } else {
                isExpenseInList = false;
            }
        }
        return isExpenseInList;
    }


    //EFFECTS : returns the money saved by subtracting the
    //          money spent from the monthly budget
    public double returnSavings() {
        return (budget.getMonthlyBudget() - getTotalSpent());
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Budget", budget.toJson());
        json.put("AllExpenses", allExpensesToJson());
        return json;
    }

    // EFFECTS: returns expense in this expense list as a JSON array
    private JSONArray allExpensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense t : allExpenses) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
//push before phase3//
}


