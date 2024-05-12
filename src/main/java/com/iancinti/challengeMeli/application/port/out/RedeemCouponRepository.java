package com.iancinti.challengeMeli.application.port.out;

import com.iancinti.challengeMeli.adapter.out.model.ItemRestResponse;
import reactor.core.publisher.Mono;

public interface   RedeemCouponRepository {

     Mono<ItemRestResponse> execute(String itemId);

}
