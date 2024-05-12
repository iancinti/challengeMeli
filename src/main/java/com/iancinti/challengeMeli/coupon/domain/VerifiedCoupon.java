package com.iancinti.challengeMeli.coupon.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerifiedCoupon {
    private List<String> items;
    private Double total;

    public VerifiedCoupon(List<String> items, Double total) {
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