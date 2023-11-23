package ui;

import model.Expense;
import model.ExpenseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// ExpenseTracker Application with GUI
public class ExpenseTrackerAppGUI extends JFrame implements ActionListener {

    private ExpenseList expenseList;

    private JFrame frame;
    private JPanel panel;

    private JButton updateBudgetButton;
    private JButton addExpenseButton;
    private JButton removeExpenseButton;
    private JButton showExpensesButton;
    private JButton showSavingsButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;

    private JTextField name;
    private JTextField amount;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workroom.json";

    // MODIFIES: this
    // EFFECTS: runs the expense tracker with GUI
    public ExpenseTrackerAppGUI() throws FileNotFoundException {
        initComponents();
        initGUI();
        addListeners();
    }

    // MODIFIES: this
    // EFFECTS: Initializes the fields
    private void initComponents() {
        frame = new JFrame("Expense Tracker");
        panel = new JPanel();
        expenseList = new ExpenseList();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        updateBudgetButton = new JButton("Add/Update Monthly Budget");
        addExpenseButton = new JButton("Add Expense");
        removeExpenseButton = new JButton("Remove Expense");
        showExpensesButton = new JButton("Show All Expenses");
        saveButton = new JButton("Save to File");
        loadButton = new JButton("Load from File");
        showSavingsButton = new JButton("Show Savings");
        quitButton = new JButton("Quit");


        name = new JTextField(30);
        amount = new JTextField(10);

    }

