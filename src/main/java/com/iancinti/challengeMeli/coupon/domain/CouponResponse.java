package com.iancinti.challengeMeli.coupon.domain;

import java.util.List;

public class CouponResponse {
    private List<String> items;
    private Double total;

    public CouponResponse(List<String> items, Double total) {
        this.items = items;
        this.total = total;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}