package com.example.restaurantapp.service;

import com.example.restaurantapp.model.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

public class ChefService {
    private List<RestaurantWorker> chefs;
    private KitchenService kitchenService;

    public ChefService(List<RestaurantWorker> chefs, KitchenService kitchenService) {
        this.chefs = chefs;
        this.kitchenService = kitchenService;
    }

    //wait until a chef is free or return the first free chef
    public Chef getChef() {
        return (Chef) chefs.stream()
                .filter(restaurantWorker -> Status.FREE==restaurantWorker.getStatus()).findFirst().orElseThrow();
    }

    public void startCookingNow() {
        Thread thread = new Thread(this::startCooking);
        thread.setName("Cook-Thread");
        thread.start();
    }
    private void startCooking() {
        //System.out.println(String.format("[%s] - cooking started..", Thread.currentThread().getName()));
        while (true) {
            Order nextOrder = kitchenService.getNextOrder();//wait until there is a new order
            Chef chef = getChef();
            chef.setStatus(Status.BUSY);

            nextOrder.setOrderStatus(OrderStatus.COOKING);
            //System.out.println(String.format("cooking order number : %s", nextOrder.getOrderId()));
            //wait for some time
            nextOrder.getFoods().forEach(food -> {
                try {
                    //System.out.println(String.format("cooking : %s ", food.getDescription()));
                    Thread.sleep(food.getPreparationTimeForFood());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            nextOrder.getBeverages().forEach(beverage -> {
                try {
                    //System.out.println(String.format("cooking : %s ", beverage.getDescription()));
                    Thread.sleep(beverage.getPreparationTime());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            chef.setStatus(Status.FREE);
            nextOrder.setOrderStatus(OrderStatus.READY);
            kitchenService.addToCompletedOrders(nextOrder);
            //System.out.println(String.format("Finished cooking order number : %s",nextOrder.getOrderId()));
        }
    }
}
