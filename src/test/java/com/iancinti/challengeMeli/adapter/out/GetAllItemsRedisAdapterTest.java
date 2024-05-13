package com.iancinti.challengeMeli.adapter.out;

import com.iancinti.challengeMeli.coupon.adapter.out.redis.GetAllItemsRedisAdapter;
import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.ItemRedis;
import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.RedisRepository;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetAllItemsRedisAdapterTest {

    @Test
    void testExecute() {

        RedisRepository redisRepositoryMock = Mockito.mock(RedisRepository.class);
        GetAllItemsRedisAdapter getAllItemsRedisAdapter = new GetAllItemsRedisAdapter(redisRepositoryMock);

        List<ItemRedis> itemRedisList = new ArrayList<>();
        itemRedisList.add(new ItemRedis("item1", 10));
        itemRedisList.add(new ItemRedis("item2", 20));
        itemRedisList.add(new ItemRedis("item3", 30));

        when(redisRepositoryMock.findAll()).thenReturn(itemRedisList);

        VerifiedCoupon result = getAllItemsRedisAdapter.execute();

        List<String> expectedItems = List.of("item1", "item2", "item3");
        assertEquals(expectedItems, result.getItems());
    }
}
