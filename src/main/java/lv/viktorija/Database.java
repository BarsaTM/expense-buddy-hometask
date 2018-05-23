package lv.viktorija;

import lv.viktorija.domain.Expense;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Expense> expenses;

    public Database() {
        this.expenses = new ArrayList<>();
    }

    public void add(Expense expense) {
        expenses.add(expense);
    }

    public Expense get(int i) {
        return expenses.get(i);
    }

    public List<Expense> getAll() {
        return expenses;
    }

    public int count() {
        return expenses.size();
    }
}
