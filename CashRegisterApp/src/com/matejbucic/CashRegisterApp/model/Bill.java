package com.matejbucic.CashRegisterApp.model;

import com.matejbucic.CashRegisterApp.model.drinks.Drink;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;

@Getter
@Setter
@ToString
public class Bill {

    private HashMap<Drink, Integer> drinks;
    private double totalPrice = 0;
    private Waiter waiter;
    private Date date;


    public Bill() {
        drinks = new HashMap<>();
    }
}
