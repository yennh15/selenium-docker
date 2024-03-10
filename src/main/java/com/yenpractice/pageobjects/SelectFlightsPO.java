package com.yenpractice.pageobjects;

import com.yenpractice.commons.BasePage;
import com.yenpractice.pageuis.RegistrationConfirmationPU;
import com.yenpractice.pageuis.SelectFlightsPU;
import org.openqa.selenium.WebDriver;

public class SelectFlightsPO extends BasePage {

    WebDriver driver;

    public SelectFlightsPO(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public boolean isAt() {
        return isPageDisplayed(driver, SelectFlightsPU.TITLE);
    }

    public void selectDeparture(String option) {
        waitForAllElementVisible(driver, SelectFlightsPU.DEPARTURE, option);
        checkTheCheckboxOrRadio(driver, SelectFlightsPU.DEPARTURE, option);
    }

    public void selectArival(String option) {
        waitForAllElementVisible(driver, SelectFlightsPU.ARRIVAL, option);
        checkTheCheckboxOrRadio(driver, SelectFlightsPU.ARRIVAL, option);
    }

    public FlightsConfirmationPO clickFlightConfirmButton(){
        waitForElementClickable(driver, SelectFlightsPU.SELECTFLIGHT);
        clickToElement(driver, SelectFlightsPU.SELECTFLIGHT);
        return PageObjectManager.getFlightsConfirmationPO(driver);
    }

    public FlightsConfirmationPO selectFlight(String departure, String arrival){
        selectDeparture(departure);
        selectArival(arrival);
        return clickFlightConfirmButton();

    }
}
