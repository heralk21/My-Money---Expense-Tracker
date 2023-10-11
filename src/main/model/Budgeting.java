package model;

// Represents a monthly budget (in $)
public class Budgeting {
    private double monthlyBudget;

    // REQUIRES: budget > 0 (in $)
    // MODIFIES : this
    // EFFECTS: constructs a monthlyBudget(in $)
    public Budgeting(double budget) {
        this.monthlyBudget = budget;
    }

    // REQUIRES : budget > 0 (in $)
    // MODIFIES : this
    // EFFECTS: sets monthlyBudget to budget (in $)
    public void setMonthlyBudget(double budget) {
        this.monthlyBudget = budget;
    }

    // EFFECTS: returns the monthlyBudget (in $)
    public double getMonthlyBudget() {
        return monthlyBudget;
    }



}
