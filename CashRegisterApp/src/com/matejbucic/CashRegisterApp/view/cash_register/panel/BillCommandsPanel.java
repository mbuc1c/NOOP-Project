package com.matejbucic.CashRegisterApp.view.cash_register.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class BillCommandsPanel extends JPanel {

    private BillPanel billPanel;
    private CommandsPanel commandsPanel;

    public BillCommandsPanel() {
        initComponents();
        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(billPanel, BorderLayout.CENTER);
        add(commandsPanel, BorderLayout.SOUTH);
    }

    private void initComponents() {
        billPanel = new BillPanel();
        commandsPanel = new CommandsPanel();
    }
}
