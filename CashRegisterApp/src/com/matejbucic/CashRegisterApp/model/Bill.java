package com.matejbucic.CashRegisterApp.model;

import com.matejbucic.CashRegisterApp.model.drinks.Drink;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class Bill {

    private HashMap<Drink, Integer> drinks;
    private double totalPrice;

    private Bill() {
        drinks = new HashMap<>();
    }

    public void addDrink(Drink drink) {
        if (!drinks.containsKey(drink)) {
            drinks.put(drink, 1);
        } else {
            drinks.put(drink, drinks.get(drink) + 1);
        }
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        for (Drink drink : drinks.keySet()) {
            totalPrice += drink.getDrinkPrice() * drinks.get(drink);
        }
    }
}
