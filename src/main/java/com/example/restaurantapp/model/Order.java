package com.example.restaurantapp.model;


import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private List<Food> foods;
    private List<Beverage> beverages;

    private OrderStatus orderStatus;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", foods=" + foods +
                ", beverages=" + beverages +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }
    public List<Food> getFoods() {
        return foods;
    }
    public List<Beverage> getBeverages() {
        return beverages;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus status){
        this.orderStatus = status;
    }




    public static class OrderBuilder {
        private Customer customer;
        private List<Food> foods;
        private List<Beverage> beverages;

        private static OrderBuilder INSTANCE;

        public static OrderBuilder create(){
            INSTANCE = new OrderBuilder();
            return INSTANCE;
        }

        public OrderBuilder withCustomer(Customer customer) {
            INSTANCE.customer = customer;
            return INSTANCE;
        }

        public OrderBuilder withBeverages(List<Beverage> beverages) {
            INSTANCE.beverages = beverages;
            return INSTANCE;
        }

        public OrderBuilder withFoods(List<Food> foods) {
            INSTANCE.foods = foods;
            return INSTANCE;
        }

        public Order build() {
            Order newOrder = new Order();
            newOrder.customer = INSTANCE.customer;
            newOrder.foods = INSTANCE.foods;
            newOrder.beverages = INSTANCE.beverages;
            newOrder.setOrderStatus(OrderStatus.NEW);
            return newOrder;
        }
    }

}
