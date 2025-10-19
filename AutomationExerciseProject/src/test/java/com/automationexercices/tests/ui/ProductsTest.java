package com.automationexercices.tests.ui;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.ProductsPage;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Automation Exercise - Products")
@Feature("UI Product Management")
@Story("Product Scenario")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")

public class ProductsTest extends BaseTest {



    @Test
    @Description("Search for a product and validate its details")
    public void searchForProduct(){
        new ProductsPage(driver)
                .navigate()
                .searchForProduct(testData.getJsonData("searchedProduct.ProductName"))
                .validateProductDetails(
                        testData.getJsonData("searchedProduct.ProductName"),
                        testData.getJsonData("searchedProduct.ProductPrice"));

    }

    @Test
    @Description("Add a product to the cart ")
    public void addProductToCartWithoutLogin(){
        new ProductsPage(driver)
                .navigate()
                .addProductToCart(testData.getJsonData("product1.name"))
                .validateItemAddedLabel(testData.getJsonData("message.cartAdded"));
    }


    //configurations
    @BeforeClass
    protected void beforeClass(){
        testData = new JsonReader("products-data");
    }

    @BeforeMethod
    public void setUp(){
        driver=new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @AfterMethod
    public void tearDown(){
        driver.quitDriver();
    }

}
