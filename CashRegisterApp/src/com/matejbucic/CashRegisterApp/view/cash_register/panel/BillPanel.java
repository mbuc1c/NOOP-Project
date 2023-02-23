package com.matejbucic.CashRegisterApp.view.cash_register.panel;

import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

@Getter
public class BillPanel extends JPanel {

    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private String[] columnIdentifiers = new String[] {
            "Product","Amount","Cost","Price"
    };
    private JLabel total;


    public BillPanel() {
        initComponents();
        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(total, BorderLayout.SOUTH);
    }

    private void initComponents() {
        table = new JTable();
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnIdentifiers);
        scrollPane = new JScrollPane(table);
        setJTableColumnsWidth(table, scrollPane.getPreferredSize().width, 52, 16, 16, 16);


        total = new JLabel("Total: 0 \u20AC");
        total.setFont(new Font(Font.DIALOG, Font.BOLD, 48));
    }

    // ref: https://www.codejava.net/java-se/swing/setting-column-width-and-row-height-for-jtable
    private void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
                                             double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int)
                    (tablePreferredWidth * (percentages[i] / total)));
        }
    }
}
