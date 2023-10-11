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
        assertEquals(testexpense.getExpenseName(),"iPad");
        assertEquals(testexpense.getExpenseCost(),600.0);
    }

    @Test
    void testChangeNameOnce() {
        assertEquals(testexpense.getExpenseName(),"iPad");
        assertEquals(testexpense.getExpenseCost(),600.0);
        testexpense.setExpenseName("iPhone");
        assertEquals(testexpense.getExpenseName(),"iPhone");
        assertEquals(testexpense.getExpenseCost(),600.0);
    }

    @Test
    void testChangeCostOnce() {
        assertEquals(testexpense.getExpenseName(),"iPad");
        assertEquals(testexpense.getExpenseCost(),600.0);
        testexpense.setExpenseCost(780.34);
        assertEquals(testexpense.getExpenseName(),"iPad");
        assertEquals(testexpense.getExpenseCost(),780.34);
    }

    @Test
    void testChangeNameMultipleTimes() {
        assertEquals(testexpense.getExpenseName(),"iPad");
        assertEquals(testexpense.getExpenseCost(),600.0);
        testexpense.setExpenseName("iPhone");

        assertEquals(testexpense.getExpenseName(),"iPhone");
        assertEquals(testexpense.getExpenseCost(),600.0);
        testexpense.setExpenseName("Rain Jacket");

        assertEquals(testexpense.getExpenseName(),"Rain Jacket");
        assertEquals(testexpense.getExpenseCost(),600.0);
    }

    @Test
    void testChangeCostMultipleTimes() {
        assertEquals(testexpense.getExpenseName(),"iPad");
        assertEquals(testexpense.getExpenseCost(),600.0);
        testexpense.setExpenseCost(544.98);

        assertEquals(testexpense.getExpenseName(),"iPad");
        assertEquals(testexpense.getExpenseCost(),544.98);
        testexpense.setExpenseCost(720);

        assertEquals(testexpense.getExpenseName(),"iPad");
        assertEquals(testexpense.getExpenseCost(),720.0);
    }

    @Test
    void testChangeNameandCostOnce() {
        assertEquals(testexpense.getExpenseName(),"iPad");
        assertEquals(testexpense.getExpenseCost(),600.0);
        testexpense.setExpenseName("iPhone");
        assertEquals(testexpense.getExpenseName(),"iPhone");
        testexpense.setExpenseCost(544.98);
        assertEquals(testexpense.getExpenseCost(),544.98);

    }

    @Test
    void testChangeNameandCostMultiple() {
        assertEquals(testexpense.getExpenseName(),"iPad");
        assertEquals(testexpense.getExpenseCost(),600.0);

        testexpense.setExpenseName("iPhone");
        assertEquals(testexpense.getExpenseName(),"iPhone");
        testexpense.setExpenseCost(544.98);
        assertEquals(testexpense.getExpenseCost(),544.98);

        testexpense.setExpenseName("Rain Jacket");
        assertEquals(testexpense.getExpenseName(),"Rain Jacket");
        testexpense.setExpenseCost(100);
        assertEquals(testexpense.getExpenseCost(),100.0);


    }


}
