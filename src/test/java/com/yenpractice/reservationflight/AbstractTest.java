package com.yenpractice.reservationflight;

import com.yenpractice.listener.MethodListener;
import com.yenpractice.listener.TestListener;
import com.yenpractice.utilities.ConfigUtil;
import com.yenpractice.utilities.TestConstants;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.time.Duration;


public class AbstractTest extends BaseTest {
    protected WebDriver driver;
    private String url;
    private String browser;

    @BeforeTest
    public void initConfig(){
        ConfigUtil.init();
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void initDriver( String browser, ITestContext ctx) throws MalformedURLException {
        log.info("Navigate to the reservation flight");
        driver = (Boolean.parseBoolean(ConfigUtil.get(TestConstants.GRID_ENABLE))) ? initRemoteDriver(browser): initBrowser(browser);
        ctx.setAttribute(TestConstants.DRIVER,driver);
        driver.get(ConfigUtil.get(TestConstants.URL));
        //driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void cleanBrowser() {
        closeBrowserAndDriver(driver);
    }
}
