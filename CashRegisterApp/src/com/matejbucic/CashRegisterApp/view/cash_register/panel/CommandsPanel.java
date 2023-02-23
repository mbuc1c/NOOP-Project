package com.matejbucic.CashRegisterApp.view.cash_register.panel;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class CommandsPanel extends JPanel {

    private JButton clearAll, deduct, checkout;

    public CommandsPanel() {
        initComponents();
        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 10, 5, 5);
        add(clearAll, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(deduct, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(5, 5, 5, 10);
        add(checkout, gbc);
    }

    private void initComponents() {
        clearAll = new JButton("Clear all");
        clearAll.setPreferredSize(new Dimension(150, 50));
        deduct = new JButton("Deduct");
        deduct.setPreferredSize(new Dimension(150, 50));
        checkout = new JButton("Checkout");
        checkout.setPreferredSize(new Dimension(150, 50));
    }
}
