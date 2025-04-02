package domain.service;

import data.entity.Order;
import data.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(int userId, double totalPrice) {
        return orderRepository.save(userId, totalPrice);
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id);
    }
}
