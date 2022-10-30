package com.example.restaurantapp.dao;

import com.example.restaurantapp.model.Beverage;
import com.example.restaurantapp.model.Order;

import java.sql.*;

public class OrderRepo {
    private static final String CREATE_ORDER_SQL = "CREATE TABLE Order"
            + "("
            + "ID INT PRIMARY KEY NOT NULL"
            + "FOOD_ID INT PRIMARY KEY NOT NULL"
            + "BEVERAGE_ID INT PRIMARY KEY NOT NULL"
            + ")";

    private static final String SQL_UPDATE = "UPDATE Order SET Food_id=? WHERE Order_id=?";

   public void saveOrder(Order order) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/restaurant-db", "postgres", "password");
             PreparedStatement statement = conn.prepareStatement(SQL_UPDATE)) {
            for (Beverage beverage: order.getBeverages()) {
                statement.setLong(1, beverage.getId());
            }
            statement.setInt(2, order.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
