package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.components.NavigationBarComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegisterPage {
    private GUIDriver driver;
    private final String signupEndpoint="/signup";

    public RegisterPage(GUIDriver driver){
        this.driver=driver;
    }

    //locators
    private final By nameField= By.cssSelector("[data-qa=\"name\"]");
    private final By emailField= By.cssSelector("[data-qa=\"email\"]");
    private final By passwordField= By.cssSelector("[data-qa=\"password\"]");
    private final By dayField= By.cssSelector("[data-qa=\"days\"]");
    private final By monthField= By.cssSelector("[data-qa=\"months\"]");
    private final By yearField= By.cssSelector("[data-qa=\"years\"]");

    private final By newsletterCheckbox= By.id("newsletter");
    private final By specialOffersCheckbox= By.id("optin");

    private final By firstNameField= By.cssSelector("[data-qa=\"first_name\"]");
    private final By lastNameField= By.cssSelector("[data-qa=\"last_name\"]");
    private final By companyField= By.cssSelector("[data-qa=\"company\"]");
    private final By addressField= By.cssSelector("[data-qa=\"address\"]");
    private final By address2Field= By.cssSelector("[data-qa=\"address2\"]");
    private final By cityField= By.cssSelector("[data-qa=\"city\"]");
    private final By stateField= By.cssSelector("[data-qa=\"state\"]");
    private final By countryField= By.cssSelector("[data-qa=\"country\"]");
    private final By zipcodeField= By.cssSelector("[data-qa=\"zipcode\"]");
    private final By mobileNumberField= By.cssSelector("[data-qa=\"mobile_number\"]");
    private final By createAccountButton= By.cssSelector("[data-qa=\"create-account\"]");

    private final By accountCreatedMessage= By.xpath("//b");
    private final By continueButton= By.cssSelector("[data-qa=\"continue-button\"]");



    //actions
    @Step("Choose gender {gender}") //Mr - Mrs
    private RegisterPage chooseGender(String gender){
        By genderRadio= By.xpath("//input[@value='"+gender+"']");
        driver.element().click(genderRadio);
        return this;
    }

    @Step("Fill Register Form")
    public RegisterPage fillRegisterForm(String gender,
                                         String password,
                                         String day,
                                         String month,
                                         String year,
                                         String firstName,
                                         String lastName,
                                         String company,
                                         String address1,
                                         String address2,
                                         String country,
                                         String state,
                                         String city,
                                         String zipcode,
                                         String mobileNumber)
    {
        chooseGender(gender);
        driver.element().type(passwordField,password);
        driver.element().selectFromDropdown(dayField,day);
        driver.element().selectFromDropdown(monthField, month);
        driver.element().selectFromDropdown(yearField, year);
        driver.element().click(newsletterCheckbox);
        driver.element().click(specialOffersCheckbox);
        driver.element().type(firstNameField,firstName);
        driver.element().type(lastNameField,lastName);
        driver.element().type(companyField,company);
        driver.element().type(addressField,address1);
        driver.element().type(address2Field,address2);
        driver.element().selectFromDropdown(countryField,country);
        driver.element().type(stateField,state);
        driver.element().type(cityField,city);
        driver.element().type(zipcodeField,zipcode);
        driver.element().type(mobileNumberField,mobileNumber);
        return this;
    }

    @Step("Click on the create account button")
    public RegisterPage clickCreateAccount(){
        driver.element().click(createAccountButton);
        return this;
    }

     @Step("Click on the continue button")
    public NavigationBarComponent clickOnContinueButton(){
        driver.element().click(continueButton);
        return new NavigationBarComponent(driver);
    }


    //validations

    @Step("Verify account created ")
    public RegisterPage verifyAccountCreated(){
        driver.verification().isElementVisible(accountCreatedMessage);
        return this;
    }

}
