package com.example.restaurantapp.model;

public enum Food {
    SOUP(1, "red lentil soup", 1_000),
    MAIN_DISH(2, "kebab", 1_000),
    SALAD(3, "caesar salad", 1_000);

    private final long id;
    private final String description;
    private final long millis;

    Food(long id, String description, long millis) {
        this.id = id;
        this.description = description;
        this.millis = millis;
    }

    public long getPreparationTimeForFood() {
        return millis;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static Food fromFoodId(int id) {
        for (Food food : Food.values()) {
            if (food.getId() == id) {
                return food;
            }
        }
        return null;
    }
}
