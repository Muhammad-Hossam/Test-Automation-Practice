package tests;

import Pages.LoginPage;
import drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonReader;
import utils.PropertyReader;

import static utils.PropertyReader.getProperty;

public class HomeTest {

    WebDriver driver;
    JsonReader jsonReader;



    @Test
    public void addToCartTC(){
        new LoginPage(driver)
                .login(jsonReader.getJsonData("username"), jsonReader.getJsonData("password"))
                .isLoggedIn(getProperty("homeUrl"))
                .addToCart()
                .validateCartIcon("1");
    }


    @BeforeMethod
    public void setup(){
        jsonReader = new JsonReader("login-data");
        driver= WebDriverFactory.initDriver();
        driver.get(jsonReader.getJsonData("baseUrl"));


    }

    @AfterMethod
    public void tearDown(){
        WebDriverFactory.quitDriver();

    }
}
