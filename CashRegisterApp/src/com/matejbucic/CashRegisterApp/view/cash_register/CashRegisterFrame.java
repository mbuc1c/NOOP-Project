package com.matejbucic.CashRegisterApp.view.cash_register;

import com.matejbucic.CashRegisterApp.controller.Controller;
import com.matejbucic.CashRegisterApp.model.Bill;
import com.matejbucic.CashRegisterApp.model.Waiter;
import com.matejbucic.CashRegisterApp.model.drinks.Drink;
import com.matejbucic.CashRegisterApp.model.listeners.CommandsPanelListener;
import com.matejbucic.CashRegisterApp.model.listeners.DrinksPanelListener;
import com.matejbucic.CashRegisterApp.model.listeners.InformationPanelListener;
import com.matejbucic.CashRegisterApp.view.cash_register.panel.BillCommandsPanel;
import com.matejbucic.CashRegisterApp.view.cash_register.panel.DrinksPanel;
import com.matejbucic.CashRegisterApp.view.cash_register.panel.InformationPanel;
import com.matejbucic.CashRegisterApp.view.login_form.LoginFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
        activateCommandsPanel();

    }

    private void activateCommandsPanel() {
        billCommandsPanel.getCommandsPanel().setListener(new CommandsPanelListener() {
            @Override
            public void clearAll() {
                controller.clearAll(bill);
                controller.updateBillTable(bill, billCommandsPanel.getBillPanel().getModel(), billCommandsPanel.getBillPanel().getTotal());

                JTable table = billCommandsPanel.getBillPanel().getTable();
                int tablePreferredWidth = billCommandsPanel.getBillPanel().getScrollPane().getPreferredSize().width;
                billCommandsPanel.getBillPanel().setJTableColumnsWidth(table,tablePreferredWidth, 52, 16, 16, 16);
            }

            @Override
            public void deduct() {
                int selectedRow = billCommandsPanel.getBillPanel().getTable().getSelectedRow();
                if (selectedRow != -1) {
                    Drink selectedDrink = controller.getSelectedDrink(selectedRow, bill);
                    controller.removeDrink(selectedDrink, bill);
                    controller.updateBillTable(bill, billCommandsPanel.getBillPanel().getModel(), billCommandsPanel.getBillPanel().getTotal());

                    JTable table = billCommandsPanel.getBillPanel().getTable();
                    int tablePreferredWidth = billCommandsPanel.getBillPanel().getScrollPane().getPreferredSize().width;
                    billCommandsPanel.getBillPanel().setJTableColumnsWidth(table,tablePreferredWidth, 52, 16, 16, 16);
                }
            }

            @Override
            public void checkout() {
                System.out.println(bill.toString());
                controller.checkoutBill(bill, waiter);
                dispose();
            }
        });
    }

    private void activateDrinksPanel() {
        drinksPanel.setListener(new DrinksPanelListener() {
            @Override
            public void addDrink(ActionEvent e) {
                // TODO modify so it updates bill JTable
                controller.addDrinkToBill(drinksPanel.getDrinks().get(e.getSource()), bill);
                controller.updateBillTable(bill, billCommandsPanel.getBillPanel().getModel(), billCommandsPanel.getBillPanel().getTotal());

                JTable table = billCommandsPanel.getBillPanel().getTable();
                int tablePreferredWidth = billCommandsPanel.getBillPanel().getScrollPane().getPreferredSize().width;
                billCommandsPanel.getBillPanel().setJTableColumnsWidth(table,tablePreferredWidth, 52, 16, 16, 16);
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
