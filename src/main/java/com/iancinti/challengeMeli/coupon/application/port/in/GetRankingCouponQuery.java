package com.iancinti.challengeMeli.coupon.application.port.in;

import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;


public interface GetRankingCouponQuery {

    VerifiedCoupon execute();
}
