package com.iancinti.challengeMeli.coupon.adapter.out.redis;

import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.ItemRedis;
import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.RedisRepository;
import com.iancinti.challengeMeli.coupon.application.port.out.GetAllItemsRepository;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class GetAllItemsRedisAdapter implements GetAllItemsRepository {

    private final RedisRepository redisRepository;

    @Autowired
    public GetAllItemsRedisAdapter(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public VerifiedCoupon execute(){
        List<ItemRedis> resp = new ArrayList<>();
        for (ItemRedis itemRedis: redisRepository.findAll()) {
            resp.add(itemRedis);
        }
        return new VerifiedCoupon(
                resp.stream()
                        .sorted(Comparator.comparing(ItemRedis::getCount))
                        .limit(5)
                        .map(ItemRedis::getId)
                        .toList().reversed(),
                null
        );
    }
}
