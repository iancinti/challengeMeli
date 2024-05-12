package com.iancinti.challengeMeli.adapter.in;

import com.iancinti.challengeMeli.application.port.in.RedeemCouponCommand;
import com.iancinti.challengeMeli.domain.CouponRequest;
import com.iancinti.challengeMeli.domain.CouponResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CouponControllerTest {

    @Mock
    private RedeemCouponCommand redeemCouponCommand;

    private CouponController couponController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        couponController = new CouponController(redeemCouponCommand);
    }

    @Test
    void testPostCoupon() {

        List<String> items = Arrays.asList("item1", "item2", "item3");
        int amount = 100;
        CouponRequest request = new CouponRequest(items, amount);
        List<String> responseItems = Arrays.asList("item1", "item3");
        double responseTotal = 50.0;
        CouponResponse response = new CouponResponse(responseItems, responseTotal);
        Mono<CouponResponse> responseMono = Mono.just(response);
        when(redeemCouponCommand.execute(request)).thenReturn(responseMono);

        ResponseEntity<Mono<CouponResponse>> responseEntity = couponController.postCoupon(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseMono, responseEntity.getBody());
        verify(redeemCouponCommand, times(1)).execute(request);
    }

    @Test
    void testPostCoupon_EmptyItems() {

        CouponRequest request = new CouponRequest(Collections.emptyList(), 100);
        Mono<CouponResponse> responseMono = Mono.empty();
        when(redeemCouponCommand.execute(request)).thenReturn(responseMono);

        ResponseEntity<Mono<CouponResponse>> responseEntity = couponController.postCoupon(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseMono, responseEntity.getBody());
        verify(redeemCouponCommand, times(1)).execute(request);
    }

    @Test
    void testPostCoupon_ZeroAmount() {

        List<String> items = Arrays.asList("item1", "item2", "item3");
        CouponRequest request = new CouponRequest(Collections.singletonList(String.valueOf(items)), 0);
        Mono<CouponResponse> responseMono = Mono.empty();
        when(redeemCouponCommand.execute(request)).thenReturn(responseMono);

        ResponseEntity<Mono<CouponResponse>> responseEntity = couponController.postCoupon(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseMono, responseEntity.getBody());
        verify(redeemCouponCommand, times(1)).execute(request);
    }
}
