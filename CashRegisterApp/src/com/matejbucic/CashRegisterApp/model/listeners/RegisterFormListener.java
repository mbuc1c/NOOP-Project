package com.matejbucic.CashRegisterApp.model.listeners;

import java.sql.SQLException;

public interface RegisterFormListener {

    void cancel();
    void submit() throws SQLException;
}
