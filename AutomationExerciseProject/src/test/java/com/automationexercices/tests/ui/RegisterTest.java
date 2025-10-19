package com.automationexercices.tests.ui;

import com.automationexercices.apis.UserManagementAPI;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.RegisterPage;
import com.automationexercices.pages.SignupLoginPage;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise - Register")
@Feature("UI User Management")
@Story("Register Scenario")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")
public class RegisterTest extends BaseTest {
    String timestamp= TimeManager.getSimpleTimestamp();

        //Tests
    @Description("Verify that user can register successfully with valid data")
    @Test
    public void validSignUpTC(){
        new SignupLoginPage(driver)
                .navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email")+ timestamp+"@gmail.com")
                .clickOnSignupButton();
                new RegisterPage(driver)
                        .fillRegisterForm(
                                testData.getJsonData("genderMale"),
                                testData.getJsonData("password"),
                                testData.getJsonData("day"),
                                testData.getJsonData("month"),
                                testData.getJsonData("year"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"),
                                testData.getJsonData("company"),
                                testData.getJsonData("address1"),
                                testData.getJsonData("address2"),
                                testData.getJsonData("country"),
                                testData.getJsonData("state"),
                                testData.getJsonData("city"),
                                testData.getJsonData("zipcode"),
                                testData.getJsonData("mobileNumber"))

                .clickCreateAccount()
                .verifyAccountCreated();

        new UserManagementAPI().deleteAccount(
                        testData.getJsonData("email")+timestamp+"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();
    }

    @Description("Verify that user cannot register with an existing email")
    @Test
    public void verifyErrorMessageWhenAccountCreatedBefore(){
        //precondition > create a user account
        new UserManagementAPI()
                .createAccount(
                        testData.getJsonData("name"),
                        testData.getJsonData("email")+timestamp+"@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("genderMale"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("company"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber"))
                .verifyUserCreatedSuccessfully();

        new SignupLoginPage(driver)
                .navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email")+ timestamp+"@gmail.com")
                .clickOnSignupButton()
                .verifySignupErrorMsg(testData.getJsonData("messages.error"));

        new UserManagementAPI().deleteAccount(
                        testData.getJsonData("email")+timestamp+"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();

    }

    //configurations

    @BeforeClass
    protected void beforeClass(){
        testData = new JsonReader("register-data");
    }

    @BeforeMethod
    public void setUp(){
        driver=new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @AfterMethod
    public void tearDown(){
        driver.quitDriver();
    }

}
