package com.matejbucic.CashRegisterApp.view.register_form.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class DataInputPanel extends JPanel {

    private JTextField nameField, surnameField;
    private JPasswordField passwordField, repeatPasswordField;
    private JLabel nameLabel, surnameLabel, passwordLabel, repeatPasswordLabel, instructionLabel;

    public DataInputPanel() {
        initComponents();
        layoutComponents();

        setPreferredSize(new Dimension(750, 650));

    }

    private void layoutComponents() {
        // Add the components to the panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 15, 0);
        add(instructionLabel, gbc);

        gbc.gridy++;
        gbc.weighty = 0.1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 0, 1, 5);
        add(nameLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(nameField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 0, 1, 5);
        add(surnameLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(surnameField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 0, 1, 5);
        add(passwordLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(5, 0, 5, 10);
        add(passwordField, gbc);

        gbc.gridx++;
        gbc.gridy--;
        gbc.insets = new Insets(5, 5, 1, 0);
        add(repeatPasswordLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(repeatPasswordField, gbc);
    }

    private void initComponents() {
        // Set up instruction label
        instructionLabel = new JLabel("Input below data of new waiter:");
        instructionLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 24));

        // Set up the name field
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(300, 30)); // Set preferred size of text field
        Font font = new Font(nameLabel.getFont().getName(), Font.PLAIN, 18);
        nameLabel.setFont(font); // Increase font size of label
        nameField.setFont(font); // Increase font size of field

        // Set up the surname field
        surnameLabel = new JLabel("Surname:");
        surnameField = new JTextField(20);
        surnameField.setPreferredSize(new Dimension(300, 30)); // Set preferred size of text field
        surnameLabel.setFont(font); // Increase font size
        surnameField.setFont(font); // Increase font size of field

        // Set up the password field
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(300, 30)); // Set preferred size of text field
        passwordLabel.setFont(font); // Increase font size of label
        passwordField.setFont(font); // Increase font size of field

        // Set up the repeat password field
        repeatPasswordLabel = new JLabel("Repeat Password:");
        repeatPasswordField = new JPasswordField(15);
        repeatPasswordField.setPreferredSize(new Dimension(300, 30)); // Set preferred size of text field
        repeatPasswordLabel.setFont(font); // Increase font size of label
        repeatPasswordField.setFont(font); // Increase font size of field
    }

}
