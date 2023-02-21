package com.matejbucic.CashRegisterApp.model;

import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;

/*
this is a class that simulates database for testing purposes.
TODO: Connect it with remote database
 */
@Getter
public class Database {

    public static ArrayList<Waiter> waiters;

    public Database() {
//        generateWaiters();
    }

    private void generateWaiters() {
        waiters = new ArrayList<>();
        waiters.add(new Waiter("Frane", "Franic", "1111"));
        waiters.add(new Waiter("Krone", "Cicanocic", "2222"));
        waiters.add(new Waiter("Alojzije", "Cetkovic", "1234"));
    }

    public boolean checkIfValid(String password) {
        for (Waiter waiter: waiters) {
            if (waiter.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public Waiter getWaiterWithPassword(String password) {
        for (Waiter waiter: waiters) {
            if (waiter.getPassword().equals(password)) {
                return waiter;
            }
        }
        return null;
    }

    public void addWaiterToDatabase(Waiter waiter) {
        this.waiters.add(waiter);
        System.out.println(waiter.toString());
    }
}
