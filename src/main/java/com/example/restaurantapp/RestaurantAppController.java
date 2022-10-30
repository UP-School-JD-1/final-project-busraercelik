package com.example.restaurantapp;

import com.example.restaurantapp.model.*;
import com.example.restaurantapp.service.ChefService;
import com.example.restaurantapp.service.KitchenService;
import com.example.restaurantapp.service.WaiterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class RestaurantAppController {

    public static final Logger LOGGER = LoggerFactory.getLogger(RestaurantAppController.class);

    private static KitchenService kitchenService;
    private static WaiterService waiterService;
    private static ChefService chefService;

    public static void main(String[] args) {
        initiateApplication();
        executeApplication(new Scanner(System.in));
    }

    //set up the services
    private static void initiateApplication() {
        kitchenService = new KitchenService();
        chefService = new ChefService(List.of(
                new Chef().setId(1).setName("Mausam").setStatus(Status.FREE),
                new Chef().setId(2).setName("Betul").setStatus(Status.FREE)
        ), kitchenService);
        waiterService = new WaiterService(List.of(
                new Waiter().setId(1).setName("John").setStatus(Status.FREE),
                new Waiter().setId(2).setName("Jacob").setStatus(Status.FREE)
        ), kitchenService);
        chefService.startCookingNow();
    }

    //run the application - ask for user input
    private static void executeApplication(Scanner sc) {
        while (true) {
            System.out.print("Press 1 to check for completed orders, 2 to place a new order :");
            String answer = sc.nextLine();

            if (answer.equals("1")) kitchenService.anounceReadyOrders();
            else askUserForOrder(sc);

            System.out.print("Do you want to exit? Y/N: ");
            answer = sc.nextLine();

            if (answer.equalsIgnoreCase("Y")) break;
        }
    }

    private static void askUserForOrder(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.println("What would you like to order in main food?");
        System.out.println("1-soup, 2-main dish, 3-salad 4-none");
        int orderFoodId = sc.nextInt();

        System.out.println("Okay, now would would you want as drink?");
        System.out.println("1-water, 2-cola, 3-tea 4-coffee, 5-juice, 6-none");
        int orderDrinkId = sc.nextInt();
        sc.nextLine();

        Customer customer = Customer.CustomerBuilder.create().withFirstName(name).build();
        Order order = Order.OrderBuilder.create()
                .withCustomer(customer)
                .withBeverages(List.of(Objects.requireNonNull(Beverage.fromBeverageId(orderDrinkId))))
                .withFoods(List.of(Objects.requireNonNull(Food.fromFoodId(orderFoodId))))
                .build();

        Waiter waiter = waiterService.getWaiter();
        Order placedOrder = waiterService.takeOrder(waiter, order);

        //show placed order detail
        System.out.println(String.format(" Dear %s , your order with id : %s has been placed.",
                customer.getFirstName(), placedOrder.getOrderId()));
    }

}
