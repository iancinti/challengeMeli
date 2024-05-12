package com.iancinti.challengeMeli.application.port.in;

import com.iancinti.challengeMeli.domain.CouponRequest;
import com.iancinti.challengeMeli.domain.CouponResponse;
import reactor.core.publisher.Mono;

public interface RedeemCouponCommand {

    Mono<CouponResponse> execute(CouponRequest couponRequest);

}

