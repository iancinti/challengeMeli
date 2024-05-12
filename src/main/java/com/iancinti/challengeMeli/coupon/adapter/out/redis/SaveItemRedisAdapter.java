package com.iancinti.challengeMeli.coupon.adapter.out.redis;

import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.ItemRedis;
import com.iancinti.challengeMeli.coupon.application.port.out.SaveItemRepository;
import com.iancinti.challengeMeli.coupon.domain.CouponResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SaveItemRedisAdapter implements SaveItemRepository {

    private final RedisRepository redisRepository;

    @Autowired
    public SaveItemRedisAdapter(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public void execute(CouponResponse couponResponse){

        couponResponse.getItems().forEach( item -> {

            Optional<ItemRedis> resp = redisRepository.findById(item);

            if (resp.isPresent()){
                resp.get().setCount(resp.get().getCount() + 1);
                redisRepository.save(resp.get());
            }else {
                redisRepository.save(new ItemRedis(item, 1));
            }

        });
    }


}
