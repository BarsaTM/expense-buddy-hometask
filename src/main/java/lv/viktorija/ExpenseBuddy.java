package lv.viktorija;

/**
 * This is your expense buddy.
 * Going to a trip with your friends?
 * Expense buddy can handle your expenses!
 */
public class ExpenseBuddy {

    public static void main(String[] args) {
        new ExpenseBuddy().run();
    }

    /**
     * Start expense buddy!
     */
    public void run() {
        Terminal terminal = new Terminal();

        System.out.println("Do you want to explore sample dataset?");
        System.out.println("Type 'yes' if you want, or anything else to enter data manually.");

        String userInput = terminal.getUserInput();

        if (userInput.equalsIgnoreCase("yes")) {
            terminal.loadSampleData();
            showAllInformation(terminal);
        } else {
            // Endlessly ask user for expenses, until it decides to finish
            while (true) {
                System.out.println("-> Do you want to add another expense? Type 'yes' if do, or anything else otherwise.");

                String shouldAddAnotherExpense = terminal.getUserInput();
                if (shouldAddAnotherExpense.equalsIgnoreCase("yes")) {
                    // Ask user to enter expense information
                    terminal.askUserForExpenseInformation();
                } else {
                    // user do not want to add another expense, break from loop and show information.
                    break;
                }
            }

            // We are done, calculate and show information
            showAllInformation(terminal);
        }
    }

    private void showAllInformation(Terminal terminal) {
        terminal.showExpenses();
        terminal.showStatistics();
        terminal.showTransactions();
    }
}
