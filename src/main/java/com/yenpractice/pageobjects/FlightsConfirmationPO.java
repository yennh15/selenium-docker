package com.yenpractice.pageobjects;

import com.yenpractice.commons.BasePage;
import com.yenpractice.pageuis.CustomerRegistrationPU;
import com.yenpractice.pageuis.FlightsConfirmationPU;
import org.openqa.selenium.WebDriver;

public class FlightsConfirmationPO extends BasePage {
    WebDriver driver;

    public FlightsConfirmationPO(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public boolean isAt() {
        return isPageDisplayed(driver, FlightsConfirmationPU.TITLE);
    }

    public String getTax(){
        waitForAllElementVisible(driver, FlightsConfirmationPU.TAX);
        return getElementText(driver, FlightsConfirmationPU.TAX);
    }
    public String getTotalPrice(){
        waitForAllElementVisible(driver, FlightsConfirmationPU.TOTALPRICE);
        return getElementText(driver, FlightsConfirmationPU.TOTALPRICE);
    }

}
