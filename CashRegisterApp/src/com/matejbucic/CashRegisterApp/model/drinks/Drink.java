package com.matejbucic.CashRegisterApp.model.drinks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Drink {

    protected double drinkPrice;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " -> " + drinkPrice;
    }
}
