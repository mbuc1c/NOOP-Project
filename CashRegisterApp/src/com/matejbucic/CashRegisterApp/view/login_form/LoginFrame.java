package com.matejbucic.CashRegisterApp.view.login_form;

import com.matejbucic.CashRegisterApp.controller.Controller;
import com.matejbucic.CashRegisterApp.model.listeners.LoginFormListener;
import com.matejbucic.CashRegisterApp.view.login_form.panel.InputPanel;
import com.matejbucic.CashRegisterApp.view.login_form.panel.KeyBoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class LoginFrame extends JFrame {

    private InputPanel inputPanel;
    private KeyBoardPanel keyBoardPanel;
    private Controller controller;

    public LoginFrame() {
        super("Login");
        initComponents();
        layoutComponents();
        activatePanels();

        setVisible(true);
        setSize(400, 650);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void activatePanels() {
        keyBoardPanel.setListener(new LoginFormListener() {
            @Override
            public void numBtnPressed(ActionEvent e) {
                controller.updatePasswordInput((JButton) e.getSource(), inputPanel.getPasswordField());
            }

            @Override
            public void clear() {
                controller.clearPasswordInput(inputPanel.getPasswordField());
            }

            @Override
            public void submit() {
                String password = new String(inputPanel.getPasswordField().getPassword());
                if (controller.loginValid(password)) {
                    System.out.println(password);
                    JOptionPane.showMessageDialog(null, "Login Success!");
                } else {
                    clear();
                    JOptionPane.showMessageDialog(null, "Login Fail!");
                }
            }
        });
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(inputPanel, BorderLayout.NORTH);
        add(keyBoardPanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        inputPanel = new InputPanel();
        keyBoardPanel = new KeyBoardPanel();

        controller = new Controller();
    }
}
