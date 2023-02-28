package com.matejbucic.CashRegisterApp.model;

import com.matejbucic.CashRegisterApp.model.drinks.Drink;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

@Getter
@Setter
@ToString
public class Bill {

    private LinkedHashMap<Drink, Integer> drinks;
    private double totalPrice = 0;
    private Waiter waiter;
    private Timestamp date;


    public Bill() {
        drinks = new LinkedHashMap<>();
    }
}
