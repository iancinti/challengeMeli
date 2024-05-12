package com.iancinti.challengeMeli.coupon.application.port.in;

import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.ItemRedis;

import java.util.List;

public interface GetRankingCouponQuery {

    List<ItemRedis> execute();
}
