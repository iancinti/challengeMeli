package com.iancinti.challengeMeli.adapter.out;

import com.iancinti.challengeMeli.coupon.adapter.out.redis.SaveItemRedisAdapter;
import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.ItemRedis;
import com.iancinti.challengeMeli.coupon.adapter.out.redis.model.RedisRepository;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SaveItemRedisAdapterTest {

    @Test
    void testExecute() {

        RedisRepository redisRepositoryMock = Mockito.mock(RedisRepository.class);
        SaveItemRedisAdapter saveItemRedisAdapter = new SaveItemRedisAdapter(redisRepositoryMock);

        VerifiedCoupon verifiedCoupon = new VerifiedCoupon(List.of("item1", "item2", "item3"), null);

        when(redisRepositoryMock.findById(anyString())).thenReturn(Optional.empty());

        saveItemRedisAdapter.execute(verifiedCoupon);

        verify(redisRepositoryMock, times(3)).save(any(ItemRedis.class));

        ArgumentCaptor<ItemRedis> itemRedisCaptor = ArgumentCaptor.forClass(ItemRedis.class);
        verify(redisRepositoryMock, times(3)).save(itemRedisCaptor.capture());

        List<ItemRedis> savedItems = itemRedisCaptor.getAllValues();
        assertEquals("item1", savedItems.get(0).getId());
        assertEquals("item2", savedItems.get(1).getId());
        assertEquals("item3", savedItems.get(2).getId());
        assertEquals(1, savedItems.get(0).getCount());
        assertEquals(1, savedItems.get(1).getCount());
        assertEquals(1, savedItems.get(2).getCount());
    }
}
