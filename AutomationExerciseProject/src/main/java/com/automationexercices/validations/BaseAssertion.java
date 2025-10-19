package com.automationexercices.validations;

import com.automationexercices.FileUtils;
import com.automationexercices.utils.Actions.ElementActions;
import com.automationexercices.utils.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseAssertion {
    protected  WebDriver driver;
    protected  WaitManager waitManager;
    protected  ElementActions elementActions;


    protected BaseAssertion(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        this.elementActions = new ElementActions(driver);
    }
    protected BaseAssertion(){};

    protected abstract void assertTrue(boolean condition, String message);

    protected abstract void assertFalse(boolean condition, String message);

    protected abstract void assertEquals(String expected, String actual, String message);

    public BaseAssertion  Equals(String actual, String expected, String message) {
        assertEquals(expected, actual, message);
        return this;
    }

    public BaseAssertion isElementVisible(By locator)
    {
       boolean flag= waitManager.fluentWait().until(driver1 ->
        {

            try {
                driver1.findElement(locator).isDisplayed();
                return true;

            } catch (Exception e) {
                return false;
            }

        });
        assertTrue(flag, "Element is not visible"+locator);
        return this;
    }

    public BaseAssertion assertPageUrl(String expectedUrl) {
        String actualUrl=driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "Page URL is not as expected"+expectedUrl+" but actual is "+actualUrl);
        return this;
    }

    public BaseAssertion assertPageTitle(String expectedTitle) {
        String actualTitle=driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Page title is not as expected");
        return this;
    }

    public void assertFileExits(String fileName, String message) {
        waitManager.fluentWait().until(
                d-> FileUtils.isFileExists(fileName));
        assertTrue(FileUtils.isFileExists(fileName), message);
    }
}