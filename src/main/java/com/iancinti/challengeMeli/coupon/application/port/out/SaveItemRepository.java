package com.iancinti.challengeMeli.coupon.application.port.out;

import com.iancinti.challengeMeli.coupon.domain.VerifiedCoupon;

public interface SaveItemRepository {

    void execute(VerifiedCoupon verifiedCoupon);
}
