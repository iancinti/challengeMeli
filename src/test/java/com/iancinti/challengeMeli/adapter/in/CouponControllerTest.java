package com.iancinti.challengeMeli.adapter.in;

import com.iancinti.challengeMeli.coupon.adapter.in.CouponController;
import com.iancinti.challengeMeli.coupon.application.port.in.GetRankingCouponQuery;
import com.iancinti.challengeMeli.coupon.application.port.in.RedeemCouponCommand;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CouponControllerTest {

    @Mock
    private RedeemCouponCommand redeemCouponCommand;

    @Mock
    private GetRankingCouponQuery rankingCouponQuery;

    private CouponController couponController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        couponController = new CouponController(redeemCouponCommand, rankingCouponQuery);
    }

    @Test
    void testPostCoupon() {
        Coupon coupon = new Coupon();
        List<String> items = Arrays.asList("item1", "item2");
        Double total = 100.0;
        VerifiedCoupon verifiedCoupon = new VerifiedCoupon(items, total);
        when(redeemCouponCommand.execute(coupon)).thenReturn(Mono.just(verifiedCoupon));

        ResponseEntity<Mono<VerifiedCoupon>> responseEntity = couponController.postCoupon(coupon);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(verifiedCoupon, responseEntity.getBody().block());
    }

    @Test
    void testGetCoupon() {
        List<String> items = Arrays.asList("item1", "item2");
        Double total = 100.0;
        VerifiedCoupon verifiedCoupon = new VerifiedCoupon(items, total);
        when(rankingCouponQuery.execute()).thenReturn(verifiedCoupon);

        ResponseEntity<VerifiedCoupon> responseEntity = couponController.getCoupon();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(verifiedCoupon, responseEntity.getBody());
    }

}
