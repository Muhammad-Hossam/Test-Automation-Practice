package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.JsonReader;
import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;


public class ProductDetatailsPage {
    private final GUIDriver driver;
    private final String pageEndPoint = "/product_details/2";

    public ProductDetatailsPage(GUIDriver driver) {
        this.driver= driver;
    }


    //locators
    private final By productName= By.cssSelector(".product-information > h2");
    private final By productPrice= By.cssSelector("span > span");

    private final By nameFeild= By.id("name");
    private final By emailFeild= By.id("email");
    private final By reviewFeild= By.cssSelector("form> textarea");

    private final By submitButton= By.id("button-review");
    private final By successMessage= By.xpath("//div/span[.=\"Thank you for your review.\"]");


    //actions
    public ProductDetatailsPage navigate()
    {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"+pageEndPoint));
        return this;
    }

    public ProductDetatailsPage addReview(String name, String email, String review)
    {
        driver.element().type(nameFeild,name);
        driver.element().type(emailFeild,email);
        driver.element().type(reviewFeild,review);
        driver.element().click(submitButton);
        return this;
    }

    //validation
    @Step("verify product details")
    public ProductDetatailsPage verifyProductDetails(String pName, String pPrice)
    {
        String actualProductName= driver.element().getText(productName);
        String actualProductPrice= driver.element().getText(productPrice);
        LogsManager.info("actual product name:",actualProductName,"actual product price:",actualProductPrice);
        driver.validation().Equals(actualProductName,pName,"product name not match");
        driver.validation().Equals(actualProductPrice,pPrice,"product price not match");
        LogsManager.info("product name and price verified successfully");
        return this;
    }


    @Step("verify review success message")
    public ProductDetatailsPage verifyReviewSuccessMessage(String msg)
    {
        String actualSuccessMessage= driver.element().getText(successMessage);
        LogsManager.info("actual success message:",actualSuccessMessage);
        driver.verification().Equals(actualSuccessMessage,msg,"review success message not match");
        LogsManager.info("review success message verified successfully");

        return this;
    }


}
