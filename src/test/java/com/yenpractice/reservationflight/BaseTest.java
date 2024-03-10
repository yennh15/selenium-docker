package com.yenpractice.reservationflight;

import com.yenpractice.listener.TestListener;
import com.yenpractice.utilities.ConfigUtil;
import com.yenpractice.utilities.TestConstants;
import com.yenpractice.utilities.VerificationFailures;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    private WebDriver driver;
    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);


    protected WebDriver initRemoteDriver(String browser) throws MalformedURLException {

        String hubUrl = String.format(ConfigUtil.get(TestConstants.HUB_URL), ConfigUtil.get(TestConstants.HUB_IP));
        Capabilities capabilities;
        if (browser.equalsIgnoreCase("chrome")) {
            capabilities = new ChromeOptions();
        } else {
            capabilities = new FirefoxOptions();
        }
        log.info("This is remote");
        return new RemoteWebDriver(new URL(hubUrl), capabilities);
    }

    protected WebDriver initBrowser(String browser) {
        log.info("This is local");
        if (browser.equalsIgnoreCase("chrome")) {
            // WebDriverManager.chromedriver().clearDriverCache().setup();
            //System.setProperty("webdriver.chrome.driver", projectLocation+ getSlash("browserDrivers") + "chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            //System.setProperty("webdriver.gecko.driver", projectLocation+ getSlash("browserDrivers") + "geckodriver.exe");
            //WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Please enter correct browser");
        }

        return driver;
    }

    private boolean checkTrue(boolean condition) {
        boolean pass = true;
        try {
            if (condition == true) {
                log.info(" -------------------------- PASSED -------------------------- ");
            } else {
                log.info(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyTrue(boolean condition) {
        return checkTrue(condition);
    }

    private boolean checkFailed(boolean condition) {
        boolean pass = true;
        try {
            if (condition == false) {
                log.info(" -------------------------- PASSED -------------------------- ");
            } else {
                log.info(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        return checkFailed(condition);
    }

    private boolean checkEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        return checkEquals(actual, expected);
    }

    protected void closeBrowserAndDriver(WebDriver driver) {
        try {
            // Get ra tên của OS và convert qua chữ thường
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            // Khai báo 1 biến command line để thực thi
            String cmd = "";
            if (driver != null) {
                driver.quit();
            }

            // Quit driver executable file in Task Manager
            if (driver.toString().toLowerCase().contains("chrome")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill chromedriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                }
            } else if (driver.toString().toLowerCase().contains("internetexplorer")) {
                if (osName.toLowerCase().contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driver.toString().toLowerCase().contains("firefox")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill geckodriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                }
            } else if (driver.toString().toLowerCase().contains("edge")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill msedgedriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                }
            }

            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            log.info("---------- QUIT BROWSER SUCCESS ----------");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
