package data.entity;

public class Coupon {
    private final int id;
    private final String code;
    private final int usageLimit;
    private final int discountPercent;
    private final boolean isPersonal;
    private int usageCount = 0;

    public Coupon(int id, String code, int usageLimit, int discountPercent, boolean isPersonal) {
        this.id = id;
        this.code = code;
        this.usageLimit = usageLimit;
        this.discountPercent = discountPercent;
        this.isPersonal = isPersonal;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public boolean isPersonal() {
        return isPersonal;
    }

    public boolean canBeUsed() {
        return usageCount < usageLimit;
    }

    public void incrementUsage() {
        usageCount++;
    }
}
