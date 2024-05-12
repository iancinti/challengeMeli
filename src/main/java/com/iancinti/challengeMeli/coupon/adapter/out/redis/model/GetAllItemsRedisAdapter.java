package com.iancinti.challengeMeli.coupon.adapter.out.redis.model;

import com.iancinti.challengeMeli.coupon.adapter.out.redis.RedisRepository;
import com.iancinti.challengeMeli.coupon.application.port.out.GetAllItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllItemsRedisAdapter implements GetAllItemsRepository {

    private final RedisRepository redisRepository;

    @Autowired
    public GetAllItemsRedisAdapter(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public List<ItemRedis> execute(){
        List<ItemRedis> resp = new ArrayList<>();
        for (ItemRedis itemRedis: redisRepository.findAll()) {
            resp.add(itemRedis);
        }
        return resp;
    }
}
