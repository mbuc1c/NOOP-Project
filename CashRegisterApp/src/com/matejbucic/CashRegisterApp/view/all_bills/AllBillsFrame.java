package com.matejbucic.CashRegisterApp.view.all_bills;

import com.matejbucic.CashRegisterApp.model.Waiter;
import com.matejbucic.CashRegisterApp.view.all_bills.panel.AllBillsTablePanel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AllBillsFrame extends JFrame {

    private AllBillsTablePanel allBillsTablePanel;
    private Waiter waiter;

    public AllBillsFrame() {
        super();
        initComponents();
        layoutComponents();

        setVisible(true);
        setSize(650, 450);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(allBillsTablePanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        allBillsTablePanel = new AllBillsTablePanel();
    }
}
