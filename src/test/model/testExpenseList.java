package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class testExpenseList {
    private ExpenseList testexpenselist;
    private Expense expense1;
    private Expense expense2;
    private Expense expense3;

    @BeforeEach
    void runBefore() {
        testexpenselist = new ExpenseList();
        expense1 = new Expense("iPad", 600.0);
        expense2 = new Expense("iPhone", 425.76);
        expense3 = new Expense("Rain Jacket", 86.75);

    }

    @Test
    void testConstructor() {
        List<Expense> testList = testexpenselist.getAllExpenses();
        assertTrue(testList.isEmpty());
        assertEquals(testexpenselist.getMonthlyBudget(),0);
        assertEquals(testexpenselist.getTotalSpent(),0);
        assertEquals(testexpenselist.returnSavings(),0);
    }

    @Test
    void testUpdatebudgetOnceNoExpenses() {
        List<Expense> testList = testexpenselist.getAllExpenses();
        assertTrue(testList.isEmpty());
        assertEquals(testexpenselist.getMonthlyBudget(),0);
        assertEquals(testexpenselist.getTotalSpent(),0);
        assertEquals(testexpenselist.returnSavings(),0);

        assertTrue(testexpenselist.updateBudget(2000.0));
        assertEquals(testexpenselist.getMonthlyBudget(),2000.0);
        assertEquals(testexpenselist.getTotalSpent(),0);
        assertEquals(testexpenselist.returnSavings(),2000.0);

    }

    @Test
    void testUpdatebudgetMultipleTimesNoExpenses() {
        List<Expense> testList = testexpenselist.getAllExpenses();
        assertTrue(testList.isEmpty());
        assertEquals(testexpenselist.getMonthlyBudget(),0);
        assertEquals(testexpenselist.getTotalSpent(),0);
        assertEquals(testexpenselist.returnSavings(),0);

        assertTrue(testexpenselist.updateBudget(2000.0));
        assertEquals(testexpenselist.getMonthlyBudget(),2000.0);
        assertEquals(testexpenselist.getTotalSpent(),0);
        assertEquals(testexpenselist.returnSavings(),2000.0);

        assertTrue(testexpenselist.updateBudget(250.99));
        assertEquals(testexpenselist.getMonthlyBudget(),250.99);
        assertEquals(testexpenselist.getTotalSpent(),0);
        assertEquals(testexpenselist.returnSavings(),250.99);

    }

    @Test
    void testAddExpenseOnce() {
        List<Expense> testList = testexpenselist.getAllExpenses();
        assertTrue(testList.isEmpty());
        assertEquals(testexpenselist.getMonthlyBudget(),0);
        assertEquals(testexpenselist.getTotalSpent(),0);
        assertEquals(testexpenselist.returnSavings(),0);

        assertFalse(testexpenselist.addExpense(expense1));
        assertEquals(testexpenselist.getMonthlyBudget(),0);
        assertEquals(testexpenselist.getTotalSpent(),0);
        assertEquals(testexpenselist.returnSavings(),0);
    }

    @Test
    void testUpdateBudgetAddExpenseOnce() {
        List<Expense> testList = testexpenselist.getAllExpenses();
        assertTrue(testList.isEmpty());
        assertEquals(testexpenselist.getMonthlyBudget(),0);
        assertEquals(testexpenselist.getTotalSpent(),0);

        assertTrue(testexpenselist.updateBudget(1500.0));

        assertTrue(testexpenselist.addExpense(expense1));
        assertEquals(testexpenselist.getMonthlyBudget(),1500.0);
        assertEquals(testexpenselist.getTotalSpent(),600.0);
        assertEquals(testexpenselist.returnSavings(),900.0);
    }

    @Test
    void testUpdateBudgetAddExpenseMultipleTimes() {
        List<Expense> testList = testexpenselist.getAllExpenses();
        assertTrue(testList.isEmpty());
        assertEquals(testexpenselist.getMonthlyBudget(),0);
        assertEquals(testexpenselist.getTotalSpent(),0);
        assertEquals(testexpenselist.returnSavings(),0);

        assertTrue(testexpenselist.updateBudget(1500.0));

        assertTrue(testexpenselist.addExpense(expense1));
        assertEquals(testexpenselist.getMonthlyBudget(),1500.0);
        assertEquals(testexpenselist.getTotalSpent(),600.0);
        assertEquals(testexpenselist.returnSavings(),900.0);

        assertTrue(testexpenselist.addExpense(expense2));
        assertEquals(testexpenselist.getMonthlyBudget(),1500.0);
        assertEquals(testexpenselist.getTotalSpent(),1025.76);
        assertEquals(testexpenselist.returnSavings(),474.24);
    }










}