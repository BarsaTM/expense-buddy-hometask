package lv.viktorija.calculator;

import lv.viktorija.Database;
import lv.viktorija.domain.Expense;
import lv.viktorija.domain.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is service for calculating expenses stored in Database.
 */
public class ExpenseCalculator {

    public ExpenseCalculator() {
    }

    public ExpenseCalculationResult calculate(Database database) {
        long total = total(database);
        Map<String, Long> perPersonStatistics = personStatistics(database);
        double perPersonAverage = average(total, perPersonStatistics.size());

        // Calculate transactions.
        // Calculations are made in separate class, because we are using
        // recursive algorithm and we wanna to isolate this.
        TransactionCalculator transactionCalculator = new TransactionCalculator(total, perPersonAverage, perPersonStatistics);
        List<Transaction> transactions = transactionCalculator.getTransactions();

        return new ExpenseCalculationResult(total, perPersonAverage, perPersonStatistics, transactions);
    }

    /**
     * Get total amount of money spend.
     */
    private long total(Database database) {
        long sum = 0;
        for (int i = 0; i < database.count(); i++) {
            sum += database.get(i).getAmount();
        }
        return sum;
    }

    /**
     * Calculate average amount of money spend per-person.
     */
    private double average(long totalAmount, int personCount) {
        if (personCount == 0) {
            return 0;
        } else {
            // Ensure that we will not loose fractions in our calculation
            return (double) totalAmount / (double) personCount;
        }
    }

    /**
     * Calculate how much each person have spend money.
     */
    private Map<String, Long> personStatistics(Database database) {
        Map<String, Long> perPersonStatistics = new HashMap<>();

        for (int i = 0; i < database.getAll().size(); i++) {
            Expense expense = database.get(i);

            String personName = expense.getPersonName();
            Long currentAmount = perPersonStatistics.getOrDefault(personName, 0L);
            perPersonStatistics.put(personName, currentAmount + expense.getAmount());
        }

        return perPersonStatistics;
    }
}
