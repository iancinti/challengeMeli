package com.iancinti.challengeMeli.coupon.application.usecase;

import com.iancinti.challengeMeli.coupon.application.port.in.RedeemCouponCommand;
import com.iancinti.challengeMeli.coupon.application.port.out.RedeemCouponRepository;
import com.iancinti.challengeMeli.coupon.application.port.out.SaveItemRepository;
import com.iancinti.challengeMeli.coupon.domain.Coupon;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
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
    private final SaveItemRepository saveItemRepository;

    @Autowired
    public RedeemCouponUseCase(RedeemCouponRepository redeemCouponRepository, GeneticService geneticService, SaveItemRepository saveItemRepository) {
        this.redeemCouponRepository = redeemCouponRepository;
        this.geneticService = geneticService;
        this.saveItemRepository = saveItemRepository;
    }

    @Override
    public Mono<VerifiedCoupon> execute(Coupon coupon) {
        if (coupon.getItems().isEmpty()) {
            return Mono.just(new VerifiedCoupon(Collections.emptyList(), 0.0));
        } else {
            return Flux.fromIterable(coupon.getItems())
                    .flatMap(redeemCouponRepository::execute)
                    .collectList()
                    .flatMap(items ->
                            geneticService.generate(50, 5, 0.05, items, items.size(), coupon.getAmount())
                                .onErrorResume(Mono::error).doOnNext(saveItemRepository::execute));
        }
    }

}


