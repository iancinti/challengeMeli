package com.iancinti.challengeMeli.coupon.adapter.out.redis;

import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.ItemRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<ItemRedis, String> {
}
