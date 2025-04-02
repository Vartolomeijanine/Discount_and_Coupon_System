package data.repository;

import data.entity.CouponRedemption;
import java.util.*;

public class CouponRedemptionRepository {
    private final List<CouponRedemption> redemptions = new ArrayList<>();

    public void save(CouponRedemption redemption) {
        redemptions.add(redemption);
    }

    public boolean wasUsedByUser(int couponId, int userId) {
        return redemptions.stream()
                .anyMatch(r -> r.getCouponId() == couponId && r.getUserId() == userId);
    }
}
