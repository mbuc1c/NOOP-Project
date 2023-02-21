package com.matejbucic.CashRegisterApp.view.login_form;

import com.matejbucic.CashRegisterApp.controller.Controller;
import com.matejbucic.CashRegisterApp.model.listeners.LoginFormListener;
import com.matejbucic.CashRegisterApp.view.login_form.panel.InputPanel;
import com.matejbucic.CashRegisterApp.view.login_form.panel.KeyBoardPanel;
import com.matejbucic.CashRegisterApp.view.register_form.RegisterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame {

    private InputPanel inputPanel;
    private KeyBoardPanel keyBoardPanel;
    private Controller controller;

    public LoginFrame() {
        super("Login");
        initComponents();
        layoutComponents();
        activatePanels();
        setShortcut();

        setVisible(true);
        setSize(400, 650);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setShortcut() {
        // Set up the shortcut
        getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK), "newFrame");
        getRootPane().getActionMap().put("newFrame", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Create a new frame
                dispose();
                RegisterFrame registerFrame = new RegisterFrame();
                registerFrame.setLocation(getX() + 25, getY() + 25);
                registerFrame.setVisible(true);
            }
        });
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
