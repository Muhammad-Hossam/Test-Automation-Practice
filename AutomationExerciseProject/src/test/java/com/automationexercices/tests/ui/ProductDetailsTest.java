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

@Epic("Automation Exercise - Product Details")
@Feature("UI Product Details")
@Story("Product Details Scenario")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")

public class ProductDetailsTest extends BaseTest {


    @Description("Verify product details Name and Price")
    @Test
    public void verifyProductDetailsTC()
    {
        new ProductsPage(driver)
                .navigate()
                .clickViewProduct(testData.getJsonData("product.ProductName"))
                .verifyProductDetails(testData.getJsonData("product.ProductName"),testData.getJsonData("product.ProductPrice"));

    }



    @Test
    public void verifyReviewSuccessMsgTC(){
        new ProductsPage(driver)
                .navigate()
                .clickViewProduct(testData.getJsonData("product.ProductName"))
                .addReview(testData.getJsonData("review.name"),testData.getJsonData("review.email"),testData.getJsonData("review.review"))
                .verifyReviewSuccessMessage(testData.getJsonData("message.review"));

    }


    //configurations
    @BeforeClass
    protected void beforeClass(){
        testData = new JsonReader("product-details-data");
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
