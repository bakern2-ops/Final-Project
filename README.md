# Bank Account Manager

**Author:** Nikolas Baker

**Course:** CS (Java)

**Date:** April 2026

## Description

Bank Account Manager is a Java application that lets users manage multiple bank accounts. The program supports:

- creating new accounts
- editing account owner names
- removing accounts
- depositing funds
- withdrawing funds with overdraft protection
- generating full transaction statements
- displaying a list of all accounts

The application stores accounts in an `ArrayList` and uses a static variable to generate unique account numbers. It also uses exception handling to validate input and prevent invalid operations such as negative deposits or overdrafts.

## Project Structure

```
Bank Account Manager/
├── src/
│   ├── BankAccount.java       # Account class — stores data and handles transactions
│   ├── BankAccountApp.java    # Console version of the app (text menu)
│   └── BankAccountGUI.java    # Swing GUI version of the app
└── README.md                  # This file
```

## Requirements

- Java JDK 8 or higher

## Compile and Run

### GUI version

```bash
cd "Cis 254/Final Project/src"
javac BankAccount.java BankAccountGUI.java
java BankAccountGUI
```

### Console version

```bash
cd "Cis 254/Final Project/src"
javac BankAccount.java BankAccountApp.java
java BankAccountApp
```

## Features

- Create Account — enter a name and opening balance; a unique account number is assigned automatically
- Edit Account — update the owner name of an existing account
- Remove Account — delete an account with confirmation
- Deposit — add funds to a selected account
- Withdraw — remove funds from an account with overdraft protection
- View Statement — show full transaction history for an account
- List All Accounts — display all saved accounts

## Screenshots

Add screenshots of the running GUI or console app here.

## Key Concepts Used

- `ArrayList` for storing multiple `BankAccount` objects
- static variable for generating unique account numbers
- exception handling with `try/catch` and `IllegalArgumentException`
- Java Swing for the graphical interface
- Javadoc documentation for classes and methods

## Notes

Remove any `.class` files from the repository before submitting. Only source files (`.java`) and documentation should be included.
