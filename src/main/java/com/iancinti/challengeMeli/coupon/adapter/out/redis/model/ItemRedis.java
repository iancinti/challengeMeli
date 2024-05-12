package com.iancinti.challengeMeli.coupon.adapter.out.redis.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Item")
public class ItemRedis implements Serializable {
    private String id;
    private Integer count;

    public ItemRedis() {
    }

    public ItemRedis(String id, Integer count) {
        this.id = id;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
