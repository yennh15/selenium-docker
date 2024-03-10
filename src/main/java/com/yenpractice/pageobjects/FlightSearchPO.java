package com.yenpractice.pageobjects;

import com.yenpractice.commons.BasePage;
import com.yenpractice.pageuis.FlightSearchPU;
import org.openqa.selenium.WebDriver;

public class FlightSearchPO extends BasePage {
    WebDriver driver;

    public FlightSearchPO(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public boolean isAt() {
        return isPageDisplayed(driver, FlightSearchPU.TITLE);
    }

    public void selectRoundTrip(){
        waitForAllElementVisible(driver, FlightSearchPU.ROUNDTRIP);
        checkTheCheckboxOrRadio(driver, FlightSearchPU.ROUNDTRIP);
    }
    public void selectPassengers(String passengers){
        waitForAllElementVisible(driver, FlightSearchPU.PASSENGERS);
        selectItemInDropDown(driver, FlightSearchPU.PASSENGERS,passengers);
    }
    public void selectDeparturingFrom(String departFrom){
        waitForAllElementVisible(driver, FlightSearchPU.DEPARTFROM);
        selectItemInDropDown(driver, FlightSearchPU.DEPARTFROM,departFrom);
    }
    public void selectArrivingIn(String arriveIn){
        waitForAllElementVisible(driver, FlightSearchPU.ARRIVEIN);
        selectItemInDropDown(driver, FlightSearchPU.ARRIVEIN,arriveIn);
    }
    public void selectServiceClass(){
        waitForAllElementVisible(driver, FlightSearchPU.FIRSTSERVICECLASS);
        checkTheCheckboxOrRadio(driver, FlightSearchPU.FIRSTSERVICECLASS);
    }


    public SelectFlightsPO clickSearchFlightButton(){
        waitForElementClickable(driver,FlightSearchPU.SEARCHFLIGHT);
        clickToElement(driver,FlightSearchPU.SEARCHFLIGHT);
        return PageObjectManager.getSelectFlightsPO(driver);
    }

    public SelectFlightsPO searchFlight(String passengers, String departFrom, String arriveIn){
        selectRoundTrip();
        selectPassengers(passengers);
        selectDeparturingFrom(departFrom);
        selectArrivingIn(arriveIn);
        selectServiceClass();
        return clickSearchFlightButton();
    }
}
