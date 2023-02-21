package com.matejbucic.CashRegisterApp.view.register_form;

import com.matejbucic.CashRegisterApp.controller.Controller;
import com.matejbucic.CashRegisterApp.model.listeners.RegisterFormListener;
import com.matejbucic.CashRegisterApp.view.login_form.LoginFrame;
import com.matejbucic.CashRegisterApp.view.register_form.panel.DataInputPanel;
import com.matejbucic.CashRegisterApp.view.register_form.panel.RegisterButtonsPanel;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private DataInputPanel dataInputPanel;
    private RegisterButtonsPanel registerButtonsPanel;
    private Controller controller;

    public RegisterFrame() {
        super("Register");
        initComponents();
        layoutComponents();
        activatePanels();

        setVisible(true);
        setSize(650, 450);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void activatePanels() {
        registerButtonsPanel.setListener(new RegisterFormListener() {
            @Override
            public void cancel() {
                dispose();
                new LoginFrame();
            }

            @Override
            public void submit() {
                JOptionPane.showMessageDialog(null, "Submit button pressed!");
            }
        });
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(dataInputPanel, BorderLayout.CENTER);
        add(registerButtonsPanel, BorderLayout.SOUTH);
    }

    private void initComponents() {
        dataInputPanel = new DataInputPanel();
        registerButtonsPanel = new RegisterButtonsPanel();

        controller = new Controller();
    }
}
