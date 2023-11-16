package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BudgetingTest {
    private Budgeting testBudgeting1;
    private Budgeting testBudgeting2;
    private Budgeting testBudgeting3;

    @BeforeEach
    void runBefore() {
        testBudgeting1 = new Budgeting(200.0);
        testBudgeting2 = new Budgeting(320.65);
        testBudgeting3 = new Budgeting(0);
    }

    @Test
    void testConstructor() {
        assertEquals(200.0,testBudgeting1.getMonthlyBudget());
        testBudgeting1.setMonthlyBudget(100.0);
        assertEquals(100.0,testBudgeting1.getMonthlyBudget());
    }

    @Test
    void testUpdateBudgetMultipleTimes() {
        testBudgeting2.setMonthlyBudget(50.0);
        assertEquals(50.0,testBudgeting2.getMonthlyBudget());

        testBudgeting2.setMonthlyBudget(300.0);
        assertEquals(300.0,testBudgeting2.getMonthlyBudget());
    }

    @Test
    void testBudgetIsZero() {
        assertEquals(0,testBudgeting3.getMonthlyBudget());
        testBudgeting3.setMonthlyBudget(59.0);
        assertEquals(59.0,testBudgeting3.getMonthlyBudget());

        testBudgeting3.setMonthlyBudget(10.15);
        assertEquals(10.15,testBudgeting3.getMonthlyBudget());
    }

//push before phase3//

}