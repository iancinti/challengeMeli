package com.iancinti.challengeMeli.coupon.domain;

import java.util.List;

public class Coupon {
    private List<String> items;
    private int amount;

    public Coupon() {
    }

    public Coupon(List<String> items, int amount) {
        this.items = items;
        this.amount = amount;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}