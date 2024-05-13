package com.iancinti.challengeMeli.coupon.application.usecase;

import com.iancinti.challengeMeli.coupon.application.port.in.GetRankingCouponQuery;
import com.iancinti.challengeMeli.coupon.application.port.out.GetAllItemsRepository;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetRankingCouponUseCase implements GetRankingCouponQuery {

    private final GetAllItemsRepository getAllItemsRepository;
    private static final Logger logger = LoggerFactory.getLogger(GetRankingCouponUseCase.class);

    @Autowired
    public GetRankingCouponUseCase(GetAllItemsRepository getAllItemsRepository) {
        this.getAllItemsRepository = getAllItemsRepository;
    }

    @Override
    public VerifiedCoupon execute() {
        logger.info("Ejecutando GetRankingCouponUseCase");
        VerifiedCoupon verifiedCoupon = getAllItemsRepository.execute();
        logger.info("GetRankingCouponUseCase ejecutado exitosamente");
        return verifiedCoupon;
    }
}
