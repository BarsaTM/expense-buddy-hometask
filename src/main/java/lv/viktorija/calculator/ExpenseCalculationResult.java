package lv.viktorija.calculator;

import lv.viktorija.domain.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Expense calculation results.
 */
public class ExpenseCalculationResult {

    private final long total;
    private final double perPersonAverage;
    private final Map<String, Long> personStatistics;
    private final List<Transaction> transactions;

    public ExpenseCalculationResult(long total, double perPersonAverage,
                                    Map<String, Long> personStatistics, List<Transaction> transactions) {
        this.total = total;
        this.perPersonAverage = perPersonAverage;
        this.personStatistics = personStatistics;
        this.transactions = transactions;
    }

    public long getTotal() {
        return total;
    }

    public double getPerPersonAverage() {
        return perPersonAverage;
    }

    public Map<String, Long> getPerPersonStatistics() {
        return personStatistics;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
