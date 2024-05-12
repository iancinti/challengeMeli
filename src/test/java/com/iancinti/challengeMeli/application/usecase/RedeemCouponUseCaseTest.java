package com.iancinti.challengeMeli.application.usecase;

import com.iancinti.challengeMeli.adapter.out.model.ItemRestResponse;
import com.iancinti.challengeMeli.application.port.out.RedeemCouponRepository;
import com.iancinti.challengeMeli.application.port.usecase.RedeemCouponUseCase;
import com.iancinti.challengeMeli.domain.CouponRequest;
import com.iancinti.challengeMeli.domain.CouponResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class RedeemCouponUseCaseTest {

    @Mock
    private RedeemCouponRepository redeemCouponRepository;

    private RedeemCouponUseCase redeemCouponUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        redeemCouponUseCase = new RedeemCouponUseCase(redeemCouponRepository);
    }

    @Test
    void testExecute() {

        CouponRequest request = new CouponRequest(Arrays.asList("item1", "item2", "item3"), 100);
        ItemRestResponse item1Response = new ItemRestResponse("item1", 50.0);
        ItemRestResponse item2Response = new ItemRestResponse("item2", 30.0);
        ItemRestResponse item3Response = new ItemRestResponse("item3", 20.0);
        when(redeemCouponRepository.execute("item1")).thenReturn(Mono.just(item1Response));
        when(redeemCouponRepository.execute("item2")).thenReturn(Mono.just(item2Response));
        when(redeemCouponRepository.execute("item3")).thenReturn(Mono.just(item3Response));

        Mono<CouponResponse> resultMono = redeemCouponUseCase.execute(request);
        CouponResponse result = resultMono.block();

        assertEquals(100.0, result.getTotal());
        assertEquals(Arrays.asList("item1", "item2", "item3"), result.getItems());
        verify(redeemCouponRepository, times(1)).execute("item1");
        verify(redeemCouponRepository, times(1)).execute("item2");
        verify(redeemCouponRepository, times(1)).execute("item3");
    }

    @Test
    void testExecute_EmptyItems() {

        MockitoAnnotations.openMocks(this);
        RedeemCouponUseCase useCase = new RedeemCouponUseCase(redeemCouponRepository);
        CouponRequest request = new CouponRequest(Collections.emptyList(), 100);

        Mono<CouponResponse> resultMono = useCase.execute(request);
        CouponResponse result = resultMono.block();

        if (result != null) {
            assertEquals(0.0, result.getTotal());
            assertEquals(Collections.emptyList(), result.getItems());
        } else {
            fail("Result is null");
        }
        verify(redeemCouponRepository, never()).execute(anyString());
    }

}
