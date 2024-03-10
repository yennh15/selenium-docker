package com.yenpractice.pageobjects;


import com.yenpractice.commons.BasePage;
import com.yenpractice.pageuis.CustomerRegistrationPU;
import com.yenpractice.pageuis.PagePU;
import org.openqa.selenium.WebDriver;

public class CustomerRegistrationPO extends BasePage {
    WebDriver driver;

    public CustomerRegistrationPO(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public boolean isAt() {
        return isPageDisplayed(driver, CustomerRegistrationPU.TITLE);
    }

    public void enterFirstName(String firstName) {
        waitForAllElementVisible(driver, CustomerRegistrationPU.FIRSTNAME);
        sendKeyToElement(driver, CustomerRegistrationPU.FIRSTNAME, firstName);
    }

    public void enterLastName(String lastName) {
        waitForAllElementVisible(driver, CustomerRegistrationPU.FIRSTNAME);
        sendKeyToElement(driver, CustomerRegistrationPU.FIRSTNAME, lastName);
    }

    public void enterEmail(String email) {
        waitForAllElementVisible(driver, CustomerRegistrationPU.EMAIL);
        sendKeyToElement(driver, CustomerRegistrationPU.EMAIL, email);
    }

    public void enterPassword(String password) {
        waitForAllElementVisible(driver, CustomerRegistrationPU.PASSWORD);
        sendKeyToElement(driver, CustomerRegistrationPU.PASSWORD, password);
    }

    public void enterStreet(String street) {
        waitForAllElementVisible(driver, CustomerRegistrationPU.STREET);
        sendKeyToElement(driver, CustomerRegistrationPU.STREET, street);
    }

    public void enterCity(String city) {
        waitForAllElementVisible(driver, CustomerRegistrationPU.CITY);
        sendKeyToElement(driver, CustomerRegistrationPU.CITY, city);
    }

    public void chooseState(String state) {
        waitForAllElementVisible(driver, CustomerRegistrationPU.STATE);
        selectItemInDropDown(driver,CustomerRegistrationPU.STATE,state);
    }

    public void enterZip(String zip) {
        waitForAllElementVisible(driver, CustomerRegistrationPU.ZIP);
        sendKeyToElement(driver, CustomerRegistrationPU.ZIP, zip);
    }
    public RegistrationConfirmationPO clickRegister() {
        waitForAllElementVisible(driver, CustomerRegistrationPU.REGISTER);
        clickToElement(driver, CustomerRegistrationPU.REGISTER);
        return PageObjectManager.getRegistrationConfirmationPO(driver);
    }

    public RegistrationConfirmationPO register(String firstName, String lastName, String email, String password, String street, String city, String state, String zip) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        enterStreet(street);
        enterCity(city);
        chooseState(state);
        enterZip(zip);
        return clickRegister();
    }
}
