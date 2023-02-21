package com.matejbucic.CashRegisterApp.view.login_form.panel;

import com.matejbucic.CashRegisterApp.model.listeners.LoginFormListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class KeyBoardPanel extends JPanel {

    private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnClear, btnSubmit;
    private LoginFormListener listener;

    public KeyBoardPanel() {
        initComponents();
        layoutComponents();
        activateButtons();
    }

    private void activateButtons() {
        JButton[] numButtons= {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0};
        for (JButton numButton : numButtons) {
            numButton.addActionListener(e -> {
                listener.numBtnPressed(e);
            });
        }

        btnClear.addActionListener(e -> {
            listener.clear();
        });

        btnSubmit.addActionListener(e -> {
            listener.submit();
        });
    }

    private void layoutComponents() {
        setLayout(new GridLayout(4, 3));

        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(btn6);
        add(btn7);
        add(btn8);
        add(btn9);
        add(btnClear);
        add(btn0);
        add(btnSubmit);
    }

    private void initComponents() {
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        btn0 = new JButton("0");
        btnClear = new JButton("Clear");
        btnSubmit = new JButton("Submit");
    }
}
