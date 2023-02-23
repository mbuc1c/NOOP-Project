package com.matejbucic.CashRegisterApp.view.cash_register;

import com.matejbucic.CashRegisterApp.controller.Controller;
import com.matejbucic.CashRegisterApp.model.Bill;
import com.matejbucic.CashRegisterApp.model.Waiter;
import com.matejbucic.CashRegisterApp.model.listeners.DrinksListener;
import com.matejbucic.CashRegisterApp.model.listeners.InformationPanelListener;
import com.matejbucic.CashRegisterApp.view.cash_register.panel.BillCommandsPanel;
import com.matejbucic.CashRegisterApp.view.cash_register.panel.BillPanel;
import com.matejbucic.CashRegisterApp.view.cash_register.panel.DrinksPanel;
import com.matejbucic.CashRegisterApp.view.cash_register.panel.InformationPanel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// TODO: line 50
@Getter
@Setter
public class CashRegisterFrame extends JFrame {

    private InformationPanel informationPanel;
    private DrinksPanel drinksPanel;
    private BillCommandsPanel billCommandsPanel;

    private Waiter waiter;
    private Bill bill;
    private Controller controller;


    public CashRegisterFrame() {
        super("Cash register");
        initComponents();
        layoutComponents();
        activatePanels();

        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH); // Sets frame fullscreen
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void activatePanels() {
        activateInformationPanel();
        activateDrinksPanel();

    }

    private void activateDrinksPanel() {
        drinksPanel.setListener(new DrinksListener() {
            @Override
            public void addDrink(ActionEvent e) {
                // TODO modify so it updates bill JTable
                controller.addDrinkToBill(waiter, drinksPanel.getDrinks().get(e.getSource()), bill, billCommandsPanel.getBillPanel().getTotal());
            }
        });
    }

    private void activateInformationPanel() {
        informationPanel.setListener(new InformationPanelListener() {
            @Override
            public void logOut() {
                controller.logOut();
                dispose();
            }

            @Override
            public void allBills() {
                // TODO: display frame with all current workers bills
                System.out.println(bill.toString());
            }
        });
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(informationPanel, BorderLayout.NORTH);
        add(drinksPanel, BorderLayout.CENTER);
        add(billCommandsPanel, BorderLayout.EAST);
    }

    private void initComponents() {
        informationPanel = new InformationPanel();
        drinksPanel = new DrinksPanel();
        billCommandsPanel = new BillCommandsPanel();

        bill = new Bill();
        controller = new Controller();
    }
}
