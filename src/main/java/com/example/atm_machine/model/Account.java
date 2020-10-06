package com.example.atm_machine.model;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table (name = "account")

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    @NotNull
    private String card;
    @Column
    @NotNull
    private String pin;
    @Column
    private double balance;

    public Account() {
    }

    public Account(String card, String pin, double balance) {
        this.card = card;
        this.pin = pin;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
