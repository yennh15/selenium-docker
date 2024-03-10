package com.yenpractice.pageobjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    public static CustomerRegistrationPO customerRegistrationPO;
    public static FlightsConfirmationPO flightsConfirmationPO;
    public static FlightSearchPO flightSearchPO;
    public static RegistrationConfirmationPO registrationConfirmationPO;
    public static SelectFlightsPO selectFlightsPO;

    public static CustomerRegistrationPO getCustomerRegistrationPage(WebDriver driver) {
        customerRegistrationPO = new CustomerRegistrationPO(driver);
        return customerRegistrationPO;
    }

    public static FlightsConfirmationPO getFlightsConfirmationPO(WebDriver driver) {
        flightsConfirmationPO = new FlightsConfirmationPO(driver);
        return flightsConfirmationPO;
    }

    public static FlightSearchPO getFlightSearchPO(WebDriver driver) {
        flightSearchPO = new FlightSearchPO(driver);
        return flightSearchPO;
    }

    public static RegistrationConfirmationPO getRegistrationConfirmationPO(WebDriver driver) {
        registrationConfirmationPO = new RegistrationConfirmationPO(driver);
        return registrationConfirmationPO;
    }

    public static SelectFlightsPO getSelectFlightsPO(WebDriver driver) {
        selectFlightsPO = new SelectFlightsPO(driver);
        return selectFlightsPO;
    }
}
