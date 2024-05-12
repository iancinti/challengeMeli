package com.iancinti.challengeMeli.coupon.application.usecase;

import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.ItemRedis;
import com.iancinti.challengeMeli.coupon.application.port.in.GetRankingCouponQuery;
import com.iancinti.challengeMeli.coupon.application.port.out.GetAllItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class GetRankingCouponUseCase implements GetRankingCouponQuery {

    private final GetAllItemsRepository getAllItemsRepository;

    @Autowired
    public GetRankingCouponUseCase(GetAllItemsRepository getAllItemsRepository) {
        this.getAllItemsRepository = getAllItemsRepository;
    }

    @Override
    public List<ItemRedis> execute() {
        return getAllItemsRepository.execute()
                .stream()
                .sorted(Comparator.comparing(ItemRedis::getCount))
                .limit(5)
                .toList().reversed();
    }
}
