package com.iancinti.challengeMeli.coupon.application.usecase;

import com.iancinti.challengeMeli.coupon.application.port.in.GetRankingCouponQuery;
import com.iancinti.challengeMeli.coupon.application.port.out.GetAllItemsRepository;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetRankingCouponUseCase implements GetRankingCouponQuery {

    private final GetAllItemsRepository getAllItemsRepository;

    @Autowired
    public GetRankingCouponUseCase(GetAllItemsRepository getAllItemsRepository) {
        this.getAllItemsRepository = getAllItemsRepository;
    }

    @Override
    public VerifiedCoupon execute() {
        return getAllItemsRepository.execute();
    }
}
