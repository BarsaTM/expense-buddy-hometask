package lv.viktorija.domain;

/**
 * This class represents one transaction between 2 persons.
 */
public class Transaction {

    private String fromName;
    private String toName;
    private double amount;

    public Transaction(String fromName, String toName, double amount) {
        this.fromName = fromName;
        this.toName = toName;
        this.amount = amount;
    }

    public String getFromName() {
        return fromName;
    }

    public String getToName() {
        return toName;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "fromName='" + fromName + '\'' +
                ", toName='" + toName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
