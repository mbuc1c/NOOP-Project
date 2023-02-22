package com.matejbucic.CashRegisterApp.view.cash_register.panel;

import com.matejbucic.CashRegisterApp.model.listeners.InformationPanelListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class InformationPanel extends JPanel {

    private JLabel waiter;
    private JButton logOut, allBills;

    private InformationPanelListener listener;

    public InformationPanel() {
        initComponents();
        layoutComponents();
        activateButtons();
    }

    private void activateButtons() {
        logOut.addActionListener(e -> {
            listener.logOut();
        });

        allBills.addActionListener(e -> {
            listener.allBills();
        });
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 5, 10, 0);
        add(waiter, gbc);

        gbc.gridx++;
        add(allBills, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(10, 5, 10, 5);
        add(logOut, gbc);
    }

    private void initComponents() {
        waiter = new JLabel();
        logOut = new JButton("Log out");
        allBills = new JButton("All bills");
    }
}
