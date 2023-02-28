package com.matejbucic.CashRegisterApp;

import com.matejbucic.CashRegisterApp.model.Waiter;
import com.matejbucic.CashRegisterApp.view.all_bills.AllBillsFrame;
import com.matejbucic.CashRegisterApp.view.all_bills.panel.AllBillsTablePanel;
import com.matejbucic.CashRegisterApp.view.login_form.LoginFrame;

import javax.swing.*;


public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }
        });
    }
}
