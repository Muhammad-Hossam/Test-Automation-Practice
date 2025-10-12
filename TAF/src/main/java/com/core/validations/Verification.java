package com.core.validations;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

//hard assertion
public class Verification extends BaseAssertion {

    public Verification(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);

    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    @Override
    protected void assertEquals(String expected, String actual, String message) {
        Assert.assertEquals(expected, actual, message);
    }
}
