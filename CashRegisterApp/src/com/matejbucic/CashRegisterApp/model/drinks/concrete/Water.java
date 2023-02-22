package com.matejbucic.CashRegisterApp.model.drinks.concrete;

import com.matejbucic.CashRegisterApp.model.drinks.Drink;

public class Water extends Drink {

    private final double PRICE = 99.99;

    public Water() {
        drinkPrice = PRICE;
    }
}