    // MODIFIES: this
    // EFFECTS: Initializes the GUI of the application
    private void initGUI() {
        initAlignments();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panel.setPreferredSize(new Dimension(280, 600));

        addImage(panel);
        addButtons(panel);

        frame.add(panel, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: Initializes the alignment of all elements
    private void initAlignments() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        updateBudgetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addExpenseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeExpenseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        showExpensesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        showSavingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }


    // MODIFIES: this
    // EFFECTS: Adds required button to panel
    private void addButtons(JPanel panel) {
        panel.add(Box.createVerticalStrut(10));
        panel.add(updateBudgetButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(addExpenseButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(removeExpenseButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(showExpensesButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(saveButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(loadButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(showSavingsButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(quitButton);
    }

    // MODIFIES: this
    // EFFECTS: Adds image to panel
    private void addImage(JPanel panel) {
        try {
            BufferedImage myPicture = ImageIO.read(new File("./image/cute dollar sign.jpg"));
            Image scaledImage = myPicture.getScaledInstance(200,200,Image.SCALE_SMOOTH);
            JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
            picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(picLabel);
        } catch (IOException e) {
            System.out.println("Unable to load the image");
        }
        JLabel str = new JLabel("Welcome to MyMoney!");
        str.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(str);
    }

    // MODIFIES: this
    // EFFECTS: Adds listeners to the buttons
    private void addListeners() {
        addExpenseButton.addActionListener(this);
        removeExpenseButton.addActionListener(this);
        updateBudgetButton.addActionListener(this);
        showExpensesButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        showSavingsButton.addActionListener(this);
        quitButton.addActionListener(this);
    }


    // EFFECTS: Processes specific action when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateBudgetButton) {
            updateBudget();
        } else if (e.getSource() == addExpenseButton) {
            addExpense();
        } else if (e.getSource() == removeExpenseButton) {
            removeExpense();
        } else if (e.getSource() == showExpensesButton) {
            showAllExpenses();
        } else if (e.getSource() == saveButton) {
            saveExpenseList();
        } else if (e.getSource() == loadButton) {
            checkForLoadFile();
        } else if (e.getSource() == showSavingsButton) {
            showSavings();
        } else if (e.getSource() == quitButton) {
            quitProgram();
        }
    }

    // MODIFIES: this
    // EFFECTS: Resets name and cost TextFields
    private void resetFields() {
        name.setText("");
        amount.setText("");
    }

    // MODIFIES: this
    // EFFECTS: adds an expense to the expense list
    private void addExpense() {
        JPanel addExpensePanel = new JPanel();

        initAddPanel(addExpensePanel);

        addExpensePanel.setLayout(new BoxLayout(addExpensePanel, BoxLayout.Y_AXIS));

        int result = JOptionPane.showConfirmDialog(null, addExpensePanel,
                "Enter Expense", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Expense expense = new Expense(name.getText(), Double.parseDouble(amount.getText()));
            if (expenseList.addExpense(expense)) {
                JOptionPane.showMessageDialog(null,
                        String.format("Add Successful!"),
                        "Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                resetFields();
            } else {
                JOptionPane.showMessageDialog(null,
                        String.format("You are going over budget!!"),
                        "Unsuccessful",
                        JOptionPane.INFORMATION_MESSAGE);
                resetFields();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: helper in initializing the add panel
    private void initAddPanel(JPanel addExpensePanel) {
        addExpensePanel.add(Box.createVerticalStrut(10));
        addExpensePanel.add(new JLabel("Expense Name"));
        addExpensePanel.add(name);
        addExpensePanel.add(Box.createVerticalStrut(10));
        addExpensePanel.add(new JLabel("Expense Cost"));
        addExpensePanel.add(amount);
    }

    // MODIFIES: this
    // EFFECTS: removes the specified expense from the expense list
    private void removeExpense() {
        JPanel removeExpensePanel = new JPanel();
        removeExpensePanel.setLayout(new BoxLayout(removeExpensePanel, BoxLayout.Y_AXIS));
        removeExpensePanel.add(new JLabel("Enter name of the expense you wish to remove:"));
        removeExpensePanel.add(new JLabel("Expense Name"));
        removeExpensePanel.add(name);

        int result = JOptionPane.showConfirmDialog(null, removeExpensePanel,
                "Prompt", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (expenseList.removeExpense(name.getText())) {
                JOptionPane.showMessageDialog(null,
                        "Remove Successful!", "Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                resetFields();
            } else {
                JOptionPane.showMessageDialog(null,
                        String.format("Expense with the name : %s is not present in your monthly expenses, ",
                                name.getText()), "Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
                resetFields();
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: updates the monthly budget
    private void updateBudget() {
        JPanel updateBudgetPanel = new JPanel();
        updateBudgetPanel.setLayout(new BoxLayout(updateBudgetPanel, BoxLayout.Y_AXIS));
        amount.setText(Double.toString(expenseList.getMonthlyBudget()));
        initUpdateBudget(updateBudgetPanel);


        int result = JOptionPane.showConfirmDialog(null, updateBudgetPanel,
                "Prompt", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (expenseList.updateBudget(Double.parseDouble(amount.getText()))) {
                JOptionPane.showMessageDialog(null,
                        "Budget updated Successfully!", "Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                resetFields();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: helper in initializing the update panel
    private void initUpdateBudget(JPanel updatePanel) {
        updatePanel.add(new JLabel("Update budget:"));
        updatePanel.add(Box.createVerticalStrut(10));
        updatePanel.add(amount);
    }


    // EFFECTS: shows all the current expenses in the expense list
    private void showAllExpenses() {
        List<Expense> allExpenses = expenseList.getAllExpenses();
        JPanel showAllExpensesPanel = new JPanel();
        showAllExpensesPanel.setLayout(new BoxLayout(showAllExpensesPanel, BoxLayout.Y_AXIS));
        showAllExpensesPanel.add(Box.createVerticalStrut(10));
        showAllExpensesPanel.add(new JLabel("Current expenses are displayed below:"));
        showAllExpensesPanel.add(Box.createVerticalStrut(20));

        for (Expense e : allExpenses) {
            addExpenseToPanel(showAllExpensesPanel, e);
        }

        JOptionPane.showMessageDialog(null, showAllExpensesPanel,
                "Flashcards", JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: helper in adding an expense to the showAllExpenses panel
    private void addExpenseToPanel(JPanel showAllExpensesPanel, Expense e) {
        showAllExpensesPanel.add(new JLabel(String.format("Expense name: %s", e.getExpenseName())));
        showAllExpensesPanel.add(Box.createVerticalStrut(5));
        showAllExpensesPanel.add(new JLabel(String.format("Expense cost: $%s", e.getExpenseCost())));
        showAllExpensesPanel.add(Box.createVerticalStrut(20));
    }


    // EFFECTS: saves the expense list to file
    private void saveExpenseList() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenseList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    String.format("Saved " + "Expenses" + " to " + JSON_STORE), "Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    String.format("Unable to save to file: " + JSON_STORE), "Unsuccessful",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if user wants to override the current expenseList with the saved one
    private void checkForLoadFile() {
        java.util.List<Expense> showExpenseList = expenseList.getAllExpenses();
        if (!showExpenseList.isEmpty()) {
            int result = JOptionPane.showConfirmDialog(null,
                    "Do you wish to open last saved file, your current file will be overriden..",
                    "Alert", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                loadExpenseList();
            }
        } else {
            loadExpenseList();
        }
    }


    // MODIFIES: this
    // EFFECTS: loads expense list from file
    private void loadExpenseList() {
        try {
            expenseList = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    String.format("Loaded " + "Last Saved Expenses" + " from " + JSON_STORE), "Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    String.format("Unable to load from file: " + JSON_STORE), "Unsuccessful",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: shows savings till now
    private void showSavings() {
        String message = "";
        if (expenseList.returnSavings() > 0) {
            message = String.format("Congratulations on your savings: $%s", expenseList.returnSavings());
        } else if (expenseList.returnSavings() == 0) {
            message = "You have used your monthly budget!";
        } else {
            message = "You have gone over your set budget!";
        }

        JOptionPane.showMessageDialog(null,
                message, "Savings",
                JOptionPane.INFORMATION_MESSAGE);
    }


    // MODIFIES: this
    // EFFECTS: ends the application
    private void quitProgram() {
        JOptionPane.showMessageDialog(null,
                "Thank you for using MyMoney!!", "Quitting",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
        frame.dispose();
        frame.setVisible(false);
    }
}


