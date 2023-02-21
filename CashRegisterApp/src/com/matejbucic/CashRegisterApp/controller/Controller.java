package com.matejbucic.CashRegisterApp.controller;

import javax.swing.*;

public class Controller {
    public void updatePasswordInput(JButton button, JPasswordField passwordField) {
        String password = new String(passwordField.getPassword());
        password += button.getText();
        passwordField.setText(password);
    }

    public void clearPasswordInput(JPasswordField passwordField) {
        passwordField.setText("");
    }

    public boolean loginValid(String password) {
        // currently for testing purposes
        if (password.length() == 4 && password.equals("1111")) return true;
        return false;
    }
}
