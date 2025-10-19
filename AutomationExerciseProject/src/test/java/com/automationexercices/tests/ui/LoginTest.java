package com.automationexercices.tests.ui;

import com.automationexercices.apis.UserManagementAPI;
import com.automationexercices.drivers.GUIDriver;

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

@Epic("Automation Exercise - Login")
@Feature("UI User Management")
@Story("Login Scenario")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")

public class LoginTest extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();

    //tests
    @Description("Verify that user can login successfully with valid credentials")
    @Test
    public void validLoginTC()
    {
        new UserManagementAPI().createAccountMinReqFields(
                testData.getJsonData("name"),
                testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password"),
                testData.getJsonData("firstName"),
                testData.getJsonData("lastName"))
                .verifyUserCreatedSuccessfully();

        new SignupLoginPage(driver)
                .navigate()
                .enterLoginEmail(testData.getJsonData("email")+timestamp+"@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickOnLoginButton()
                .navigationBar.verifyUserLabel(testData.getJsonData("name"));


        new UserManagementAPI().deleteAccount(
                testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();
    }

    @Description("Verify that user cannot login using an invalid email")
    @Test
    public void inValidLoginUsingInvalidEmailTC()
    {
        new UserManagementAPI().createAccountMinReqFields(
                        testData.getJsonData("name"),
                        testData.getJsonData("email")+timestamp+"@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))
                .verifyUserCreatedSuccessfully();

        new SignupLoginPage(driver)
                .navigate()
                .enterLoginEmail(testData.getJsonData("email")+"@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickOnLoginButton()
                .verifyLoginErrorMsg(testData.getJsonData("messages.error"));


        new UserManagementAPI().deleteAccount(
                        testData.getJsonData("email")+timestamp+"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();
    }

    @Description("Verify that user cannot login using an invalid password")
    @Test
    public void inValidLoginUsingInvalidPasswordTC()
    {
        new UserManagementAPI().createAccountMinReqFields(
                        testData.getJsonData("name"),
                        testData.getJsonData("email")+timestamp+"@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))
                .verifyUserCreatedSuccessfully();

        new SignupLoginPage(driver)
                .navigate()
                .enterLoginEmail(testData.getJsonData("email")+timestamp+"@gmail.com")
                .enterLoginPassword(testData.getJsonData("password")+timestamp)
                .clickOnLoginButton()
                .verifyLoginErrorMsg(testData.getJsonData("messages.error"));


        new UserManagementAPI().deleteAccount(
                        testData.getJsonData("email")+timestamp+"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();
    }

    //configurations
    @BeforeClass
    protected void beforeClass(){
        testData = new JsonReader("login-data");
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
