package com.matejbucic.CashRegisterApp.view.cash_register.panel;

import com.matejbucic.CashRegisterApp.model.Bill;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class BillPanel extends JPanel {

    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private String[] columnIdentifiers = new String[] {
            "Product","Amount","Cost","Price"
    };
    private String[][] rows;

    private Bill bill;

    public BillPanel() {
        initComponents();
        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        add(scrollPane, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(new JLabel("Total: ToBe added!"), gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JButton("Btn1"), gbc);

        gbc.gridx++;
        add(new JButton("Btn2"), gbc);

        gbc.gridx++;
        add(new JButton("Btn3"), gbc);
    }

    private void initComponents() {
        table = new JTable();
        model = (DefaultTableModel) table.getModel();
        scrollPane = new JScrollPane(table);

        model.setColumnIdentifiers(columnIdentifiers);
    }
}
