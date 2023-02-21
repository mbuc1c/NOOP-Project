package com.matejbucic.CashRegisterApp;

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
