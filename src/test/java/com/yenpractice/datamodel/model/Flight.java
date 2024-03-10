package com.yenpractice.datamodel.model;

public record Flight(String passengers,
                     String departureFrom,
                     String arriveIn,
                     String departure,
                     String arrival,
                     String tax,
                     String totlaPrice) {
}
