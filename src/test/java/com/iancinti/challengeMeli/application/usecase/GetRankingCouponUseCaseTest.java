package com.iancinti.challengeMeli.application.usecase;

import com.iancinti.challengeMeli.coupon.application.port.out.GetAllItemsRepository;
import com.iancinti.challengeMeli.coupon.application.usecase.GetRankingCouponUseCase;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetRankingCouponUseCaseTest {

    @Test
    void execute_ReturnsVerifiedCoupon() {

        GetAllItemsRepository getAllItemsRepository = Mockito.mock(GetAllItemsRepository.class);
        List<String> mockItems = Arrays.asList("item1", "item2");
        Double mockTotal = 100.0;
        VerifiedCoupon expectedCoupon = new VerifiedCoupon(mockItems, mockTotal);
        when(getAllItemsRepository.execute()).thenReturn(expectedCoupon);
        GetRankingCouponUseCase useCase = new GetRankingCouponUseCase(getAllItemsRepository);

        VerifiedCoupon actualCoupon = useCase.execute();

        assertEquals(expectedCoupon, actualCoupon);
    }
}
