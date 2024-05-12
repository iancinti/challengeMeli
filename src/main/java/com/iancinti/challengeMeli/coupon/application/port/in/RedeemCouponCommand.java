package com.iancinti.challengeMeli.coupon.application.port.in;

import com.iancinti.challengeMeli.coupon.domain.CouponRequest;
import com.iancinti.challengeMeli.coupon.domain.CouponResponse;
import reactor.core.publisher.Mono;

public interface RedeemCouponCommand {

    Mono<CouponResponse> execute(CouponRequest couponRequest);

}

