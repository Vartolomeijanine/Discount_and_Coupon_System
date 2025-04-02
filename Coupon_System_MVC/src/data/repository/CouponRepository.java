package data.repository;

import data.entity.Coupon;
import java.util.*;


public class CouponRepository {
    private final Map<String, Coupon> coupons = new HashMap<>();

    public void save(Coupon coupon) {
        coupons.put(coupon.getCode(), coupon);
    }

    public Coupon findByCode(String code) {
        return coupons.get(code);
    }
}
