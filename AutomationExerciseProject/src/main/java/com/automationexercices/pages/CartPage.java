package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
        private final GUIDriver driver;
        private final String pageEndPoint = "/view_cart";

        public CartPage(GUIDriver driver) {
            this.driver= driver;
        }


    //locators
    private final By proceedToCheckoutButton = By.cssSelector(".col-sm-6 >a");


    //dynamic locators
    private By productName(String productName)
    {
        return By.xpath("(//h4 /a[.='"+productName+"'])[1]");
    }

    private By productPrice(String productName) {
        return By.xpath("(//h4 /a[.='"+productName+"']//following::td[@class='cart_price']/p)[1]");
    }
    private By productQuantity(String productName) {
        return By.xpath("(//h4 /a[.='"+productName+"']//following::td[@class='cart_quantity']/button)[1]");
    }
    private By productTotal(String productName) {
        return By.xpath("(//h4 /a[.='"+productName+"']//following::td[@class='cart_total']/p)[1]");
    }
    private By removeProductButton(String productName) {
        return By.xpath("(//h4 /a[.='"+productName+"']//following::td[@class='cart_delete']/a)[1]");
    }


    //actions
    @Step("Navigate to Cart Page")
    public CartPage navigate()
    {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+pageEndPoint);
        return this;
    }
    @Step("Click Proceed To Checkout Button")
    public CheckoutPage clickProceedToCheckout()
    {
        driver.element().click(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }
    @Step("Remove Product From Cart")
    public CartPage removeProduct(String pName)
    {
        driver.element().click(removeProductButton(pName));
        return this;
    }

    //validations
    @Step("Verify Product Details")
    public CartPage verifyProductDetails(String pName,String pPrice,String pQuantity,String pTotal)
    {
        String actualProductName= driver.element().getText(productName(pName));
        String actualProductPrice= driver.element().getText(productPrice(pName));
        String actualProductQuantity= driver.element().getText(productQuantity(pName));
        String actualProductTotal= driver.element().getText(productTotal(pName));

        driver.validation().Equals(actualProductName,pName,"Product name is not as expected")
        .Equals(actualProductPrice,pPrice,"Product price is not as expected")
        .Equals(actualProductQuantity,pQuantity,"Product quantity is not as expected")
        .Equals(actualProductTotal,pTotal,"Product total is not as expected");
        return this;
    }



























}
