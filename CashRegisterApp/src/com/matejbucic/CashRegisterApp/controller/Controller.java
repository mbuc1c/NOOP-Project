package com.matejbucic.CashRegisterApp.controller;

import com.matejbucic.CashRegisterApp.model.Bill;
import com.matejbucic.CashRegisterApp.model.Database;
import com.matejbucic.CashRegisterApp.model.Waiter;
import com.matejbucic.CashRegisterApp.model.drinks.Drink;
import com.matejbucic.CashRegisterApp.view.all_bills.AllBillsFrame;
import com.matejbucic.CashRegisterApp.view.cash_register.CashRegisterFrame;
import com.matejbucic.CashRegisterApp.view.login_form.LoginFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Set;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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

    public boolean isLoginValid(String password) throws SQLException {
        // currently for testing purposes
        if (!database.checkIfValid(password)) {
            JOptionPane.showMessageDialog(null, "Incorrect password!");
            return false;
        }
        return true;
    }

    public Waiter getWaiterWithPassword(String password) throws SQLException {
        return database.getWaiterWithPassword(password);
    }

    public boolean checkIsRegistrationValid(String name, String surname, String password, String repPassword) throws SQLException {
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

        } else if (database.getWaiterWithPassword(password).getPassword() != null) {
            JOptionPane.showMessageDialog(null, "Waiter with that password already exists", "Warning!", JOptionPane.WARNING_MESSAGE);
            return false;

        } else return true;
    }

    private boolean isPasswordOnlyDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void addWaiter(Waiter waiter) throws SQLException {
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

    public void addDrinkToBill(Drink drink, Bill bill) {
        if (!bill.getDrinks().containsKey(drink)) {
            bill.getDrinks().put(drink, 1);
        } else {
            bill.getDrinks().put(drink, bill.getDrinks().get(drink) + 1);
        }

        double totalPrice = bill.getTotalPrice() + drink.getDrinkPrice();
        bill.setTotalPrice(totalPrice);
    }

    public void removeDrink(Drink drink, Bill bill) {
        if (bill.getDrinks().get(drink) == 1) {
            bill.getDrinks().remove(drink);
        } else {
            bill.getDrinks().put(drink, bill.getDrinks().get(drink) - 1);
        }

        double totalPrice = bill.getTotalPrice() - drink.getDrinkPrice();
        bill.setTotalPrice(totalPrice);
    }

    public void clearAll(Bill bill) {
        bill.getDrinks().clear();
        bill.setTotalPrice(0.0);
    }

    public void updateBillTable(Bill bill, DefaultTableModel model, JLabel total) {
        String[] columnIdentifiers = new String[model.getColumnCount()];
        String[][] billData = new String[bill.getDrinks().size()][model.getColumnCount()];

        for (int i = 0; i < columnIdentifiers.length; i++) {
            columnIdentifiers[i] = model.getColumnName(i);
        }

        int r = 0;
        for (Drink drink : bill.getDrinks().keySet()) {
            int amount = bill.getDrinks().get(drink);
            double drinkPrice = drink.getDrinkPrice();

            billData[r][0] = drink.getClass().getSimpleName();
            billData[r][1] = String.valueOf(amount);
            billData[r][2] = String.valueOf(drinkPrice);
            billData[r][3] = String.valueOf(drinkPrice * amount);
            r++;
        }

        model.setDataVector(billData, columnIdentifiers);
        total.setText("Total: " + String.format("%.2f", bill.getTotalPrice()) + " \u20AC");
    }

    public Drink getSelectedDrink(int selectedRow, Bill bill) {
        Set<Drink> keySet = bill.getDrinks().keySet();
        Drink[] keyArray = keySet.toArray(new Drink[keySet.size()]);
        return keyArray[selectedRow];
    }

    // ref: https://www.javatpoint.com/java-create-pdf
    public void checkoutBill(Bill bill, Waiter waiter) {
        bill.setWaiter(waiter);
        bill.setDate(new Timestamp(System.currentTimeMillis()));
        String fileName = waiter.getName() + waiter.getSurname() + "-" + bill.getDate().getTime();

        //created PDF document instance
        Document document = new Document();

        try {
            //generate a PDF at the specified location
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("bill_log\\" + fileName + ".pdf"));

            //opens the PDF
            document.open();

            //adds paragraph to the PDF file
            document.add(new Paragraph(bill.toString()));

            //close the PDF file
            document.close();
            //closes the writer
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            database.addBillToDB(bill);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        new LoginFrame();
    }

    public void openAllBills(Waiter waiter) {
        AllBillsFrame frame = new AllBillsFrame();
        frame.setWaiter(waiter);
        frame.setTitle("All bills - " + waiter.getName() + " " + waiter.getSurname());
        try {
            frame.getAllBillsTablePanel().getTable().setModel(database.getAllBillsTableModel(waiter));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
