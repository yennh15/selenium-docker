package com.yenpractice.reservationflight;

import com.yenpractice.datamodel.model.Customer;
import com.yenpractice.datamodel.model.Flight;
import com.yenpractice.pageobjects.*;
import com.yenpractice.utilities.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    public CustomerRegistrationPO customerRegistrationPO;
    public RegistrationConfirmationPO registrationConfirmationPO;
    public FlightSearchPO flightSearchPO;
    public SelectFlightsPO selectFlightPO;
    public FlightsConfirmationPO flightsConfirmationPO;
    public Customer customerTestData ;
    public Flight flightTestData ;
            //= JsonUtil.getCustomerData(".//src/test/java/com/yenpractice/datamodel/data/customer_A.json");

    @Test
    @Parameters({"customerDataPath", "flightDataPath" })
    public void test(String customerDataPath, String flightDataPath) {
        customerTestData = JsonUtil.getCustomerData(customerDataPath);
        flightTestData = JsonUtil.getFlightData(flightDataPath);
        customerRegistrationPO = PageObjectManager.getCustomerRegistrationPage(driver);
        log.info("Verify navigate successfully");
        Assert.assertTrue(customerRegistrationPO.isAt());
        log.info("Register {}", customerTestData.firstName());
        registrationConfirmationPO = customerRegistrationPO.register(customerTestData.firstName() ,
                customerTestData.lastName(),
                 customerTestData.email(), customerTestData.password(), customerTestData.street(), customerTestData.city(),
                customerTestData.state(), customerTestData.zip());
        log.info("Verify register successfully");
        Assert.assertTrue(registrationConfirmationPO.isAt());
        Assert.assertTrue(registrationConfirmationPO.isSuccessfulMessageDisplayed());
        log.info("Click flight search");
        flightSearchPO = registrationConfirmationPO.clickGoToFlightSearchButton();
        log.info("Verify at select flights");
        Assert.assertTrue(flightSearchPO.isAt());
        log.info("Choose flight criteria and search");
        selectFlightPO= flightSearchPO.searchFlight("Two","New York","London");
        log.info("Verify at search flights");
        Assert.assertTrue(selectFlightPO.isAt());
        log.info("Choose departure and arrival");
        flightsConfirmationPO= selectFlightPO.selectFlight("ba-business","ba-business");
        log.info("Verify at flight confirmation");
        Assert.assertTrue(flightsConfirmationPO.isAt());
        log.info("Verify Tax");
        Assert.assertEquals(flightsConfirmationPO.getTax(),"$89 USD");
        log.info("Verify Total Price");
        //Assert.assertEquals(flightsConfirmationPO.getTotalPrice(),flightTestData.totlaPrice());
        verifyEquals(flightsConfirmationPO.getTotalPrice(),flightTestData.totlaPrice());
    }

    @Test
    @Parameters({"customerDataPath", "flightDataPath" })
    public void testOtherCustomer(String customerDataPath, String flightDataPath) {
        customerTestData = JsonUtil.getCustomerData(customerDataPath);
        flightTestData = JsonUtil.getFlightData(flightDataPath);
        customerRegistrationPO = PageObjectManager.getCustomerRegistrationPage(driver);
        log.info("Verify navigate successfully");
        Assert.assertTrue(customerRegistrationPO.isAt());
        log.info("Register {}", customerTestData.firstName());
        registrationConfirmationPO = customerRegistrationPO.register(customerTestData.firstName() ,
                customerTestData.lastName(),
                customerTestData.email(), customerTestData.password(), customerTestData.street(), customerTestData.city(),
                customerTestData.state(), customerTestData.zip());
        log.info("Verify register successfully");
        Assert.assertTrue(registrationConfirmationPO.isAt());
        Assert.assertTrue(registrationConfirmationPO.isSuccessfulMessageDisplayed());
        log.info("Click flight search");
        flightSearchPO = registrationConfirmationPO.clickGoToFlightSearchButton();
        log.info("Verify at select flights");
        Assert.assertTrue(flightSearchPO.isAt());
        log.info("Choose flight criteria and search");
        selectFlightPO= flightSearchPO.searchFlight("Two","New York","London");
        log.info("Verify at search flights");
        Assert.assertTrue(selectFlightPO.isAt());
        log.info("Choose departure and arrival");
        flightsConfirmationPO= selectFlightPO.selectFlight("ba-business","ba-business");
        log.info("Verify at flight confirmation");
        Assert.assertTrue(flightsConfirmationPO.isAt());
        log.info("Verify Tax");
        Assert.assertEquals(flightsConfirmationPO.getTax(),"$89 USD");
        log.info("Verify Total Price");
        //Assert.assertEquals(flightsConfirmationPO.getTotalPrice(),flightTestData.totlaPrice());
        verifyEquals(flightsConfirmationPO.getTotalPrice(),flightTestData.totlaPrice());
    }



}
