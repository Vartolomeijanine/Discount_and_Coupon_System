package data.repository;

import data.entity.Order;
import java.util.*;

public class OrderRepository {
    private final Map<Integer, Order> orders = new HashMap<>();
    private int nextId = 1;

    public Order save(int userId, double totalPrice) {
        Order order = new Order(nextId++, userId, totalPrice);
        orders.put(order.getId(), order);
        return order;
    }

    public Order findById(int id) {
        return orders.get(id);
    }
}
