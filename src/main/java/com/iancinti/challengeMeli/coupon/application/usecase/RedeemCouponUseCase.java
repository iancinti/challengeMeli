package com.iancinti.challengeMeli.coupon.application.usecase;

import com.iancinti.challengeMeli.coupon.application.port.in.RedeemCouponCommand;
import com.iancinti.challengeMeli.coupon.application.port.out.RedeemCouponRepository;
import com.iancinti.challengeMeli.coupon.domain.CouponRequest;
import com.iancinti.challengeMeli.coupon.domain.CouponResponse;
import com.iancinti.challengeMeli.genetic.GeneticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;


@Component
public class RedeemCouponUseCase implements RedeemCouponCommand {

    private final RedeemCouponRepository redeemCouponRepository;
    private final GeneticService geneticService;

    @Autowired
    public RedeemCouponUseCase(RedeemCouponRepository redeemCouponRepository, GeneticService geneticService) {
        this.redeemCouponRepository = redeemCouponRepository;
        this.geneticService = geneticService;
    }

    @Override
    public Mono<CouponResponse> execute(CouponRequest couponRequest) {
        if (couponRequest.getItems().isEmpty()) {
            return Mono.just(new CouponResponse(Collections.emptyList(), 0.0));
        } else {
            return Flux.fromIterable(couponRequest.getItems())
                    .flatMap(redeemCouponRepository::execute)
                    .collectList()
                    .flatMap(items -> {
                        return geneticService.generate(50, 5, 0.05, items, items.size(), couponRequest.getAmount())
                                .onErrorResume(e -> {
                                    return Mono.error(e);
                                });
                    });
        }
    }

}


