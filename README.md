Bank Account Manager
Author: Nikolas Baker
Course: CS (Java)
Date: April 2026

Description
Bank Account Manager is a Java application that allows users to manage multiple bank accounts through a graphical interface. Users can create accounts, deposit and withdraw money, view full transaction histories, edit account details, and remove accounts. Account numbers are automatically generated using a static counter, and all accounts are stored in an ArrayList. The application uses exception handling throughout to catch invalid inputs and prevent errors like overdrafts or negative deposits.

Project Structure
BankAccountManager/
├── BankAccount.java       # Account class — stores data and handles transactions
├── BankAccountGUI.java    # Swing GUI — main window and all user interactions
├── BankAccountApp.java    # Text-based version of the app (console menu)
└── README.md              # This file

How to Use
Requirements

Java JDK 8 or higher

Compile and Run (GUI)
bashjavac BankAccount.java BankAccountGUI.java
java BankAccountGUI
Compile and Run (Console version)
bashjavac BankAccount.java BankAccountApp.java
java BankAccountApp

Features

Create Account — Enter a name and opening balance; a unique account number is assigned automatically
Edit Account — Update the owner name of any existing account
Remove Account — Delete an account with a confirmation prompt
Deposit — Add funds to a selected account
Withdraw — Remove funds with overdraft protection
View Statement — See a full transaction history for any account
List All Accounts — See every account at a glance in the sidebar


Screenshots

(Add screenshots of your running application here)


Key Concepts Used

ArrayList for storing multiple account objects
Static variable (nextAccountNumber) for unique ID generation
Exception handling (try/catch, IllegalArgumentException) for input validation
Java Swing (JFrame, JList, JTextArea, JButton, JOptionPane) for the GUI
Javadoc documentation on all classes and methods