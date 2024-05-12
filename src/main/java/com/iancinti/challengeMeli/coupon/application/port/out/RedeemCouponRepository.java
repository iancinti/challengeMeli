package com.iancinti.challengeMeli.coupon.application.port.out;

import com.iancinti.challengeMeli.coupon.adapter.out.webflux.model.ItemRestResponse;
import reactor.core.publisher.Mono;

public interface   RedeemCouponRepository {

     Mono<ItemRestResponse> execute(String itemId);

}
