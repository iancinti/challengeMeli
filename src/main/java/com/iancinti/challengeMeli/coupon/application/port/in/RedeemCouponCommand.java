package com.iancinti.challengeMeli.coupon.application.port.in;

import com.iancinti.challengeMeli.coupon.domain.Coupon;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
import reactor.core.publisher.Mono;

public interface RedeemCouponCommand {

    Mono<VerifiedCoupon> execute(Coupon coupon);

}

