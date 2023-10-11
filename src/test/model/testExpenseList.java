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

    @BeforeEach
    void runBefore() {
        testexpenselist = new ExpenseList();
    }

    @Test
    void testConstructor() {
        List<Expense> testList = testexpenselist.getAllExpenses();
        assertTrue(testList.isEmpty());
        assertEquals(testexpenselist.getMonthlyBudget(),0);
        assertEquals(testexpenselist.getTotalSpent(),0);
    }



}