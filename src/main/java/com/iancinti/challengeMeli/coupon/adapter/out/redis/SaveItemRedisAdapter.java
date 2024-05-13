package com.iancinti.challengeMeli.coupon.adapter.out.redis;

import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.ItemRedis;
import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.RedisRepository;
import com.iancinti.challengeMeli.coupon.application.port.out.SaveItemRepository;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SaveItemRedisAdapter implements SaveItemRepository {

    private final RedisRepository redisRepository;

    private static final Logger logger = LoggerFactory.getLogger(SaveItemRedisAdapter.class);

    @Autowired
    public SaveItemRedisAdapter(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public void execute(VerifiedCoupon verifiedCoupon) {
        logger.info("Ejecutando SaveItemRedisAdapter");

        verifiedCoupon.getItems().forEach(item -> {
            logger.debug("Procesando ítem");

            Optional<ItemRedis> resp = redisRepository.findById(item);

            if (resp.isPresent()) {
                logger.debug("El ítem ya existe en Redis. Incrementando contador.");
                resp.get().setCount(resp.get().getCount() + 1);
                redisRepository.save(resp.get());
                logger.debug("Contador del ítem incrementado.");
            } else {
                logger.debug("El ítem no existe en Redis. Creando nueva entrada.");
                redisRepository.save(new ItemRedis(item, 1));
                logger.debug("Nueva entrada creada para el ítem.");
            }
        });

        logger.info("Ejecución de SaveItemRedisAdapter completada");
    }
}
