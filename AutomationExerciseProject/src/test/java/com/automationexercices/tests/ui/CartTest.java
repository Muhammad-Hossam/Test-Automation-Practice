package com.automationexercices.tests.ui;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.ProductsPage;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise - Cart")
@Feature("UI Cart Management")
@Story("Add Product To Cart")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")
public class CartTest extends BaseTest {




    //test
    @Test
    public void verifyCartProductDetails(){
        new ProductsPage(driver)
                .navigate()
                .addProductToCart(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("message.cartAdded"))
                .clickViewCart()
                .verifyProductDetails(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"));
    }


    //configurations
    @BeforeClass
    protected void beforeClass(){
        testData = new JsonReader("cart-data");
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
