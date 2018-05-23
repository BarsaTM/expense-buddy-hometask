package lv.viktorija;

import lv.viktorija.domain.Expense;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() throws Exception {
        database = new Database();
    }

    @Test
    public void sizeIsZeroWhenNoExpense() throws Exception {
        Assert.assertEquals(database.count(), 0);
    }

    @Test
    public void sizeIsOneAfterAdd() throws Exception {
        database.add(new Expense("Viktoria", "Aloe", 100));
        Assert.assertEquals(database.count(), 1);
    }

    @Test
    public void containsOurExpense() throws Exception {
        Expense expense = new Expense("Viktoria", "Vera", 100);
        database.add(expense);
        Assert.assertTrue(database.getAll().contains(expense));
    }
}
