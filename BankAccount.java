/**
 * BankAccount.java
 *
 * Description: Represents a single bank account with deposit, withdrawal,
 * and statement generation functionality. Uses a static counter to
 * automatically assign unique account numbers.
 *
 * @author Nikolas Baker
 * @version 1.0
 * @date 2026-04-29
 */
 
import java.util.ArrayList;
 
/**
 * Models a bank account with an owner name, balance, and transaction history.
 */
public class BankAccount {
 
    /** Static counter used to generate unique account numbers. */
    private static int nextAccountNumber = 1000;
 
    /** This account's unique number. */
    private int accountNumber;
 
    /** Name of the account holder. */
    private String ownerName;
 
    /** Current balance of the account. */
    private double balance;
 
    /** List of transaction records for statement generation. */
    private ArrayList<String> transactions;
 
    /**
     * Constructs a new BankAccount with a unique account number.
     *
     * @param ownerName      the name of the account holder
     * @param initialDeposit the opening balance
     * @throws IllegalArgumentException if initialDeposit is negative
     */
    public BankAccount(String ownerName, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }
        this.accountNumber = nextAccountNumber++;
        this.ownerName = ownerName;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        transactions.add("Account opened with $" + String.format("%.2f", initialDeposit));
    }
 
    /**
     * Returns the account number.
     *
     * @return the unique account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }
 
    /**
     * Returns the name of the account owner.
     *
     * @return the owner's name
     */
    public String getOwnerName() {
        return ownerName;
    }
 
    /**
     * Returns the current balance.
     *
     * @return the balance as a double
     */
    public double getBalance() {
        return balance;
    }
 
    /**
     * Updates the account owner's name.
     *
     * @param ownerName the new name for the account holder
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
 
    /**
     * Deposits the specified amount into the account.
     *
     * @param amount the amount to deposit (must be positive)
     * @throws IllegalArgumentException if amount is zero or negative
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        transactions.add("Deposit:    +$" + String.format("%.2f", amount)
                + "  |  Balance: $" + String.format("%.2f", balance));
    }
 
    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount the amount to withdraw (must be positive and within balance)
     * @throws IllegalArgumentException if amount is zero, negative, or exceeds balance
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException(
                    "Insufficient funds. Current balance: $" + String.format("%.2f", balance));
        }
        balance -= amount;
        transactions.add("Withdrawal: -$" + String.format("%.2f", amount)
                + "  |  Balance: $" + String.format("%.2f", balance));
    }
 
    /**
     * Generates and returns a formatted account statement including all transactions.
     *
     * @return a string containing the full account statement
     */
    public String generateStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================\n");
        sb.append("           ACCOUNT STATEMENT\n");
        sb.append("========================================\n");
        sb.append(String.format("  Account Number : %d%n", accountNumber));
        sb.append(String.format("  Owner          : %s%n", ownerName));
        sb.append(String.format("  Current Balance: $%.2f%n", balance));
        sb.append("----------------------------------------\n");
        sb.append("  Transaction History:\n");
        for (String t : transactions) {
            sb.append("    ").append(t).append("\n");
        }
        sb.append("========================================\n");
        return sb.toString();
    }
 
    /**
     * Returns a short summary string for this account.
     *
     * @return formatted string with account number, owner, and balance
     */
    @Override
    public String toString() {
        return String.format("  [#%d] %-20s  Balance: $%.2f",
                accountNumber, ownerName, balance);
    }
}