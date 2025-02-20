package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.ExpenseList;
import model.Expense;
import persistence.JsonReader;
import persistence.JsonWriter;

//Expense Tracker Application
public class ExpenseTrackerApp {
    private static final String JSON_STORE = "./data/workroom.json";
    private Scanner input;
    private ExpenseList expenseList;
    private Expense expense;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //runs the expense tracker application
    public ExpenseTrackerApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        expenseList = new ExpenseList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runExpenseTracker();
    }

    //MODIFIES : this
    //EFFECTS : processes the input
    private void runExpenseTracker() {
        boolean keepGoing = true;
        int command = 0;

        init();

        System.out.println("Welcome to MyMoney!");

        while (keepGoing) {
            displayMenu();
            command = input.nextInt();

            if (command == 8) {
                System.out.println("Thank you for using MyMoney!!");
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    //MODIFIES : this
    //EFFECTS : initializes the expense list
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        expenseList = new ExpenseList();

    }

    //EFFECTS : displays the menu of options to the user
    private void displayMenu() {
        System.out.println("\nSelect from :");
        System.out.println("\t1 -> add/update existing monthly budget");
        System.out.println("\t2 -> add expense");
        System.out.println("\t3 -> remove expense");
        System.out.println("\t4 -> show all expenses");
        System.out.println("\t5 -> save work room to file");
        System.out.println("\t6 -> load work room from file");
        System.out.println("\t7 -> show savings and quit");
        System.out.println("\t8 -> quit the application");
    }

    //MODIFIES: this
    //EFFECTS : processes user command
    private void processCommand(int command) {
        if (command == 1) {
            editBudget();
        } else if (command == 2) {
            addExpense();
        } else if (command == 3) {
            removeExpense();
        } else if (command == 4) {
            showAllExpenses();
        } else if (command == 5) {
            saveExpenseListBig();
        } else if (command == 6) {
            loadExpenseListBig();
        } else if (command == 7) {
            showSavings();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES : this
    //EFFECTS : updates the monthly budget
    private void editBudget() {
        System.out.println("Your current budget : " + expenseList.getMonthlyBudget());
        System.out.println("Enter your new budget : ");
        double inpBudget = input.nextDouble();

        if (expenseList.updateBudget(inpBudget)) {
            System.out.println("Budget Update successful!");
        } else {
            System.out.println("You have already spent more than your new budget!!");
            System.out.println("So budget change not possible ;(");
        }
        System.out.println("Your current budget : " + expenseList.getMonthlyBudget());
    }

    //MODIFIES : this
    //EFFECTS : inputs an expense and adds it to expenseList
    private void addExpense() {
        System.out.println("Enter expense name : ");

        String inpExpenseName = input.next();

        System.out.println("Enter expense cost :");
        double inpExpensecost = input.nextDouble();

        expense = new Expense(inpExpenseName,inpExpensecost);

        if (expenseList.addExpense(expense)) {
            System.out.println("Add successful!");
        } else {
            System.out.println("You are going over budget!!");
        }
    }

    //MODIFIES : this
    //EFFECTS : inputs a name of the expense the user wishes
    //          to remove and removes the expense from expenseList
    //          (if it exists in expenseList)
    private void removeExpense() {
        System.out.println("Enter expense name you wish to remove : ");

        String inpExpenseName = input.next();

        if (expenseList.removeExpense(inpExpenseName)) {
            System.out.println("Remove successful!");
        } else {
            System.out.println("Invalid input");
        }
    }

    //EFFECTS : displays all the expenses with their respective
    //          names and cost (in $)
    private void showAllExpenses() {
        System.out.println("Your expenses: ");
        List<Expense> allExpenses = expenseList.getAllExpenses();

        if (!(expenseList.getAllExpenses().size() == 0)) {
            for (int i = 0; i < expenseList.getAllExpenses().size(); i++) {
                System.out.println("Name: " + allExpenses.get(i).getExpenseName()
                        + " ; " + "Cost: $" + allExpenses.get(i).getExpenseCost());
            }
        } else {
            System.out.println("No expenses right now!");
        }
    }

    // EFFECTS: saves the expense list to file
    private void saveExpenseListBig() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenseList);
            jsonWriter.close();
            System.out.println("Saved " + expenseList.getAllExpenses() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads expense list from file
    private void loadExpenseListBig() {
        try {
            expenseList = jsonReader.read();
            System.out.println("Loaded " + expenseList.getAllExpenses() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS : displays the money saved (in $) from the monthly budget
    //          and quits the application
    private void showSavings() {
        System.out.println("Your savings: " + expenseList.returnSavings());
        if (expenseList.returnSavings() > 0) {
            System.out.println("Congratulations on your savings!");
        } else if (expenseList.returnSavings() == 0) {
            System.out.println("You have used your monthly budget!");
        } else {
            System.out.println("You have gone over your set budget!");
        }
    }


//push before phase3//
}

