package com.matejbucic.CashRegisterApp.model.drinks.concrete;

import com.matejbucic.CashRegisterApp.model.drinks.Drink;

public class Soda extends Drink {

    private final double PRICE = 2.0;

    public Soda() {
        drinkPrice = PRICE;
    }
}
