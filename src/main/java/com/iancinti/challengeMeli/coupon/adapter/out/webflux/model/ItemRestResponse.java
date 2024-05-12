package com.iancinti.challengeMeli.coupon.adapter.out.webflux.model;

public class ItemRestResponse {
    private String id;
    private Double price;

    public ItemRestResponse(String id, Double price) {
        this.id = id;
        this.price = price;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
