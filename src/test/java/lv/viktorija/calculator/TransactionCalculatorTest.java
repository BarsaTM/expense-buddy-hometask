package lv.viktorija.calculator;

import lv.viktorija.domain.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class TransactionCalculatorTest {

    @Test
    public void testTransactions() throws Exception {
        TransactionCalculator transactionCalculator = new TransactionCalculator(0, 0, new HashMap<>());

        List<Transaction> transactions = transactionCalculator.getTransactions();

        Assert.assertEquals(0, transactions.size());
    }

    @Test
    public void twoPersons() throws Exception {
        HashMap<String, Long> perPersonStatistics = new HashMap<>();
        perPersonStatistics.put("Viktoria", 3000L);
        perPersonStatistics.put("Vanya", 5000L);
        long total = 5000L + 3000L;
        double average = (double) total / 2.0;

        TransactionCalculator transactionCalculator = new TransactionCalculator(total, average, perPersonStatistics);

        List<Transaction> transactions = transactionCalculator.getTransactions();
        Assert.assertEquals(1, transactions.size());
        Transaction transaction = transactions.get(0);

        Assert.assertEquals(transaction.getFromName(), "Viktoria");
        Assert.assertEquals(transaction.getToName(), "Vanya");
        Assert.assertEquals(transaction.getAmount(), (5000L - 3000L) / 2.0, 0.00000001);
    }
}
