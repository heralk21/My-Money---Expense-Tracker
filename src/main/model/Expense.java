package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an expense having a name and cost (in $)
public class Expense implements Writable {
    private String expenseName;
    private double expenseCost;

    // REQUIRES: cost > 0 (in $)
    // EFFECTS: constructs an expense having a name and cost (in $)
    public Expense(String name,double cost) {
        this.expenseName = name;
        this.expenseCost = cost;

    }

    //getters//

    // EFFECTS: returns name of the expense
    public String getExpenseName() {
        return expenseName;
    }

    // EFFECTS: returns cost of the expense (in $)
    public double getExpenseCost() {
        return expenseCost;
    }


    //setters//

    //MODIFIES : this
    // EFFECTS: sets name of the expense to expenseName
    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    //REQUIRES : expenseCost > 0
    //MODIFIES : this
    // EFFECTS: sets cost of the expense (in $) to expenseCost
    public void setExpenseCost(double expenseCost) {
        this.expenseCost = expenseCost;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Expense", expenseName);
        json.put("Cost", expenseCost);
        return json;
    }

    //push phase1

}
