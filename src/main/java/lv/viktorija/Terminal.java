package lv.viktorija;

import lv.viktorija.calculator.ExpenseCalculationResult;
import lv.viktorija.calculator.ExpenseCalculator;
import lv.viktorija.domain.Expense;
import lv.viktorija.domain.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Hide implementation behing Terminal class.
 * It will provide easy-to-use methods to get input from user and show results.
 */
public class Terminal {

    private final Database database;
    private final ExpenseCalculator expenseCalculator;
    private final Scanner scanner;

    public Terminal() {
        database = new Database();
        expenseCalculator = new ExpenseCalculator();

        // Get input from console
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.next();
    }

    public void askUserForExpenseInformation() {
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Reason: ");
        String reason = scanner.next();
        System.out.print("Amount (minor units): ");
        long amount = scanner.nextLong();

        addExpense(name, reason, amount);

        System.out.println();
    }

    /**
     * Load sample dataset to database to illustrate how buddy works.
     */
    public void loadSampleData() {
        addExpense("Lisa", "Cake", 500);
        addExpense("Lisa", "Hotel", 12000);
        addExpense("Lisa", "Museum ticket", 2000);
        addExpense("Hans", "Museum ticket", 2000);
        addExpense("Hans", "Museum ticket", 2000);
        addExpense("Hans", "Dinner", 3400);
        addExpense("Ivan", "Railway tickets", 4800);
        addExpense("Ivan", "Supper", 3300);
    }

    public void addExpense(String name, String reason, long amount) {
        database.add(new Expense(name, reason, amount));
    }

    public void showExpenses() {
        System.out.println();
        System.out.println("--------------");
        System.out.println("Expenses:");

        for (Expense expense : database.getAll()) {
            System.out.println("  " + expense.getPersonName() + ": " + expense.getExpenseReason() + " - " + toMoney(expense.getAmount()));
        }
    }

    public void showStatistics() {
        ExpenseCalculationResult result = expenseCalculator.calculate(database);

        System.out.println();
        System.out.println("--------------");
        System.out.println("Total: " + toMoney(result.getTotal()));
        System.out.println("Average per person: " + toMoney(result.getPerPersonAverage()));
        System.out.println();

        Map<String, Long> perPersonStatistics = result.getPerPersonStatistics();

        System.out.println("Per person:");
        for (Map.Entry<String, Long> entry : perPersonStatistics.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + toMoney(entry.getValue()));
        }
    }

    public void showTransactions() {
        ExpenseCalculationResult result = expenseCalculator.calculate(database);

        System.out.println();
        System.out.println("--------------");
        System.out.println("Transaction to be made:");

        List<Transaction> transactions = result.getTransactions();
        for (Transaction transaction : transactions) {
            System.out.println("  " + transaction.getFromName() + " -> "
                    + transaction.getToName() + " : " + toMoney(transaction.getAmount()));
        }
    }

    private String toMoney(long amount) {
        return toMoney((double) amount);
    }

    private String toMoney(double amount) {
        return amount / 100.0 + "$";
    }
}
