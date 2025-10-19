package com.automationexercices.pages.components;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.*;
import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent {
    private GUIDriver driver;

    public NavigationBarComponent(GUIDriver driver ){
        this.driver=driver;
    }


    //locators
    private final By homeButton=By.xpath("//a[text()=' Home']");
    private final By productsButton=By.xpath("//a[text()=' Products']");
    private final By cartButton=By.xpath("//a[text()=' Cart']");
    private final By signupButton=By.xpath("//a[.=' Signup / Login']");
    private final By logoutButton=By.xpath("//a[.=' Logout']");
    private final By testCasesButton=By.xpath("//a[text()=' Test Cases']");
    private final By contactButton=By.xpath("//a[.=' Contact us']");
    private final By apiTestingButton=By.xpath("//a[text()=' API Testing']");
    private final By homePageLabel=By.cssSelector("h1 > span");
    private final By deleteAccountButton=By.xpath("//a[.=' Delete Account']");
    private final By userLabel=By.tagName("b");

    //actions
    @Step("Navigate to the home page")
    public NavigationBarComponent navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on the home button")
    public NavigationBarComponent clickOnHomeButton(){
        driver.element().click(homeButton);
        return this;
    }

    @Step("Click on the products button")
    public ProductsPage clickOnProductsButton(){
        driver.element().click(productsButton);
        return new ProductsPage(driver);
    }

     @Step("Click on the cart button")
    public CartPage clickOnCartButton(){
        driver.element().click(cartButton);
        return new CartPage(driver);
    }

     @Step("Click on the signup button")
    public SignupLoginPage clickOnSignupButton(){
        driver.element().click(signupButton);
        return new SignupLoginPage(driver);
    }

     @Step("Click on the logout button")
    public LogoutPage clickOnLogoutButton(){
        driver.element().click(logoutButton);
        return new LogoutPage(driver);
    }

     @Step("Click on the test cases button")
    public TestCasesPage clickOnTestCasesButton(){
        driver.element().click(testCasesButton);
        return new TestCasesPage(driver);
    }

     @Step("Click on the contact button")
    public ContactPage clickOnContactButton(){
        driver.element().click(contactButton);
        return new ContactPage(driver);
    }

     @Step("Click on the delete account button")
    public DeleteAccountPage clickOnDeleteAccountButton(){
        driver.element().click(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }


    //validations
     @Step("Verify that the home page label is displayed")
    public NavigationBarComponent verifyHomePage(){
         driver.verification().isElementVisible(homePageLabel);
         return this;
    }

     @Step("Verify that the user label is displayed")
    public NavigationBarComponent verifyUserLabel(String expectedName)
     {
         String actualName=driver.element().getText(userLabel);
         LogsManager.info("Actual user name is: {}", actualName);
            driver.verification().Equals(actualName, expectedName,"User name is not as expected");
            return this;
     }

}
