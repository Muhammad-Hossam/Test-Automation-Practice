package Pages;

import bots.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckOutPage1 {
    //variables
    private WebDriver driver;
    private ActionsBot actionsBot;
    private final By firstName= By.id("first-name");
    private final By lastName= By.id("last-name");
    private final By postalCode= By.id("postal-code");
    private final By continueButton= By.cssSelector("[type=\"submit\"]");
    private final By cancelButton= By.cssSelector(".checkout_buttons>a");
    private final By errorMessage= By.cssSelector("[data-test=\"error\"]");

    //constructor
    public CheckOutPage1(WebDriver driver) {
        this.driver = driver;
        this.actionsBot = new ActionsBot(driver);
    }

    //actions
    public CheckOutPage1 fillCheckOutForm(String firstname,String lastname,String postcode){
        actionsBot.type(firstName,firstname);
        actionsBot.type(lastName,lastname);
        actionsBot.type(postalCode,postcode);
        return this;
    }
     public CheckOutPage1 cancelCheckOut(){
        actionsBot.click(cancelButton);
        return this;
    }

     public CheckOutPage1 continueCheckOut(){
        actionsBot.click(continueButton);
        return this;
    }


    //validation

    public CheckOutPage1 validSuccessFirstStep(String expectedUrl){
        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
        return this;
    }

    public CheckOutPage1 firstNameError(){
        Assert.assertEquals(actionsBot.getText(errorMessage),"Error: First Name is required");
        return this;
    }
     public CheckOutPage1 lastNameError(){
        Assert.assertEquals(actionsBot.getText(errorMessage),"Error: Last Name is required");
        return this;
    }
     public CheckOutPage1 postalCodeError(){
        Assert.assertEquals(actionsBot.getText(errorMessage),"Error: Postal Code is required");
        return this;
    }

    public CheckOutPage1 checkOutCancel(String cartUrl){
        Assert.assertEquals(driver.getCurrentUrl(),cartUrl);
        return this;
    }









}
