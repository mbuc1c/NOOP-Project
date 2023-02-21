package com.matejbucic.CashRegisterApp;

import com.matejbucic.CashRegisterApp.model.Database;
import com.matejbucic.CashRegisterApp.model.Waiter;
import com.matejbucic.CashRegisterApp.view.login_form.LoginFrame;

import javax.swing.*;
import java.util.ArrayList;

import static com.matejbucic.CashRegisterApp.model.Database.waiters;

public class App {
    public static void main(String[] args) {
        generateWaiters();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }
        });
    }

    private static void generateWaiters() {
        waiters = new ArrayList<>();
        waiters.add(new Waiter("Frane", "Franic", "1111"));
        waiters.add(new Waiter("Krone", "Cicanocic", "2222"));
        waiters.add(new Waiter("Alojzije", "Cetkovic", "1234"));
    }
}
