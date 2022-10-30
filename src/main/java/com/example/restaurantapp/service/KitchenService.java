package com.example.restaurantapp.service;

import com.example.restaurantapp.model.Beverage;
import com.example.restaurantapp.model.Food;
import com.example.restaurantapp.model.Order;
import com.example.restaurantapp.model.OrderStatus;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class KitchenService {
    private final BlockingQueue<Order> pendingOrders = new LinkedBlockingQueue<>();
    private final BlockingQueue<Order> completedOrders = new LinkedBlockingQueue<>();
    int counter = 0;

    public void placeOrder(Order order) {
        order.setOrderId(++counter);
        order.setOrderStatus(OrderStatus.PLACED);
        try {
            pendingOrders.put(order);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getNextOrder() {
        try {
            return pendingOrders.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToCompletedOrders(Order order) {
        order.setOrderStatus(OrderStatus.READY);
        completedOrders.add(order);
    }

    public Order getCompletedOrder() {
        return completedOrders.poll();
    }

    public void anounceReadyOrders() {
        while (!this.completedOrders.isEmpty()) {
            Order completedOrder = getCompletedOrder();
            System.out.println(String.format(
                    "Dear %s your food [%s] and beverage [%s] is ready! ",
                    completedOrder.getCustomer().getFirstName(),
                    completedOrder.getFoods().stream().map(Food::getDescription).collect(Collectors.joining(",")),
                    completedOrder.getBeverages().stream().map(Beverage::getDescription).collect(Collectors.joining(","))));
            System.out.println(completedOrder);
        }
    }
}
