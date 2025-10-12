package Pages;

import bots.ActionsBot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    //variables
    private WebDriver driver;                       //note this only to be used in this class to make actions
    private ActionsBot actionsBot;



    //locators
    private final By userName= By.id("user-name");
    private final By password= By.id("password");
    private final By loginButton= By.id("login-button");



    //constructor
    public LoginPage(WebDriver driver)
    {
        this.driver= driver;
        this.actionsBot= new ActionsBot(driver);
    }



    //actions
    @Step("login to web portal with username: {username} and password: {pass}")
    public LoginPage login(String username, String pass)
    {
        actionsBot.type(userName,username);
        actionsBot.type(password,pass);
        actionsBot.click(loginButton);
        return this;
    }

    //validations
    @Step("verify that the user is logged in successfully to {expectedUrl}")
    public HomePage isLoggedIn(String expectedUrl){
        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
        return new HomePage(driver);
    }

}
