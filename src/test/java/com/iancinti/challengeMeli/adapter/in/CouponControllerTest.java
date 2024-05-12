package com.iancinti.challengeMeli.adapter.in;

import com.iancinti.challengeMeli.coupon.application.port.in.RedeemCouponCommand;
import com.iancinti.challengeMeli.coupon.adapter.in.CouponController;
import com.iancinti.challengeMeli.coupon.domain.Coupon;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
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
        Coupon request = new Coupon(items, amount);
        List<String> responseItems = Arrays.asList("item1", "item3");
        double responseTotal = 50.0;
        VerifiedCoupon response = new VerifiedCoupon(responseItems, responseTotal);
        Mono<VerifiedCoupon> responseMono = Mono.just(response);
        when(redeemCouponCommand.execute(request)).thenReturn(responseMono);

        ResponseEntity<Mono<VerifiedCoupon>> responseEntity = couponController.postCoupon(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseMono, responseEntity.getBody());
        verify(redeemCouponCommand, times(1)).execute(request);
    }

    @Test
    void testPostCoupon_EmptyItems() {

        Coupon request = new Coupon(Collections.emptyList(), 100);
        Mono<VerifiedCoupon> responseMono = Mono.empty();
        when(redeemCouponCommand.execute(request)).thenReturn(responseMono);

        ResponseEntity<Mono<VerifiedCoupon>> responseEntity = couponController.postCoupon(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseMono, responseEntity.getBody());
        verify(redeemCouponCommand, times(1)).execute(request);
    }

    @Test
    void testPostCoupon_ZeroAmount() {

        List<String> items = Arrays.asList("item1", "item2", "item3");
        Coupon request = new Coupon(Collections.singletonList(String.valueOf(items)), 0);
        Mono<VerifiedCoupon> responseMono = Mono.empty();
        when(redeemCouponCommand.execute(request)).thenReturn(responseMono);

        ResponseEntity<Mono<VerifiedCoupon>> responseEntity = couponController.postCoupon(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseMono, responseEntity.getBody());
        verify(redeemCouponCommand, times(1)).execute(request);
    }
}
