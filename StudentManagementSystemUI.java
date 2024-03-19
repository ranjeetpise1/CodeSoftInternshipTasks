// Name: RanJeet Pise

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentManagementSystemUI extends JFrame {
    private StudentManagementSystem system;
    private JTextField nameField, rollNumberField, gradeField;
    private JTextArea outputArea;

    public StudentManagementSystemUI(StudentManagementSystem system) {
        this.system = system;
        setTitle("Student Management System");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll Number:"));
        rollNumberField = new JTextField();
        inputPanel.add(rollNumberField);
        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                String grade = gradeField.getText();
                system.addStudent(new Student(name, rollNumber, grade));
                clearFields();
                outputArea.setText("Student added successfully.");
            }
        });

        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
                for (Student student : system.getStudents()) {
                    outputArea.append(student.toString() + "\n");
                }
            }
        });

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(outputArea, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        StudentManagementSystemUI ui = new StudentManagementSystemUI(system);
        ui.setVisible(true);
    }
}
