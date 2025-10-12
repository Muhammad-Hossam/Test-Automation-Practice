package com.core.validations;

import com.core.utils.Actions.ElementActions;
import com.core.utils.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseAssertion {
    protected final WebDriver driver;
    protected final WaitManager waitManager;
    protected final ElementActions elementActions;


    protected BaseAssertion(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        this.elementActions = new ElementActions(driver);
    }

    protected abstract void assertTrue(boolean condition, String message);

    protected abstract void assertFalse(boolean condition, String message);

    protected abstract void assertEquals(String expected, String actual, String message);

    protected void Equals(String actual, String expected, String message) {
        assertEquals(expected, actual, message);
    }

    protected void isElementVisible(By locator) {
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
    }

    protected void assertPageUrl(String expectedUrl) {
        String actualUrl=driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "Page URL is not as expected"+expectedUrl+" but actual is "+actualUrl);
    }

    protected void assertPageTitle(String expectedTitle) {
        String actualTitle=driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Page title is not as expected");
    }


}