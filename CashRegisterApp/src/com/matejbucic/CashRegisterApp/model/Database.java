package com.matejbucic.CashRegisterApp.model;

import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

/*
this is a class that simulates database for testing purposes.
TODO: Connect it with remote database
 */
@Getter
public class Database {

    public static ArrayList<Waiter> waiters;
    Connection con;

    public Database() {
    }

    public void connect() throws SQLException {
        System.out.println("Connecting to database...");
        try {
            // load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // obtain connection
            String url = "jdbc:mysql://db4free.net:3306/cashregisterdb"; // your database
            String user = "mbuc1c"; // your username
            String password = "noop1234"; // your password
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to -> " + con.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load driver!!!");
        }
    }

    public void disconnect() throws SQLException {
        con.close();
        System.out.println("Disconnected from DB....");
    }

    public boolean checkIfValid(String password) throws SQLException {
        connect();
        PreparedStatement statement = con.prepareStatement
                ("SELECT * FROM waiters WHERE password = ?");
        statement.setString(1, password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            disconnect();
            return true;
        } else {
            disconnect();
            return false;
        }
    }

    public Waiter getWaiterWithPassword(String password) throws SQLException {
        Waiter waiter = new Waiter();
        connect();
        PreparedStatement statement = con.prepareStatement
                ("SELECT * FROM waiters WHERE password=?");
        statement.setString(1, password);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            waiter.setId(resultSet.getInt(1));
            waiter.setName(resultSet.getString(2));
            waiter.setSurname(resultSet.getString(3));
            waiter.setPassword(resultSet.getString(4));
        }
        disconnect();
        return waiter;
    }

    public void addWaiterToDatabase(Waiter waiter) throws SQLException {
        connect();
        if (con != null) {
            try {
                PreparedStatement statement = con.prepareStatement
                        ("INSERT INTO waiters (name, surname, password) VALUES (?, ?, ?)");
                statement.setString(1, waiter.getName());
                statement.setString(2, waiter.getSurname());
                statement.setString(3, waiter.getPassword());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        disconnect();
    }


    public void addBillToDB(Bill bill) throws SQLException {
        connect();
        if (con != null) {
            try {
                PreparedStatement statement = con.prepareStatement
                        ("INSERT INTO bills (waiter_id, total, date) VALUES (?, ?, ?)");
                statement.setInt(1, bill.getWaiter().getId());
                statement.setDouble(2, bill.getTotalPrice());
                statement.setTimestamp(3, bill.getDate());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        disconnect();
    }

    public DefaultTableModel getAllBillsTableModel(Waiter waiter) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        Vector<String> columnIdentifiers = new Vector<>();
        Vector<Vector<String>> data = new Vector<>();

        columnIdentifiers.add("ID");
        columnIdentifiers.add("Total");
        columnIdentifiers.add("Date");

        connect();
        if (con != null) {
            try {
                PreparedStatement statement = con.prepareStatement
                        ("SELECT * FROM bills WHERE waiter_id=?");
                statement.setInt(1, waiter.getId());
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Vector<String> row = new Vector<>();
                    row.add(String.valueOf(resultSet.getInt(1)));
                    row.add(String.valueOf(resultSet.getDouble(3)));
                    row.add(String.valueOf(resultSet.getTimestamp(4)));
                    data.add(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        disconnect();
        model.setDataVector(data, columnIdentifiers);
        return model;
    }
}
