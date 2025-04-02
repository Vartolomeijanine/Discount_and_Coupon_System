package data.entity;

public class CouponRedemption {
    private int couponId;
    private int userId;
    private int orderId;

    public CouponRedemption(int couponId, int userId, int orderId) {
        this.couponId = couponId;
        this.userId = userId;
        this.orderId = orderId;
    }

    public int getCouponId() { return couponId; }
    public int getUserId() { return userId; }
    public int getOrderId() { return orderId; }
}
