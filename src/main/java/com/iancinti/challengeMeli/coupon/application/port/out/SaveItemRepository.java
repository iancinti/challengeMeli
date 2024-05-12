package com.iancinti.challengeMeli.coupon.application.port.out;

import com.iancinti.challengeMeli.coupon.domain.CouponResponse;

public interface SaveItemRepository {

    void execute(CouponResponse couponResponse);
}
