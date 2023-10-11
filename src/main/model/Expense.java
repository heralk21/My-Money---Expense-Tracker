package model;


public class Expense {
    private String expenseName;
    private double expenseCost;

    /*
     * REQUIRES: expenseName has a non-zero length, expenseCost > 0
     * EFFECTS: expenseName of expense is set to name;
     */

    public Expense(String name,double cost) {
        this.expenseName = name;
        this.expenseCost = cost;

    }

    public String getExpenseName() {
        return expenseName;
    }

    public double getExpenseCost() {
        return expenseCost;
    }

    public void setExpenseCost(double expenseCost) {
        this.expenseCost = expenseCost;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }



}
