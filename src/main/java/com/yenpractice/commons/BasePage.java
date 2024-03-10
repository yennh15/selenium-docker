package com.yenpractice.commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import com.yenpractice.pageuis.PagePU;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void back(WebDriver driver) {
        driver.navigate().back();
    }

    public void forward(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void waitAlertPresent(WebDriver driver) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitAlertPresent(driver);
        alert.accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitAlertPresent(driver);
        alert.dismiss();
    }

    public String getAlertText(WebDriver driver) {
        waitAlertPresent(driver);
        return alert.getText();
    }

    public void sendKeyToAleTt(WebDriver driver, String text) {
        waitAlertPresent(driver);
        alert.sendKeys(text);
    }

    public void switchWindowByID(WebDriver driver, String parentID) {
        Set<String> windowList = driver.getWindowHandles();
        for (String currentWindowID : windowList) {
            if (!currentWindowID.equals(parentID)) {
                driver.switchTo().window(currentWindowID);
                break;
            }
        }
    }

    public void switchWindowByTitle(WebDriver driver, String title) {
        Set<String> windowList = driver.getWindowHandles();

        for (String currentWindowID : windowList) {
            driver.switchTo().window(currentWindowID);
            if (driver.getTitle().equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(WebDriver driver, String parentTitle) {
        Set<String> windowList = driver.getWindowHandles();

        for (String currentWindowID : windowList) {
            driver.switchTo().window(currentWindowID);
            if (!driver.getTitle().equals(parentTitle)) {
                driver.close();
            }
        }
    }

    public By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public By getByXpath(String locator, String... values) {
        return By.xpath(String.format(locator, (Object[]) values));
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    public WebElement getElement(WebDriver driver, String locator, String... values) {
        return driver.findElement(getByXpath(locator, values));
    }

    public List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    public List<WebElement> getElements(WebDriver driver, String locator, String... values) {
        return driver.findElements(getByXpath(locator, values));
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... values) {
        getElement(driver, locator, values).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String text) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(text);
    }

    public void sendKeyToElement(WebDriver driver, String locator, String text, String... values) {
        getElement(driver, locator, values).clear();
        getElement(driver, locator, values).sendKeys(text);
    }

    public void selectItemInDropDown(WebDriver driver, String locator, String value) {
        select = new Select(getElement(driver, locator));
        select.selectByVisibleText(value);
    }

    public WebElement getSelectedItemInDropDown(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.getFirstSelectedOption();
    }

    public WebElement getSelectedItemInDropDown(WebDriver driver, String locator, String... values) {
        select = new Select(getElement(driver, locator, values));
        return select.getFirstSelectedOption();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.isMultiple();
    }

    public void waitUtilAllItemDisplayed(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfAllElements(getElements(driver, locator)));
    }

    public void waitUtilAllItemDisplayed(WebDriver driver, String locator, String... values) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfAllElements(getElements(driver, locator, values)));
    }

    public void selectItemInCustomDropDown(WebDriver driver, String dropDownLocator, String valueList,
                                           String expectedValue) {
        clickToElement(driver, dropDownLocator);
        sleepInSecond(1);
        waitUtilAllItemDisplayed(driver, valueList);
        sleepInSecond(1);
        List<WebElement> elements = getElements(driver, valueList);
        for (WebElement item : elements) {
            if (item.getText().trim().equals(expectedValue)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                item.click();
                sleepInSecond(1);
            }
        }

    }

    public void sleepInSecond(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getAttributeValue(WebDriver driver, String locator, String attribute) {
        return getElement(driver, locator).getAttribute(attribute);
    }

    public String getAttributeValue(WebDriver driver, String locator, String attribute, String... values) {
        return getElement(driver, locator, values).getAttribute(attribute);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... values) {
        return getElement(driver, locator, values).getText();
    }

    public String getElementTextFromSelectedItem(WebDriver driver, String locator) {
        return getSelectedItemInDropDown(driver, locator).getText();
    }

    public String getElementTextFromSelectedItem(WebDriver driver, String locator, String... values) {
        return getSelectedItemInDropDown(driver, locator, values).getText();
    }

    public int getElementSize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    public int getElementSize(WebDriver driver, String locator, String... values) {
        return getElements(driver, locator, values).size();
    }

    public boolean isSelectedElement(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isSelectedElement(WebDriver driver, String locator, String... values) {
        return getElement(driver, locator, values).isSelected();
    }

    public void checkTheCheckboxOrRadio(WebDriver driver, String locator) {
        if (!isSelectedElement(driver, locator)) {
            getElement(driver, locator).click();
        }
    }

    public void checkTheCheckboxOrRadio(WebDriver driver, String locator, String... values) {
        if (!isSelectedElement(driver, locator, values)) {
            getElement(driver, locator, values).click();
        }
    }

    public void uncheckTheCheckbox(WebDriver driver, String locator) {
        if (isSelectedElement(driver, locator)) {
            getElement(driver, locator).click();
        }
    }

    public void uncheckTheCheckbox(WebDriver driver, String locator, String... values) {
        if (isSelectedElement(driver, locator, values)) {
            getElement(driver, locator, values).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        boolean status;
        try {
            status = getElement(driver, locator).isDisplayed();
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
        boolean status;
        try {
            status = getElement(driver, locator, values).isDisplayed();
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        int count = 0;
        List<WebElement> elements = getElements(driver, locator);
        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0) {
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    count++;
                }
            }
            if (count == 0) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
        int count = 0;
        List<WebElement> elements = getElements(driver, locator, values);
        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0) {
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    count++;
                }
            }
            if (count == 0) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String values) {
        return getElement(driver, locator, values).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public boolean isElementEnabled(WebDriver driver, String locator, String... values) {
        return getElement(driver, locator, values).isEnabled();
    }

    public WebDriver switchToFrameByElement(WebDriver driver, String locator) {
        return driver.switchTo().frame(getElement(driver, locator));
    }

    public WebDriver switchToDefaultContentByElement(WebDriver driver, String locator) {
        return driver.switchTo().defaultContent();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.doubleClick(getElement(driver, locator)).perform();
    }

    public void hoverMouseToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(getElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(getElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        action = new Actions(driver);
        action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        action.sendKeys(getElement(driver, locator), key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor
                .executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
                "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
                originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToElement(WebDriver driver, String locator, String... values) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator, values));
    }

    public void sendKeyToElementByJS(WebDriver driver, String locator, String value) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
                getElement(driver, locator));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        wait = new WebDriverWait(driver, timeout);
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getElement(driver, locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator, values)));
    }

    public void waitForAllElementVisible(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public void waitForAllElementVisible(WebDriver driver, String locator, String... values) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator, values)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... values) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator, values)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator, values)));
    }

    public void waitForAlertPresent(WebDriver driver) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForAlertPresent(WebDriver driver, String... values) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public boolean isPageDisplayed(WebDriver driver, String pageName) {
        waitForAllElementVisible(driver, PagePU.TITLE, pageName);
        return isElementDisplayed(driver, PagePU.TITLE, pageName);
    }


    private Alert alert;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(2);
    private Select select;
    private JavascriptExecutor jsExecutor;
    private Actions action;
}