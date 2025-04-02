package controller;

import data.entity.Order;
import domain.service.OrderService;

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order createOrder(int userId, double totalPrice) {
        return orderService.createOrder(userId, totalPrice);
    }

    public Order getOrderById(int id) {
        return orderService.getOrderById(id);
    }
}
