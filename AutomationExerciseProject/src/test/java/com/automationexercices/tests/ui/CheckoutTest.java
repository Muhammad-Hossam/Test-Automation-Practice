package com.automationexercices.tests.ui;

import com.automationexercices.apis.UserManagementAPI;
import com.automationexercices.drivers.GUIDriver;

import com.automationexercices.pages.CartPage;
import com.automationexercices.pages.ProductsPage;
import com.automationexercices.pages.SignupLoginPage;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("Automation Exercise - Checkout")
@Feature("UI Checkout Management")
@Story("Add Product To Checkout")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")

public class CheckoutTest extends BaseTest {
    String timestamp= TimeManager.getSimpleTimestamp();


    //Scenario
    @Test()
    public void registerNewAccount()
    {
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

    }
    @Test(dependsOnMethods = "registerNewAccount")
    public void loginToAccount()
    {
        new SignupLoginPage(driver)
                .navigate()
                .enterLoginEmail(testData.getJsonData("email")+timestamp+"@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickOnLoginButton()
                .navigationBar.verifyUserLabel(testData.getJsonData("name"));
    }
    @Test(dependsOnMethods = {"loginToAccount", "registerNewAccount"})
    public void addProductsToCart()
    {
        new ProductsPage(driver)
                .navigate()
                .addProductToCart(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("message.cartAdded"))
                .clickViewCart()
                .verifyProductDetails(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"));


    }
    @Test(dependsOnMethods = {"addProductsToCart","loginToAccount","registerNewAccount"})
    public void proceedToCheckout()
    {
        new CartPage(driver)
                .clickProceedToCheckout()
                .validateDeliveryAddress(
                        testData.getJsonData("genderMale"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("company"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("city"),
                        testData.getJsonData("state"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("country"),
                        testData.getJsonData("mobileNumber"))
                .validateBillingAddress(
                        testData.getJsonData("genderMale"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("company"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("city"),
                        testData.getJsonData("state"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("country"),
                        testData.getJsonData("mobileNumber"));
    }

    @Test(dependsOnMethods = {"proceedToCheckout","addProductsToCart","loginToAccount","registerNewAccount"})
    public void deleteAccount()
    {
        new UserManagementAPI()
                .deleteAccount(
                        testData.getJsonData("email")+timestamp+"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();
    }



    //configurations
    @BeforeClass
    protected void setUP(){
        testData = new JsonReader("checkout-data");
        driver=new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @AfterClass
    public void tearDown(){
        driver.quitDriver();
    }

}
