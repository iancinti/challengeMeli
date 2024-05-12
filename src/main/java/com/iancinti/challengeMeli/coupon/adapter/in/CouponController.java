package com.iancinti.challengeMeli.coupon.adapter.in;

import com.iancinti.challengeMeli.coupon.application.port.in.RedeemCouponCommand;
import com.iancinti.challengeMeli.coupon.domain.CouponRequest;
import com.iancinti.challengeMeli.coupon.domain.CouponResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final RedeemCouponCommand redeemCouponCommand;

    public CouponController(RedeemCouponCommand redeemCouponCommand) {
        this.redeemCouponCommand = redeemCouponCommand;
    }

    @PostMapping("/")
    public ResponseEntity<Mono<CouponResponse>> postCoupon(@RequestBody CouponRequest couponRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(redeemCouponCommand.execute(couponRequest));
    }
}


