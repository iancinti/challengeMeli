package com.iancinti.challengeMeli.coupon.adapter.in;

import com.iancinti.challengeMeli.coupon.application.port.in.GetRankingCouponQuery;
import com.iancinti.challengeMeli.coupon.application.port.in.RedeemCouponCommand;
import com.iancinti.challengeMeli.coupon.domain.Coupon;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final RedeemCouponCommand redeemCouponCommand;
    private final GetRankingCouponQuery rankingCouponQuery;

    @Autowired
    public CouponController(RedeemCouponCommand redeemCouponCommand, GetRankingCouponQuery rankingCouponQuery) {
        this.redeemCouponCommand = redeemCouponCommand;
        this.rankingCouponQuery = rankingCouponQuery;
    }

    @PostMapping
    public ResponseEntity<Mono<VerifiedCoupon>> postCoupon(@RequestBody Coupon coupon) {
        return ResponseEntity.status(HttpStatus.CREATED).body(redeemCouponCommand.execute(coupon));
    }

    @GetMapping
    public ResponseEntity<VerifiedCoupon> getCoupon() {
        return ResponseEntity.status(HttpStatus.OK).body(rankingCouponQuery.execute());
    }

}


