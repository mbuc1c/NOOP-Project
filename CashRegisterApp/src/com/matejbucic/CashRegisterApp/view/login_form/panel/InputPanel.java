package com.matejbucic.CashRegisterApp.view.login_form.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class InputPanel extends JPanel {

    private JPasswordField passwordField;

    public InputPanel() {
        initComponents();
        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(passwordField, BorderLayout.CENTER);
    }

    private void initComponents() {
        passwordField = new JPasswordField();
        passwordField.setSize(400,50);
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 36));
        passwordField.setEditable(false);
        passwordField.setVisible(true);
    }
}
