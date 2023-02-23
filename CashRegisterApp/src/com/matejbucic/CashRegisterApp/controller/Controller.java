package com.matejbucic.CashRegisterApp.controller;

import com.matejbucic.CashRegisterApp.model.Bill;
import com.matejbucic.CashRegisterApp.model.Database;
import com.matejbucic.CashRegisterApp.model.Waiter;
import com.matejbucic.CashRegisterApp.model.drinks.Drink;
import com.matejbucic.CashRegisterApp.view.cash_register.CashRegisterFrame;
import com.matejbucic.CashRegisterApp.view.login_form.LoginFrame;

import javax.swing.*;
import java.util.HashMap;

public class Controller {

    private final Database database;

    public Controller() {
        database = new Database();
    }
    public void updatePasswordInput(JButton button, JPasswordField passwordField) {
        String password = new String(passwordField.getPassword());
        password += button.getText();
        passwordField.setText(password);
    }

    public void clearPasswordInput(JPasswordField passwordField) {
        passwordField.setText("");
    }

    public boolean isLoginValid(String password) {
        // currently for testing purposes
        if (!database.checkIfValid(password)) {
            JOptionPane.showMessageDialog(null, "Incorrect password!");
            return false;
        }
        return true;
    }

    public Waiter getWaiterWithPassword(String password) {
        return database.getWaiterWithPassword(password);
    }

    public boolean checkIsRegistrationValid(String name, String surname, String password, String repPassword) {
        if (name.equals("") || surname.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "All fields must be filled!", "Warning!", JOptionPane.WARNING_MESSAGE);
            return false;

        } else if (password.length() != 4) {
            JOptionPane.showMessageDialog(null, "Password length is invalid (4 digits)", "Warning!", JOptionPane.WARNING_MESSAGE);
            return false;

        } else if (!isPasswordOnlyDigits(password)) {
            JOptionPane.showMessageDialog(null, "Password must be made out of digits only!", "Warning!", JOptionPane.WARNING_MESSAGE);
            return false;

        } else if (!password.equals(repPassword)) {
            JOptionPane.showMessageDialog(null, "Repeated password does not match first inputted password!", "Warning!", JOptionPane.WARNING_MESSAGE);
            return false;

        } else if (database.getWaiterWithPassword(password) != null) {
            JOptionPane.showMessageDialog(null, "Waiter with that password already exists", "Warning!", JOptionPane.WARNING_MESSAGE);
            return false;

        } else return true;
    }

    private static boolean isPasswordOnlyDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void addWaiter(Waiter waiter) {
        database.addWaiterToDatabase(waiter);
    }

    public void openCashRegister(Waiter waiter) {
        CashRegisterFrame cashRegisterFrame = new CashRegisterFrame();
        cashRegisterFrame.setWaiter(waiter);
        cashRegisterFrame.getInformationPanel().getWaiter()
                .setText(waiter.getName() + " " + waiter.getSurname() + " (id: " + waiter.getId() + ")");
    }

    public void logOut() {
        new LoginFrame();
    }

    public void addDrinkToBill(Waiter waiter, Drink drink, Bill bill, JLabel total) {
        if (bill.getWaiter() == null) {
            bill.setWaiter(waiter);
        }
        if (!bill.getDrinks().containsKey(drink)) {
            bill.getDrinks().put(drink, 1);
        } else {
            bill.getDrinks().put(drink, bill.getDrinks().get(drink) + 1);
        }

        double totalPrice = bill.getTotalPrice() + drink.getDrinkPrice();
        bill.setTotalPrice(totalPrice);

        total.setText("Total: " + bill.getTotalPrice() + " \u20AC");
    }
}
