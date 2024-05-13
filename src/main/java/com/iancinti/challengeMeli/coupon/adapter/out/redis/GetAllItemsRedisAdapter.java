package com.iancinti.challengeMeli.coupon.adapter.out.redis;

import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.ItemRedis;
import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.RedisRepository;
import com.iancinti.challengeMeli.coupon.application.port.out.GetAllItemsRepository;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class GetAllItemsRedisAdapter implements GetAllItemsRepository {

    private final RedisRepository redisRepository;

    private static final Logger logger = LoggerFactory.getLogger(GetAllItemsRedisAdapter.class);

    @Autowired
    public GetAllItemsRedisAdapter(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public VerifiedCoupon execute() {
        logger.info("Ejecutando GetAllItemsRedisAdapter");

        List<ItemRedis> resp = new ArrayList<>();
        for (ItemRedis itemRedis : redisRepository.findAll()) {
            logger.debug("Agregando item a la lista de elementos recuperados");
            resp.add(itemRedis);
        }

        List<String> topItems = resp.stream()
                .sorted(Comparator.comparing(ItemRedis::getCount))
                .limit(5)
                .map(ItemRedis::getId)
                .toList();

        logger.info("Obtenidos los 5 elementos más populares de Redis");

        VerifiedCoupon verifiedCoupon = new VerifiedCoupon(topItems, null);

        logger.info("Ejecución de GetAllItemsRedisAdapter completada");
        return verifiedCoupon;
    }
}
