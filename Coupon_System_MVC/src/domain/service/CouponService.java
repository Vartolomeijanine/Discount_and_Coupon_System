package domain.service;

import data.entity.*;
import data.repository.*;

public class CouponService {
    private final CouponRepository couponRepo;
    private final CouponRedemptionRepository redemptionRepo;

    public CouponService(CouponRepository couponRepo, CouponRedemptionRepository redemptionRepo) {
        this.couponRepo = couponRepo;
        this.redemptionRepo = redemptionRepo;
    }

    public boolean applyCoupon(Order order, User user, String code) {
        Coupon coupon = couponRepo.findByCode(code);
        if (coupon == null || !coupon.canBeUsed()) return false;

        if (coupon.isPersonal() && redemptionRepo.wasUsedByUser(coupon.getId(), user.getId())) {
            return false;
        }

        order.applyDiscount(coupon.getDiscountPercent());
        coupon.incrementUsage();
        redemptionRepo.save(new CouponRedemption(coupon.getId(), user.getId(), order.getId()));
        return true;
    }
}
