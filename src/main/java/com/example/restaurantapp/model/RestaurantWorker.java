package com.example.restaurantapp.model;

public abstract class RestaurantWorker {

    private long id;
    private String name;
    private Status status;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public RestaurantWorker setId(long id) {
        this.id = id;
        return this;
    }

    public RestaurantWorker setName(String name) {
        this.name = name;
        return this;
    }

    public RestaurantWorker setStatus(Status status) {
        this.status = status;
        return this;
    }
}
