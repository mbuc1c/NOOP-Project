package com.matejbucic.CashRegisterApp.model.drinks.concrete;

import com.matejbucic.CashRegisterApp.model.drinks.Drink;

public class Tea extends Drink {

    private final double PRICE = 2.0;

    public Tea() {
        drinkPrice = PRICE;
    }
}
