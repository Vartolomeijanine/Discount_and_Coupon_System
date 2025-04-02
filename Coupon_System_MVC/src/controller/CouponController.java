package controller;

import data.entity.Order;
import data.entity.User;
import domain.service.CouponService;

public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    public boolean applyCoupon(Order order, User user, String code) {
        return couponService.applyCoupon(order, user, code);
    }
}
