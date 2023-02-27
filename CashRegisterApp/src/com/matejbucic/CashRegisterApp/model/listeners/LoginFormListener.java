package com.matejbucic.CashRegisterApp.model.listeners;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

public interface LoginFormListener {
    void numBtnPressed(ActionEvent e);
    void clear();
    void submit() throws SQLException;
}
