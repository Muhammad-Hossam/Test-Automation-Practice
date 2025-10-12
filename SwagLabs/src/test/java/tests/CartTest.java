package tests;

import Pages.LoginPage;
import drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonReader;

import static utils.PropertyReader.getProperty;

public class CartTest {

    WebDriver driver;
    JsonReader jsonReader;



    @BeforeMethod
    public void setup(){
        jsonReader = new JsonReader("login-data");
        driver= WebDriverFactory.initDriver();
        driver.get(jsonReader.getJsonData("baseUrl"));
    }

    @Test
    public void checkOutTC(){
        new LoginPage(driver)
                .login(jsonReader.getJsonData("username"), jsonReader.getJsonData("password"))
                .isLoggedIn(getProperty("homeUrl"))
                .addToCart()
                .validateCartIcon("1")
                .cartPageNavigate()
                .validateCartPageNavigate(getProperty("cartUrl"))
                .checkOut()
                .validateCheckOut(getProperty("checkoutUrlStep1"));
    }


    @Test
    public void removeItemTC(){
        new LoginPage(driver)
                .login(jsonReader.getJsonData("username"), jsonReader.getJsonData("password"))
                .isLoggedIn(getProperty("homeUrl"))
                .addToCart()
                .validateCartIcon("1")
                .cartPageNavigate()
                .validateCartPageNavigate(getProperty("cartUrl"))
                .removeItem();
    }

    @Test
    public void continueShoppingTC(){
        new LoginPage(driver)
                .login(jsonReader.getJsonData("username"), jsonReader.getJsonData("password"))
                .isLoggedIn(getProperty("homeUrl"))
                .addToCart()
                .validateCartIcon("1")
                .cartPageNavigate()
                .validateCartPageNavigate(getProperty("cartUrl"))
                .removeItem()
                .continueShopping()
                .validateContinueShopping(getProperty("homeUrl"));
    }




    @AfterMethod
    public void tearDown(){
        WebDriverFactory.quitDriver();
    }
}
