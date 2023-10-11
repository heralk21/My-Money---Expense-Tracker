package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class testBudgeting {
    private Budgeting testbudgeting1;
    private Budgeting testbudgeting2;
    private Budgeting testbudgeting3;

    @BeforeEach
    void runBefore() {
        testbudgeting1 = new Budgeting(200.0);
        testbudgeting2 = new Budgeting(320.65);
        testbudgeting3 = new Budgeting(0);
    }

    @Test
    void testConstructor() {
        assertEquals(testbudgeting1.getMonthlyBudget(),200.0);
        testbudgeting1.setMonthlyBudget(100.0);
        assertEquals(testbudgeting1.getMonthlyBudget(),100.0);
    }

    @Test
    void testUpdateBudgetMultipleTimes() {
        assertEquals(testbudgeting2.getMonthlyBudget(),320.65);
        testbudgeting2.setMonthlyBudget(50.0);
        assertEquals(testbudgeting2.getMonthlyBudget(),50.0);

        testbudgeting2.setMonthlyBudget(320.0);
        assertEquals(testbudgeting2.getMonthlyBudget(),320.0);
    }

    @Test
    void testBudgetIsZero() {
        assertEquals(testbudgeting3.getMonthlyBudget(),0);
        testbudgeting3.setMonthlyBudget(59.0);
        assertEquals(testbudgeting3.getMonthlyBudget(),59.0);

        testbudgeting3.setMonthlyBudget(10.15);
        assertEquals(testbudgeting3.getMonthlyBudget(),10.15);
    }



}