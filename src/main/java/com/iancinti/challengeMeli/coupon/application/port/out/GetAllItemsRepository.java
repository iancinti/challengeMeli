package com.iancinti.challengeMeli.coupon.application.port.out;

import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.ItemRedis;

import java.util.List;

public interface GetAllItemsRepository {

    List<ItemRedis> execute();
}
