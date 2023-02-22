package com.matejbucic.CashRegisterApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Waiter {

    private int id;
    private static int cnt = 1; // will be removed with database auto-incrementation
    private String name;
    private String surname;
    private String password;

    public Waiter(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        id = cnt;
        cnt++;
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
