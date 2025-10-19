package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupLoginPage {
    public NavigationBarComponent navigationBar;

    private GUIDriver driver;
    private final String loginEndpoint="/login";

    public SignupLoginPage(GUIDriver driver) {
        this.driver = driver;
        this.navigationBar = new NavigationBarComponent(driver);
    }

    //locators
    private final By loginEmail= By.cssSelector("[data-qa=\"login-email\"]");
    private final By loginPassword= By.cssSelector("[data-qa=\"login-password\"]");
    private final By loginButton= By.cssSelector("[data-qa=\"login-button\"]");

    private final By signupName= By.cssSelector("[data-qa=\"signup-name\"]");
    private final By signupEmail= By.cssSelector("[data-qa=\"signup-email\"]");
    private final By signupButton= By.cssSelector("[data-qa=\"signup-button\"]");

    private final By loginLabel= By.xpath("//h2[.=\"Login to your account\"]");
    private final By loginError= By.cssSelector(".login-form p");
    private final By signupError= By.cssSelector(".signup-form p");



    //actions
    @Step("Navigate to Register/Login Page")
    public SignupLoginPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+loginEndpoint);
        return this;
    }


    @Step("Enter name {email} in login field")
    public SignupLoginPage enterLoginEmail(String email){
        driver.element().type(loginEmail,email);
        return this ;
    }

     @Step("Enter password {password} in login field")
    public SignupLoginPage enterLoginPassword(String password){
        driver.element().type(loginPassword,password);
        return this ;
    }
     @Step("Click on login button")
    public SignupLoginPage clickOnLoginButton(){
        driver.element().click(loginButton);
        return this;
    }

    @Step("Enter name {name} in signup field")
    public SignupLoginPage enterSignupName(String name){
        driver.element().type(signupName,name);
        return this ;
    }

     @Step("Enter email {email} in signup field")
    public SignupLoginPage enterSignupEmail(String email){
        driver.element().type(signupEmail,email);
        return this ;
    }

     @Step("Click on signup button")
    public SignupLoginPage clickOnSignupButton(){
        driver.element().click(signupButton);
        return new SignupLoginPage(driver);
    }

    //validations

    @Step("Verify that login label is displayed")
    public SignupLoginPage isLoginLabelDisplayed(){
        driver.verification().isElementVisible(loginLabel);
        return this;
    }

    @Step("Verify that login error message is {expectedErrorMessage}")
    public SignupLoginPage verifyLoginErrorMsg(String expectedErrorMessage){
        String actualMsg=driver.element().getText(loginError);
        driver.verification().Equals(actualMsg,expectedErrorMessage,"Login error message is not as expected");
        return this;
    }

    @Step("Verify that signup error message is {expectedErrorMessage}")
    public SignupLoginPage verifySignupErrorMsg(String expectedErrorMessage){
        String actualMsg=driver.element().getText(signupError);
        driver.verification().Equals(actualMsg,expectedErrorMessage,"Signup error message is not as expected");
        return this;
    }

}
