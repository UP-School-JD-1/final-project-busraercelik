package com.example.restaurantapp.model;


import java.util.UUID;

public class Customer {

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class CustomerBuilder{
        private String customerId;
        private String firstName;
        private String lastName;
        private String email;

        private static CustomerBuilder INSTANCE;

        public static CustomerBuilder create(){
            INSTANCE = new CustomerBuilder();
            //generate a random uuid
            INSTANCE.customerId = UUID.randomUUID().toString();
            return INSTANCE;
        }

        public CustomerBuilder withFirstName(String firstName) {
            INSTANCE.firstName = firstName;
            return INSTANCE;
        }

        public CustomerBuilder withLastName(String lastName) {
            INSTANCE.lastName = lastName;
            return INSTANCE;
        }

        public CustomerBuilder withEmail(String email) {
            INSTANCE.email = email;
            return INSTANCE;
        }

        public Customer build(){
            Customer customer = new Customer();
            customer.customerId = INSTANCE.customerId;
            customer.firstName = INSTANCE.firstName;
            customer.lastName = INSTANCE.lastName;
            customer.email = INSTANCE.email;
            return customer;
        }
    }
}
