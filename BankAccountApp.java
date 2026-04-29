/**
 * BankAccountApp.java
 *
 * Description: Main application for managing multiple bank accounts.
 * Provides a text-based menu to create, edit, remove, deposit into,
 * withdraw from, and view statements for bank accounts stored in an ArrayList.
 *
 * @author Nikolas Baker
 * @version 1.0
 * @date 2026-04-29
 */
 
import java.util.ArrayList;
import java.util.Scanner;
 
/**
 * Entry point and controller for the Bank Account Manager application.
 * All accounts are stored in a static ArrayList.
 */
public class BankAccountApp {
 
    /** Stores all active bank accounts. */
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
 
    /** Shared scanner for reading user input throughout the application. */
    private static Scanner scanner = new Scanner(System.in);
 
    /**
     * Main method — launches the Bank Account Manager menu loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println();
        System.out.println("======================================");
        System.out.println("=      BANK ACCOUNT MANAGER v1.0      =");
        System.out.println("======================================");
 
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = getIntInput("Enter your choice: ");
 
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    editAccount();
                    break;
                case 3:
                    removeAccount();
                    break;
                case 4:
                    depositToAccount();
                    break;
                case 5:
                    withdrawFromAccount();
                    break;
                case 6:
                    viewStatement();
                    break;
                case 7:
                    listAllAccounts();
                    break;
                case 8:
                    System.out.println();
                    System.out.println("Thank you for using Bank Account Manager. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 8.");
            }
        }
        scanner.close();
    }
 
    /**
     * Prints the main menu options to the console.
     */
    private static void printMainMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("               MAIN MENU");
        System.out.println("========================================");
        System.out.println("  1. Create New Account");
        System.out.println("  2. Edit Account");
        System.out.println("  3. Remove Account");
        System.out.println("  4. Deposit");
        System.out.println("  5. Withdraw");
        System.out.println("  6. View Account Statement");
        System.out.println("  7. List All Accounts");
        System.out.println("  8. Exit");
        System.out.println("----------------------------------------");
    }
 
    /**
     * Prompts the user to create a new BankAccount and adds it to the accounts list.
     */
    private static void createAccount() {
        System.out.println("\n--- Create New Account ---");
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine().trim();
 
        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty.");
            return;
        }
 
        double initialDeposit = getDoubleInput("Enter initial deposit: $");
 
        try {
            BankAccount account = new BankAccount(name, initialDeposit);
            accounts.add(account);
            System.out.println();
            System.out.println("Account created successfully!");
            System.out.println("  Account Number: " + account.getAccountNumber());
            System.out.println("  Owner: " + account.getOwnerName());
            System.out.printf("  Opening Balance: $%.2f%n", account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }
 
    private static void editAccount() {
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts available to edit.");
            return;
        }
 
        System.out.println("\n--- Edit Account ---");
        listAllAccounts();
 
        int accountNum = getIntInput("Enter account number to edit: ");
        BankAccount account = findAccount(accountNum);
 
        if (account == null) {
            System.out.println("Error: Account #" + accountNum + " not found.");
            return;
        }
 
        System.out.println("Current name: " + account.getOwnerName());
        System.out.print("Enter new owner name: ");
        String newName = scanner.nextLine().trim();
 
        if (newName.isEmpty()) {
            System.out.println("Error: Name cannot be empty. No changes made.");
            return;
        }
 
        account.setOwnerName(newName);
        System.out.println("Account #" + accountNum + " updated successfully. New name: " + newName);
    }
 
    private static void removeAccount() {
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts available to remove.");
            return;
        }
 
        System.out.println("\n--- Remove Account ---");
        listAllAccounts();
 
        int accountNum = getIntInput("Enter account number to remove: ");
        BankAccount account = findAccount(accountNum);
 
        if (account == null) {
            System.out.println("Error: Account #" + accountNum + " not found.");
            return;
        }
 
        System.out.print("Are you sure you want to remove account #" + accountNum
                + " (" + account.getOwnerName() + ")? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
 
        if (confirm.equals("yes")) {
            accounts.remove(account);
            System.out.println("Account #" + accountNum + " removed successfully.");
        } else {
            System.out.println("Removal cancelled.");
        }
    }
 
    private static void depositToAccount() {
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts available.");
            return;
        }
 
        System.out.println("\n--- Deposit ---");
        listAllAccounts();
 
        int accountNum = getIntInput("Enter account number: ");
        BankAccount account = findAccount(accountNum);
 
        if (account == null) {
            System.out.println("Error: Account #" + accountNum + " not found.");
            return;
        }
 
        double amount = getDoubleInput("Enter deposit amount: $");
 
        try {
            account.deposit(amount);
            System.out.printf("Deposit successful! New balance: $%.2f%n", account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
 
    private static void withdrawFromAccount() {
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts available.");
            return;
        }
 
        System.out.println("\n--- Withdraw ---");
        listAllAccounts();
 
        int accountNum = getIntInput("Enter account number: ");
        BankAccount account = findAccount(accountNum);
 
        if (account == null) {
            System.out.println("Error: Account #" + accountNum + " not found.");
            return;
        }
 
        double amount = getDoubleInput("Enter withdrawal amount: $");
 
        try {
            account.withdraw(amount);
            System.out.printf("Withdrawal successful! New balance: $%.2f%n", account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
 
    private static void viewStatement() {
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts available.");
            return;
        }
 
        System.out.println("\n--- View Statement ---");
        listAllAccounts();
 
        int accountNum = getIntInput("Enter account number: ");
        BankAccount account = findAccount(accountNum);
 
        if (account == null) {
            System.out.println("Error: Account #" + accountNum + " not found.");
            return;
        }
 
        System.out.println(account.generateStatement());
    }
 
    private static void listAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts found.");
            return;
        }
        System.out.println("\n--- All Accounts (" + accounts.size() + ") ---");
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }
 
    private static BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
 
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
 
    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid dollar amount.");
            }
        }
    }
}
