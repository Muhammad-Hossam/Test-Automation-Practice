package com.automationexercices.validations;

import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

//soft assertion
public class Validation extends BaseAssertion {
    private static SoftAssert softAssert=new SoftAssert();
    private static boolean used=false;

    public Validation() {
        super();
    }

    public Validation(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        used=true; //mark that assertion was used
        softAssert.assertTrue(condition, message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        used=true;
        softAssert.assertFalse(condition, message);
    }

    @Override
    protected void assertEquals(String expected, String actual, String message) {
        used=true;
        softAssert.assertEquals(expected, actual, message);
    }

    public static void assertAll(ITestResult result){
        if (!used) return; //if no assertion was used, do nothing
        try {
            softAssert.assertAll();

        }
        catch (AssertionError e){
                LogsManager.error(e.getMessage());
                result.setStatus(ITestResult.FAILURE);
                result.setThrowable(e);
        }
        finally {
                softAssert = new SoftAssert(); //reset soft assertion
        }
    }
}
