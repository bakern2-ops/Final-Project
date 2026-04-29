/**
 * BankAccountGUI.java
 *
 * Description: Graphical User Interface for the Bank Account Manager application.
 * Allows users to create, edit, remove, deposit, withdraw, and view statements
 * for multiple bank accounts stored in an ArrayList. Built with Java Swing.
 *
 * @author Nikolas Baker
 * @version 1.0
 * @date 2026-04-29
 */
 
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
 
public class BankAccountGUI extends JFrame {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private DefaultListModel<String> listModel;
    private JList<String> accountList;
    private JTextArea outputArea;
    private JLabel statusLabel;
 
    private static final Color DARK_BG     = new Color(30, 30, 40);
    private static final Color PANEL_BG    = new Color(40, 42, 54);
    private static final Color BUTTON_BG   = new Color(68, 71, 90);
    private static final Color ACCENT      = new Color(80, 160, 240);
    private static final Color TEXT_WHITE  = new Color(230, 230, 230);
    private static final Color TEXT_DIM    = new Color(160, 160, 180);
    private static final Color SUCCESS     = new Color(80, 200, 120);
    private static final Color DANGER      = new Color(220, 80, 80);
 
    public BankAccountGUI() {
        setTitle("Bank Account Manager");
        setSize(820, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(DARK_BG);
        initComponents();
        setVisible(true);
    }
 
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(DARK_BG);
        mainPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainPanel.add(buildTitlePanel(),   BorderLayout.NORTH);
        mainPanel.add(buildAccountPanel(), BorderLayout.WEST);
        mainPanel.add(buildOutputPanel(),  BorderLayout.CENTER);
        mainPanel.add(buildButtonPanel(),  BorderLayout.EAST);
        mainPanel.add(buildStatusBar(),    BorderLayout.SOUTH);
        add(mainPanel);
    }
 
