package persistence;

import model.Expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExpense(String name, Double cost, Expense expense) {
        assertEquals(name, expense.getExpenseName());
        assertEquals(cost, expense.getExpenseCost());
    }
}
