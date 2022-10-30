package com.example.restaurantapp.service;

import com.example.restaurantapp.model.*;

import java.util.List;

public class WaiterService {
    private final List<RestaurantWorker> waiters;
    private final KitchenService kitchenService;

    public WaiterService(List<RestaurantWorker> waiters, KitchenService kitchenService) {
        this.waiters = waiters;
        this.kitchenService = kitchenService;
    }

    //get a waiter or wait until a free waiter is available
    public Waiter getWaiter() {
        return (Waiter) waiters.stream()
                .filter(restaurantWorker -> Status.FREE==restaurantWorker.getStatus()).findFirst().orElseThrow();
    }

    // - give order an uuid
    // - find a free chef
    // - assign the order to the chef
    // - place the order data in a queue
    public Order takeOrder(Waiter waiter, Order order) {
        waiter.setStatus(Status.BUSY);
        kitchenService.placeOrder(order);
        waiter.setStatus(Status.FREE);
        return order;
    }

}
