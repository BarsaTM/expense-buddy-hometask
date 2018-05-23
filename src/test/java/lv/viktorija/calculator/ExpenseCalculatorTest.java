package lv.viktorija.calculator;

import lv.viktorija.Database;
import lv.viktorija.domain.Expense;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ExpenseCalculatorTest {

    private static final double DELTA = 0.000000001;

    private ExpenseCalculator expenseCalculator;
    private Database database;

    @Before
    public void setUp() throws Exception {
        database = new Database();
        expenseCalculator = new ExpenseCalculator();
    }

    @Test
    public void amountIsZeroWhenNoExpenses() throws Exception {
        ExpenseCalculationResult result = expenseCalculator.calculate(database);

        Assert.assertEquals(0, result.getTotal());
    }

    @Test
    public void averageIsZeroWhenNoExpenses() throws Exception {
        ExpenseCalculationResult result = expenseCalculator.calculate(database);

        Assert.assertEquals(0, result.getPerPersonAverage(), DELTA);
    }

    @Test
    public void correctTotalWhenThereAreExpenses() throws Exception {
        database.add(new Expense("Viktoria", "Aloe", 100));
        database.add(new Expense("Vanya", "Vera", 250));

        ExpenseCalculationResult result = expenseCalculator.calculate(database);

        Assert.assertEquals(350, result.getTotal());
    }

    @Test
    public void correctAverageWhenThereAreExpenses() throws Exception {
        database.add(new Expense("Viktoria", "Aloe", 100));
        database.add(new Expense("Vanya", "Vera", 250));

        ExpenseCalculationResult result = expenseCalculator.calculate(database);

        Assert.assertEquals(175.0, result.getPerPersonAverage(), DELTA);
    }

    @Test
    public void statisticAreEmptyIfNoExpenses() throws Exception {
        ExpenseCalculationResult result = expenseCalculator.calculate(database);

        Map<String, Long> statistics = result.getPerPersonStatistics();

        Assert.assertEquals(0, statistics.size());
    }

    @Test
    public void correctStatisticsIfThereAreExpenses() throws Exception {
        database.add(new Expense("Viktoria", "Aloe", 100));
        database.add(new Expense("Viktoria", "Museum", 4000));
        database.add(new Expense("John", "barbeque", 10000));

        ExpenseCalculationResult result = expenseCalculator.calculate(database);

        Map<String, Long> statistics = result.getPerPersonStatistics();

        long viktoria = statistics.get("Viktoria");
        long john = statistics.get("John");

        Assert.assertEquals(4100L, viktoria);
        Assert.assertEquals(10000, john);
    }
}
