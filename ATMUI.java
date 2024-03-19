// Name: RanJeet Pise

package Task4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public boolean withdraw(double amount) {
        return account.withdraw(amount);
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public double checkBalance() {
        return account.getBalance();
    }
}

public class ATMUI extends JFrame {
    private ATM atm;
    private JTextField amountField;
    private JTextArea messageArea;

    public ATMUI(ATM atm) {
        this.atm = atm;
        setTitle("ATM Machine");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        panel.add(amountField);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (atm.withdraw(amount)) {
                    messageArea.setText("Withdrawal successful. New balance: $" + atm.checkBalance());
                } else {
                    messageArea.setText("Insufficient balance.");
                }
            }
        });
        panel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                atm.deposit(amount);
                messageArea.setText("Deposit successful. New balance: $" + atm.checkBalance());
            }
        });
        panel.add(depositButton);

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double balance = atm.checkBalance();
                messageArea.setText("Your balance: $" + balance);
            }
        });
        panel.add(balanceButton);

        messageArea = new JTextArea();
        messageArea.setEditable(false);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.NORTH);
        contentPane.add(messageArea, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance $1000
        ATM atm = new ATM(account);
        ATMUI atmUI = new ATMUI(atm);
        atmUI.setVisible(true);
    }
}
