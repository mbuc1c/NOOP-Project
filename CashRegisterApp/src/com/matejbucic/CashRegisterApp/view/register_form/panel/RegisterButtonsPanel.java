package com.matejbucic.CashRegisterApp.view.register_form.panel;

import com.matejbucic.CashRegisterApp.model.listeners.RegisterFormListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

@Getter
@Setter
public class RegisterButtonsPanel extends JPanel {

    private JButton cancel, submit;
    private RegisterFormListener listener;

    public RegisterButtonsPanel() {
        initComponents();
        layoutComponents();
        activateButtons();

        setPreferredSize(new Dimension(750, 100));
    }

    private void activateButtons() {
        cancel.addActionListener(e -> {
            listener.cancel();
        });

        submit.addActionListener(e -> {
            try {
                listener.submit();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add the Cancel button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 10, 5, 5);
        add(cancel, gbc);

        // Add the Submit button
        gbc.gridx++;
        gbc.insets = new Insets(5, 5, 5, 10);
        add(submit, gbc);
    }

    private void initComponents() {
        cancel = new JButton("Cancel");
        cancel.setPreferredSize(new Dimension(150, 50));
        submit = new JButton("Submit");
        submit.setPreferredSize(new Dimension(150, 50));

    }
}
