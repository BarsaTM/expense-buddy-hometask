package lv.viktorija.calculator;

import lv.viktorija.domain.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionCalculator {

    private final List<Transaction> transactions;
    private final long total;
    private final double average;
    private final Map<String, Long> perPersonStatistics;

    public TransactionCalculator(long total, double average, Map<String, Long> perPersonStatistics) {
        this.total = total;
        this.average = average;
        this.perPersonStatistics = perPersonStatistics;
        transactions = new ArrayList<>();

        Map<String, Double> balance = calculateBalance();
        calculateTransactions(balance);
    }

    /**
     * Retrieve calcualted transaction.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Calculate current balance for persons.
     * Positive balance -> person has more money that it should have.
     * Negative balance -> person wan't to get money from someone.
     */
    private Map<String, Double> calculateBalance() {
        // Double is not best option to store money data
        // But let's go with Double for now
        // Maybe use BigDecimal or some specific Money format?
        Map<String, Double> balance = new HashMap<>();

        // Iterating over all persons and calculating their balance.
        for (Map.Entry<String, Long> entry : perPersonStatistics.entrySet()) {
            double diff = average - (double) entry.getValue();
            if (diff != 0) { // If person have 0 balance, then he is OK already
                balance.put(entry.getKey(), diff);
            }
        }

        return balance;
    }

    /**
     * We are going to recursively iterate over balance and
     * one-by-one transfer money from someone who have money,
     * to someone who needs money.
     */
    private void calculateTransactions(Map<String, Double> balance) {
        if (balance.isEmpty()) {
            // There are no more person who owe someone.
            // Our job is done, finish.
            return;
        }

        // Find pair
        String someoneWhoHasMoney = findSomeoneWhoHasMoney(balance);
        String someoneWhoWantsMoney = findSomeoneWhoWantsMoney(balance);

        // How much spare money does this guy have?
        Double amountOfSpareMoney = balance.get(someoneWhoHasMoney);

        // Let's transfer this money to person who wants money
        transactions.add(new Transaction(someoneWhoHasMoney, someoneWhoWantsMoney, amountOfSpareMoney));

        // This person is OK, now his balance is 0.
        balance.remove(someoneWhoHasMoney);

        // Let's find out, is other guy is OK
        double newBalance = balance.get(someoneWhoWantsMoney) + amountOfSpareMoney;
        if (newBalance == 0) {
            // everything is cool, now this person received everything he wanted
            balance.remove(someoneWhoWantsMoney);
        } else {
            // nope, something else should be done.
            // Let's update his balance and move forward
            balance.put(someoneWhoWantsMoney, newBalance);
        }

        // Let's move to another pair!
        calculateTransactions(balance);
    }

    private String findSomeoneWhoHasMoney(Map<String, Double> balance) {
        for (Map.Entry<String, Double> entry : balance.entrySet()) {
            if (entry.getValue() > 0) { // Find first person which has money
                return entry.getKey();
            }
        }

        // There should always be someone who has money, if someone needs money.
        throw new RuntimeException("Should never happen");
    }

    private String findSomeoneWhoWantsMoney(Map<String, Double> balance) {
        for (Map.Entry<String, Double> entry : balance.entrySet()) {
            if (entry.getValue() < 0) { // Find first person who wants money
                return entry.getKey();
            }
        }

        // There should always be someone who want money, if someone has money.
        throw new RuntimeException("Should never happen");
    }
}
