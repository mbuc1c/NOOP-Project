package com.matejbucic.CashRegisterApp.view.all_bills.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AllBillsTablePanel extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;

    public AllBillsTablePanel() {
        initComponents();
        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);
    }

    private void initComponents() {
        table = new JTable();
        scrollPane = new JScrollPane(table);
    }
}
