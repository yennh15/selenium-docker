package com.yenpractice.pageobjects;

import com.yenpractice.commons.BasePage;
import com.yenpractice.pageuis.CustomerRegistrationPU;
import com.yenpractice.pageuis.RegistrationConfirmationPU;
import org.openqa.selenium.WebDriver;

public class RegistrationConfirmationPO extends BasePage {

    WebDriver driver;

    public RegistrationConfirmationPO(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public boolean isAt() {
        return isPageDisplayed(driver, RegistrationConfirmationPU.TITLE);
    }

    public boolean isSuccessfulMessageDisplayed(){
        waitForAllElementVisible(driver,RegistrationConfirmationPU.SUCCESSFULMESSAGE );
        return isElementDisplayed(driver, RegistrationConfirmationPU.SUCCESSFULMESSAGE);
    }

    public FlightSearchPO clickGoToFlightSearchButton(){
        waitForAllElementVisible(driver, RegistrationConfirmationPU.GOTOFLIGHTSEARCH);
        clickToElement(driver, RegistrationConfirmationPU.GOTOFLIGHTSEARCH);
        return PageObjectManager.getFlightSearchPO(driver);
    }
}
