package tests;

import Pages.LoginPage;
import drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonReader;

import static utils.PropertyReader.getProperty;

public class CheckOut1stPageTest {

    WebDriver driver;
    JsonReader jsonReader;



    @BeforeMethod
    public void setup(){
        jsonReader = new JsonReader("login-data");
        driver= WebDriverFactory.initDriver();
        driver.get(jsonReader.getJsonData("baseUrl"));
    }



@Test
public void successfullCheckOut(){
    new LoginPage(driver)
            .login(jsonReader.getJsonData("username"),jsonReader.getJsonData("password"))
            .isLoggedIn(getProperty("homeUrl"))
            .addToCart()
            .validateCartIcon("1")
            .cartPageNavigate()
            .validateCartPageNavigate(getProperty("cartUrl"))
            .checkOut()
            .validateCheckOut(getProperty("checkoutUrlStep1"))
            .fillCheckOutForm(jsonReader.getJsonData("firstname"),jsonReader.getJsonData("lastname"),jsonReader.getJsonData("postcode"))
            .continueCheckOut()
            .validSuccessFirstStep(getProperty("checkoutUrlStep2"));
}


@Test
public void checkOutWithoutName(){
    new LoginPage(driver)
            .login(jsonReader.getJsonData("username"),jsonReader.getJsonData("password"))
            .isLoggedIn(getProperty("homeUrl"))
            .addToCart()
            .validateCartIcon("1")
            .cartPageNavigate()
            .validateCartPageNavigate(getProperty("cartUrl"))
            .checkOut()
            .validateCheckOut(getProperty("checkoutUrlStep1"))
            .fillCheckOutForm("",jsonReader.getJsonData("lastname"),jsonReader.getJsonData("postcode"))
            .continueCheckOut()
            .firstNameError();
}

@Test
public void checkOutWithoutPostcode(){
    new LoginPage(driver)
            .login(jsonReader.getJsonData("username"),jsonReader.getJsonData("password"))
            .isLoggedIn(getProperty("homeUrl"))
            .addToCart()
            .validateCartIcon("1")
            .cartPageNavigate()
            .validateCartPageNavigate(getProperty("cartUrl"))
            .checkOut()
            .validateCheckOut(getProperty("checkoutUrlStep1"))
            .fillCheckOutForm(jsonReader.getJsonData("firstname"),jsonReader.getJsonData("lastname"),"")
            .continueCheckOut()
            .postalCodeError();
}

@Test
public void checkOutCancel(){
    new LoginPage(driver)
            .login(jsonReader.getJsonData("username"),jsonReader.getJsonData("password"))
            .isLoggedIn(getProperty("homeUrl"))
            .addToCart()
            .validateCartIcon("1")
            .cartPageNavigate()
            .validateCartPageNavigate(getProperty("cartUrl"))
            .checkOut()
            .validateCheckOut(getProperty("checkoutUrlStep1"))
            .fillCheckOutForm(jsonReader.getJsonData("firstname"),jsonReader.getJsonData("lastname"),jsonReader.getJsonData("postcode"))
            .cancelCheckOut()
            .checkOutCancel(getProperty("cartUrl"));
}


    @AfterMethod
    public void tearDown(){
        WebDriverFactory.quitDriver();
    }



}

