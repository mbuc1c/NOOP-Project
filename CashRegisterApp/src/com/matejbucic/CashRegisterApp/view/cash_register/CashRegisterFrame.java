package com.matejbucic.CashRegisterApp.view.cash_register;

import com.matejbucic.CashRegisterApp.controller.Controller;
import com.matejbucic.CashRegisterApp.model.Waiter;
import com.matejbucic.CashRegisterApp.model.listeners.InformationPanelListener;
import com.matejbucic.CashRegisterApp.view.cash_register.panel.BillPanel;
import com.matejbucic.CashRegisterApp.view.cash_register.panel.DrinksPanel;
import com.matejbucic.CashRegisterApp.view.cash_register.panel.InformationPanel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

// TODO: line 50
@Getter
@Setter
public class CashRegisterFrame extends JFrame {

    private InformationPanel informationPanel;
    private DrinksPanel drinksPanel;
    private BillPanel billPanel;

    private Waiter waiter;
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
        informationPanel.setListener(new InformationPanelListener() {
            @Override
            public void logOut() {
                controller.logOut();
                dispose();
            }

            @Override
            public void allBills() {
                // TODO: display frame with all current workers bills
            }
        });


    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(informationPanel, BorderLayout.NORTH);
        add(drinksPanel, BorderLayout.CENTER);
        add(billPanel, BorderLayout.EAST);
    }

    private void initComponents() {
        informationPanel = new InformationPanel();
        drinksPanel = new DrinksPanel();
        billPanel = new BillPanel();

        controller = new Controller();
    }
}
