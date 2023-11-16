package persistence;

import model.Expense;
import model.ExpenseList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ExpenseList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenseList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses expense list from JSON object and returns it
    private ExpenseList parseExpenseList(JSONObject jsonObject) {
        ExpenseList el = new ExpenseList();
        addBudget(el, jsonObject);
        addAllExpenses(el, jsonObject);
        return el;
    }

    // MODIFIES: el
    // EFFECTS: parses allexpenses from JSON object and adds them to expense list
    private void addAllExpenses(ExpenseList el, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("AllExpenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(el, nextExpense);
        }
    }

    // MODIFIES: el
    // EFFECTS: parses expense from JSON object and adds it to expense list
    private void addExpense(ExpenseList el, JSONObject jsonObject) {
        String name = jsonObject.getString("expenseName");
        Double cost = jsonObject.getDouble("expenseCost");
        Expense expense = new Expense(name, cost);
        el.addExpense(expense);
        el.getAllExpenses().get(0).getExpenseName();
    }

    // MODIFIES: el
    // EFFECTS: parses expense from JSON object and adds it to expense list
    private void addBudget(ExpenseList el, JSONObject jsonObject) {
        JSONObject jsonObj = jsonObject.getJSONObject("Budget");
        double budget = jsonObj.getDouble("monthlyBudget");
        el.updateBudget(budget);
    }

//push before phase3//
}