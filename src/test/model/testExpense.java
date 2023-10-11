package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testExpense {
    private Expense testexpense;

    @BeforeEach
    void runBefore() {
        testexpense = new Expense("iPad",600.0);
    }

    @Test
    void testConstructor() {
        assertEquals("iPad",testexpense.getExpenseName());
        assertEquals(600.0,testexpense.getExpenseCost());
    }

    @Test
    void testGetExpenseName() {
        assertEquals("iPad",testexpense.getExpenseName());
    }

    @Test
    void testGetExpenseCost() {
        assertEquals(600.0, testexpense.getExpenseCost());
    }

    @Test
    void testSetExpenseName() {
        assertEquals("iPad", testexpense.getExpenseName());
        assertEquals(600.0,testexpense.getExpenseCost());
        testexpense.setExpenseName("iPhone");
        assertEquals("iPhone", testexpense.getExpenseName());
        assertEquals(600.0,testexpense.getExpenseCost());
    }

    @Test
    void testSetExpenseCost() {
        assertEquals(600.0, testexpense.getExpenseCost());
        assertEquals("iPad",testexpense.getExpenseName());
        testexpense.setExpenseCost(435.98);
        assertEquals(435.98, testexpense.getExpenseCost());
        assertEquals("iPad",testexpense.getExpenseName());
    }

    @Test
    void testChangeNameOnce() {
        assertEquals("iPad",testexpense.getExpenseName());
        assertEquals(600.0,testexpense.getExpenseCost());
        testexpense.setExpenseName("iPhone");
        assertEquals("iPhone",testexpense.getExpenseName());
        assertEquals(600.0,testexpense.getExpenseCost());
    }

    @Test
    void testChangeCostOnce() {
        assertEquals("iPad",testexpense.getExpenseName());
        assertEquals(600.0,testexpense.getExpenseCost());
        testexpense.setExpenseCost(780.34);
        assertEquals("iPad",testexpense.getExpenseName());
        assertEquals(780.34,testexpense.getExpenseCost());
    }

    @Test
    void testChangeNameMultipleTimes() {
        assertEquals("iPad",testexpense.getExpenseName());
        assertEquals(600.0,testexpense.getExpenseCost());
        testexpense.setExpenseName("iPhone");

        assertEquals("iPhone",testexpense.getExpenseName());
        assertEquals(600.0,testexpense.getExpenseCost());
        testexpense.setExpenseName("Rain Jacket");

        assertEquals("Rain Jacket",testexpense.getExpenseName());
        assertEquals(600.0,testexpense.getExpenseCost());
    }

    @Test
    void testChangeCostMultipleTimes() {
        assertEquals("iPad",testexpense.getExpenseName());
        assertEquals(600.0,testexpense.getExpenseCost());
        testexpense.setExpenseCost(544.98);

        assertEquals("iPad",testexpense.getExpenseName());
        assertEquals(544.98,testexpense.getExpenseCost());
        testexpense.setExpenseCost(720);

        assertEquals("iPad",testexpense.getExpenseName());
        assertEquals(720.0,testexpense.getExpenseCost());
    }

    @Test
    void testChangeNameandCostOnce() {
        assertEquals("iPad",testexpense.getExpenseName());
        assertEquals(600.0,testexpense.getExpenseCost());
        testexpense.setExpenseName("iPhone");
        assertEquals("iPhone",testexpense.getExpenseName());
        testexpense.setExpenseCost(544.98);
        assertEquals(544.98,testexpense.getExpenseCost());

    }

    @Test
    void testChangeNameandCostMultiple() {
        assertEquals("iPad",testexpense.getExpenseName());
        assertEquals(600.0, testexpense.getExpenseCost());

        testexpense.setExpenseName("iPhone");
        assertEquals("iPhone",testexpense.getExpenseName());
        testexpense.setExpenseCost(544.98);
        assertEquals(544.98, testexpense.getExpenseCost());

        testexpense.setExpenseName("Rain Jacket");
        assertEquals("Rain Jacket",testexpense.getExpenseName());
        testexpense.setExpenseCost(100);
        assertEquals(100.0,testexpense.getExpenseCost());


    }


}
