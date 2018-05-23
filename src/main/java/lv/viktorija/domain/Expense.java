package lv.viktorija.domain;

/**
 * This class represents one expense entry.
 */
public class Expense {

    private final String personName;
    private final String expenseReason;
    private final long amount;

    public Expense(String personName, String expenseReason, long amount) {
        this.personName = personName;
        this.expenseReason = expenseReason;
        this.amount = amount;
    }

    public String getPersonName() {
        return personName;
    }

    public String getExpenseReason() {
        return expenseReason;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "personName='" + personName + '\'' +
                ", expenseReason='" + expenseReason + '\'' +
                ", amount=" + amount +
                '}';
    }
}
