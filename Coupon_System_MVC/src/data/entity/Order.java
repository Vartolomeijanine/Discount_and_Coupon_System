package data.entity;

public class Order {
    private final int id;
    private final int userId;
    private double totalPrice;

    public Order(int id, int userId, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void applyDiscount(int discountPercent) {
        totalPrice = totalPrice - (totalPrice * discountPercent / 100.0);
    }
}
