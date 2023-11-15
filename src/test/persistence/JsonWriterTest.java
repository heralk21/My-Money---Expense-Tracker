package persistence;

import model.ExpenseList;
import model.Expense;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            ExpenseList expenseList = new ExpenseList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ExpenseList expenseList = new ExpenseList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpenseList.json");
            writer.open();
            writer.write(expenseList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpenseList.json");
            expenseList = reader.read();
            assertEquals(0, expenseList.getAllExpenses().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ExpenseList expenseList = new ExpenseList();
            expenseList.updateBudget(1000);
            expenseList.addExpense(new Expense ("testExpense1",200));
            expenseList.addExpense(new Expense ("testExpense2",150));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExpenseList.json");
            writer.open();
            writer.write(expenseList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExpenseList.json");
            expenseList = reader.read();
            List<Expense> expenseList1 = expenseList.getAllExpenses();
            assertEquals(2, expenseList1.size());
            checkExpense("testExpense1",200.0,expenseList1.get(0));
            checkExpense("testExpense2",150.0,expenseList1.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    //push before phase3
}