    private JPanel buildTitlePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(PANEL_BG);
        panel.setBorder(new EmptyBorder(8, 12, 8, 12));
        JLabel title = new JLabel("  BANK ACCOUNT MANAGER");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(ACCENT);
        panel.add(title);
        return panel;
    }
 
    private JPanel buildAccountPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PANEL_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BUTTON_BG, 1), new EmptyBorder(8, 8, 8, 8)));
        panel.setPreferredSize(new Dimension(220, 0));
        JLabel label = new JLabel("Accounts");
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(TEXT_DIM);
        label.setBorder(new EmptyBorder(0, 0, 6, 0));
        panel.add(label, BorderLayout.NORTH);
        listModel = new DefaultListModel<>();
        accountList = new JList<>(listModel);
        accountList.setBackground(DARK_BG);
        accountList.setForeground(TEXT_WHITE);
        accountList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        accountList.setSelectionBackground(ACCENT);
        accountList.setSelectionForeground(Color.WHITE);
        accountList.setFixedCellHeight(32);
        accountList.setBorder(new EmptyBorder(4, 6, 4, 6));
        JScrollPane scroll = new JScrollPane(accountList);
        scroll.setBorder(new LineBorder(BUTTON_BG, 1));
        scroll.getViewport().setBackground(DARK_BG);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
 
    private JPanel buildOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PANEL_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BUTTON_BG, 1), new EmptyBorder(8, 8, 8, 8)));
        JLabel label = new JLabel("Output");
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(TEXT_DIM);
        label.setBorder(new EmptyBorder(0, 0, 6, 0));
        panel.add(label, BorderLayout.NORTH);
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(DARK_BG);
        outputArea.setForeground(TEXT_WHITE);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setCaretColor(TEXT_WHITE);
        outputArea.setBorder(new EmptyBorder(6, 8, 6, 8));
        outputArea.setText("  Welcome to Bank Account Manager.\n  Select an action from the menu.\n");
        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBorder(new LineBorder(BUTTON_BG, 1));
        scroll.getViewport().setBackground(DARK_BG);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
 
    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 0, 8));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(0, 8, 0, 0));
        panel.setPreferredSize(new Dimension(150, 0));
        panel.add(makeButton("Create Account",  SUCCESS,    e -> createAccount()));
        panel.add(makeButton("Edit Account",    ACCENT,     e -> editAccount()));
        panel.add(makeButton("Remove Account",  DANGER,     e -> removeAccount()));
        panel.add(makeSeparator());
        panel.add(makeButton("Deposit",         SUCCESS,    e -> depositToAccount()));
        panel.add(makeButton("Withdraw",        new Color(230, 160, 50), e -> withdrawFromAccount()));
        panel.add(makeButton("View Statement",  ACCENT,     e -> viewStatement()));
        return panel;
    }
 
    private JPanel buildStatusBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        panel.setBackground(PANEL_BG);
        statusLabel = new JLabel("No accounts yet.");
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusLabel.setForeground(TEXT_DIM);
        panel.add(statusLabel);
        return panel;
    }
 
    private JButton makeButton(String text, Color color, ActionListener listener) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 10, 8, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(listener);
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(color.brighter());
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(color);
            }
        });
        return btn;
    }
 
    private JPanel makeSeparator() {
        JPanel sep = new JPanel();
        sep.setBackground(BUTTON_BG);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        return sep;
    }
 
    private void refreshList() {
        listModel.clear();
        for (BankAccount acct : accounts) {
            listModel.addElement(String.format("#%d  %s", acct.getAccountNumber(), acct.getOwnerName()));
        }
        int count = accounts.size();
        statusLabel.setText(count == 0 ? "No accounts yet."
                : count + " account" + (count == 1 ? "" : "s") + " on file.");
    }
 
    private void printOutput(String message) {
        outputArea.setText(message);
    }
 
    private BankAccount getSelectedAccount() {
        int index = accountList.getSelectedIndex();
        if (index < 0 || index >= accounts.size()) return null;
        return accounts.get(index);
    }
 
    private void createAccount() {
        String name = JOptionPane.showInputDialog(this,
                "Enter account holder name:", "Create Account",
                JOptionPane.PLAIN_MESSAGE);
        if (name == null || name.trim().isEmpty()) return;
        String amtStr = JOptionPane.showInputDialog(this,
                "Enter initial deposit ($):", "Create Account",
                JOptionPane.PLAIN_MESSAGE);
        if (amtStr == null) return;
        try {
            double amount = Double.parseDouble(amtStr.trim());
            BankAccount account = new BankAccount(name.trim(), amount);
            accounts.add(account);
            refreshList();
            printOutput(String.format(
                    "  Account created successfully!%n%n"
                    + "  Account Number : %d%n"
                    + "  Owner          : %s%n"
                    + "  Opening Balance: $%.2f%n",
                    account.getAccountNumber(), account.getOwnerName(), account.getBalance()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    private void editAccount() {
        BankAccount account = getSelectedAccount();
        if (account == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select an account from the list.", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        String newName = (String) JOptionPane.showInputDialog(this,
                "Edit owner name:", "Edit Account",
                JOptionPane.PLAIN_MESSAGE, null, null, account.getOwnerName());
        if (newName == null || newName.trim().isEmpty()) return;
        account.setOwnerName(newName.trim());
        refreshList();
        printOutput("  Account #" + account.getAccountNumber()
                + " updated.%n  New owner name: " + account.getOwnerName());
    }
 
    private void removeAccount() {
        BankAccount account = getSelectedAccount();
        if (account == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select an account from the list.", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Remove account #" + account.getAccountNumber()
                        + " (" + account.getOwnerName() + ")?",
                "Confirm Remove", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            accounts.remove(account);
            refreshList();
            printOutput("  Account #" + account.getAccountNumber() + " removed.");
        }
    }
 
    private void depositToAccount() {
        BankAccount account = getSelectedAccount();
        if (account == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select an account from the list.", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        String amtStr = JOptionPane.showInputDialog(this,
                "Enter deposit amount ($):", "Deposit",
                JOptionPane.PLAIN_MESSAGE);
        if (amtStr == null) return;
        try {
            double amount = Double.parseDouble(amtStr.trim());
            account.deposit(amount);
            refreshList();
            printOutput(String.format(
                    "  Deposit successful!%n%n"
                    + "  Account : #%d (%s)%n"
                    + "  Deposited: $%.2f%n"
                    + "  New Balance: $%.2f%n",
                    account.getAccountNumber(), account.getOwnerName(),
                    amount, account.getBalance()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    private void withdrawFromAccount() {
        BankAccount account = getSelectedAccount();
        if (account == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select an account from the list.", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        String amtStr = JOptionPane.showInputDialog(this,
                "Enter withdrawal amount ($):", "Withdraw",
                JOptionPane.PLAIN_MESSAGE);
        if (amtStr == null) return;
        try {
            double amount = Double.parseDouble(amtStr.trim());
            account.withdraw(amount);
            refreshList();
            printOutput(String.format(
                    "  Withdrawal successful!%n%n"
                    + "  Account    : #%d (%s)%n"
                    + "  Withdrawn  : $%.2f%n"
                    + "  New Balance: $%.2f%n",
                    account.getAccountNumber(), account.getOwnerName(),
                    amount, account.getBalance()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    private void viewStatement() {
        BankAccount account = getSelectedAccount();
        if (account == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select an account from the list.", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        printOutput(account.generateStatement());
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankAccountGUI());
    }
}
