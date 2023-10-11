package model;

public class Budgeting {
    private double monthlyBudget = 0;

    /*
     * REQUIRES: monthlyBudget > 0
     * EFFECTS: expenseName of expense is set to name;
     *
     */
    public Budgeting(double budget) {
        this.monthlyBudget = budget;

    }

    public void setMonthlyBudget(double budget) {
        this.monthlyBudget = budget;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }



}
