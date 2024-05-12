package com.iancinti.challengeMeli.application.port.usecase;

import com.iancinti.challengeMeli.adapter.out.model.ItemRestResponse;
import com.iancinti.challengeMeli.application.port.in.RedeemCouponCommand;
import com.iancinti.challengeMeli.application.port.out.RedeemCouponRepository;
import com.iancinti.challengeMeli.domain.CouponRequest;
import com.iancinti.challengeMeli.domain.CouponResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;


@Component
public class RedeemCouponUseCase implements RedeemCouponCommand {

    private final RedeemCouponRepository redeemCouponRepository;

    public RedeemCouponUseCase(RedeemCouponRepository redeemCouponRepository) {
        this.redeemCouponRepository = redeemCouponRepository;
    }

    @Override
    public Mono<CouponResponse> execute(CouponRequest couponRequest) {
        if (couponRequest.getItems().isEmpty()) {
            return Mono.just(new CouponResponse(Collections.emptyList(), 0.0));
        } else {
            return Flux.fromIterable(couponRequest.getItems())
                    .flatMap(redeemCouponRepository::execute)
                    .map(ItemRestResponse::getPrice)
                    .reduce(Double::sum)
                    .map(total -> new CouponResponse(couponRequest.getItems(), total));
        }
    }

}


