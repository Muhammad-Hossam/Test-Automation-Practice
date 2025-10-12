package tests.webportal.login;

import Pages.LoginPage;
import drivers.WebDriverFactory;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static utils.PropertyReader.getProperty;

public class LoginTest {


//variables
    WebDriver driver;



//Tests
    @Test
    public void validLoginTest(){
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Valid Login"));
        new LoginPage(driver)
                .login(getProperty("valid_username"), getProperty("valid_password"))
                .isLoggedIn(getProperty("homeUrl"));

    }


    @Test
    public void inValidUserLoginTest(){
        new LoginPage(driver)
                .login(getProperty("invalid_username"), getProperty("invalid_password"))
                .isLoggedIn(getProperty("baseUrl"));

    }

    @Test
    public void inValidPasswordLoginTest(){
        new LoginPage(driver)
                .login(getProperty("valid_username"), getProperty("invalid_password"))
                .isLoggedIn(getProperty("baseUrl"));

    }


//config

    @BeforeMethod
    public void setup()
    {
        driver= WebDriverFactory.initDriver();
        driver.get(getProperty("baseUrl"));
    }

    @AfterMethod
    public void tearDown()
    {
        WebDriverFactory.quitDriver();
    }

}
