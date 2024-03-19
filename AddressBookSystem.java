// Name: RanJeet Pise

package Task5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Contact implements java.io.Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPhone: " + phoneNumber + "\nEmail: " + emailAddress;
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }
}

public class AddressBookSystem {
    private static AddressBook addressBook = new AddressBook();
    private static JFrame frame;
    private static JTextArea textArea;
    private static JTextField nameField;
    private static JTextField phoneField;
    private static JTextField emailField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Address Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JPanel panel = new JPanel(new BorderLayout());
    
        textArea = new JTextArea(20, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name: "));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone: "));
        phoneField = new JTextField();
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Email: "));
        emailField = new JTextField();
        inputPanel.add(emailField);
    
        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(e -> addContact());
        inputPanel.add(addButton);
    
        JButton displayButton = new JButton("Display All Contacts");
        displayButton.addActionListener(e -> displayAllContacts());
        inputPanel.add(displayButton);
    
        JButton searchButton = new JButton("Search Contact");
        searchButton.addActionListener(e -> searchContact());
        inputPanel.add(searchButton);
    
        JButton removeButton = new JButton("Remove Contact");
        removeButton.addActionListener(e -> removeContact());
        inputPanel.add(removeButton);
    
        panel.add(inputPanel, BorderLayout.SOUTH);
    
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    

    private static void addContact() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            Contact contact = new Contact(name, phone, email);
            addressBook.addContact(contact);
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");
            JOptionPane.showMessageDialog(frame, "Contact added successfully.");
        } else {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
        }
    }

    private static void displayAllContacts() {
        List<Contact> contacts = addressBook.getAllContacts();
        if (!contacts.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Contact contact : contacts) {
                sb.append(contact.toString()).append("\n\n");
            }
            textArea.setText(sb.toString());
        } else {
            textArea.setText("No contacts to display.");
        }
    }

    private static void searchContact() {
        String name = nameField.getText();
        if (!name.isEmpty()) {
            Contact contact = addressBook.searchContact(name);
            if (contact != null) {
                textArea.setText(contact.toString());
            } else {
                textArea.setText("Contact not found.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a name to search.");
        }
    }

    private static void removeContact() {
        String name = nameField.getText();
        if (!name.isEmpty()) {
            Contact contact = addressBook.searchContact(name);
            if (contact != null) {
                addressBook.removeContact(contact);
                textArea.setText("Contact removed successfully.");
            } else {
                textArea.setText("Contact not found.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a name to remove.");
        }
    }
}

