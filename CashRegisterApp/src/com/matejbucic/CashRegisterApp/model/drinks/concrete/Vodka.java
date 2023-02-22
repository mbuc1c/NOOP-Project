package com.matejbucic.CashRegisterApp.model.drinks.concrete;

import com.matejbucic.CashRegisterApp.model.drinks.Drink;

public class Vodka extends Drink {
    private final double PRICE = 3.0;

    public Vodka() {
        drinkPrice = PRICE;
    }
}
