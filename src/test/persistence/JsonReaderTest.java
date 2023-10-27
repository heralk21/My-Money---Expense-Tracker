package persistence;

import model.Expense;
import model.ExpenseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ExpenseList el = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            ExpenseList el = reader.read();
            assertEquals(0, el.getMonthlyBudget());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneral() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExpenseList.json");
        try {
            ExpenseList el = reader.read();
            assertEquals(10000, el.getMonthlyBudget());
            List<Expense> allExpenses = el.getAllExpenses();
            System.out.println(allExpenses);
            assertEquals(2, allExpenses.size());
            checkExpense("testExpense1", 1000.00, allExpenses.get(0));
            checkExpense("testExpense2", 2000.00, allExpenses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}