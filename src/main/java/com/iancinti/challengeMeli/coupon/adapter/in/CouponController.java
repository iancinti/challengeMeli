package com.iancinti.challengeMeli.coupon.adapter.in;

import com.iancinti.challengeMeli.coupon.application.port.in.GetRankingCouponQuery;
import com.iancinti.challengeMeli.coupon.application.port.in.RedeemCouponCommand;
import com.iancinti.challengeMeli.coupon.domain.Coupon;
import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    public CouponController(RedeemCouponCommand redeemCouponCommand, GetRankingCouponQuery rankingCouponQuery) {
        this.redeemCouponCommand = redeemCouponCommand;
        this.rankingCouponQuery = rankingCouponQuery;
    }

    @PostMapping
    public ResponseEntity<Mono<VerifiedCoupon>> postCoupon(@RequestBody Coupon coupon) {
        logger.info("Solicitud recibida para crear cupón");
        Mono<VerifiedCoupon> response = redeemCouponCommand.execute(coupon);
        logger.info("Respondiendo con el cupón creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<VerifiedCoupon> getCoupon() {
        logger.info("Se recibió una solicitud para obtener el ranking");
        VerifiedCoupon response = rankingCouponQuery.execute();
        logger.info("Respondiendo con ranking");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

