package com.example.restaurantapp.model;

public enum Beverage {
    WATER(1, "500ml water", 1_000),
    COLA(2, "300ml coca cola", 1_000),
    TEA(3, "small glass", 1_000),
    COFFEE(4, "turkish coffee", 1_000),
    JUICE(5, "1 glass of fresh orange juice", 1_000);


    private final long id;
    private final String description;
    private final long preparationTime;

    Beverage(long id, String description, long preparationTime) {
        this.id = id;
        this.description = description;
        this.preparationTime = preparationTime;
    }
    public long getPreparationTime() {
        return preparationTime;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static Beverage fromBeverageId(int id) {
        for (Beverage beverage : Beverage.values()) {
            if (beverage.getId() == id) {
                return beverage;
            }
        }
        return null;
    }
}
