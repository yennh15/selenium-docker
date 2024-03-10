package com.yenpractice.datamodel.model;

public record Customer(String firstName,
                       String lastName,
                       String email,
                       String password,
                       String street,
                       String city,
                       String state,
                       String zip) {
}
