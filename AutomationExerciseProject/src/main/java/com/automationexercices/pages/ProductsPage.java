package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import javax.naming.ldap.LdapReferralException;

public class ProductsPage {
    private final GUIDriver driver;
    public NavigationBarComponent navigateBar;

    public ProductsPage(GUIDriver driver) {
        this.driver = driver;
        this.navigateBar = new NavigationBarComponent(driver);
    }

    //variable
    private String productPageEndpoint="/products";

    //locators
    private final By searchProductFeild= By.id("search_product");
    private final By searchButton= By.cssSelector(".container >button");
    private final By itemAddedLabel= By.cssSelector(".modal-body>p");
    private final By viewCartButton= By.cssSelector("p>[href=\"/view_cart\"]");
    private final By continueShoppingButton= By.cssSelector(".modal-footer>button");


    //dynamic product locator
    private By productName(String productName)
    {
        return By.xpath("//div[@class='features_items'] //div[@class='overlay-content']/p[.='"+productName+"']");
    }

    private By productPrice(String productName)
    {
        return By.xpath("//div[@class='features_items'] //div[@class='overlay-content']/p[.='"+productName+"']//preceding-sibling::h2");
    }

    private By hoverOnProduct(String productName)
    {
        return By.xpath("//div[@class='features_items'] //div[@class='productinfo text-center']/p[.='"+productName+"']");
    }

    private By addToCartButton(String productName)
    {
        return By.xpath("//div[@class='features_items'] //div[@class='productinfo text-center']/p[.='"+productName+"']//following-sibling::a");
    }
    private By productInfo(String productName) {
        return By.xpath("//p[.=\""+productName+"\"]//following::div[@class='choose'][1]");

    }

    //actions
    @Step("Navigate to products page")
     public ProductsPage navigate()
     {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+productPageEndpoint);
        return this;
     }

     @Step("Search for product")
     public ProductsPage searchForProduct(String productName)
     {
        driver.element().type(searchProductFeild,productName).click(searchButton);
        return this;
     }

     @Step("Add product to cart")
     public ProductsPage addProductToCart(String productName)
     {
        driver.element().click(addToCartButton(productName));
        return this;
     }

     @Step("Click on view product: {productName}")
     public ProductDetatailsPage clickViewProduct(String productName)
     {
        driver.element().click(productInfo(productName));
        return new ProductDetatailsPage(driver);
     }

     @Step("click on view cart")
     public CartPage clickViewCart()
     {
        driver.element().click(viewCartButton);
        return new CartPage(driver);
     }

     @Step("Click on continue shopping")
     public ProductsPage clickContinueShopping()
     {
        driver.element().click(continueShoppingButton);
        return this;
     }


     //validate

     @Step("Validate product details")
     public ProductsPage validateProductDetails(String productName,String productPrice)
     {
         driver.element().hover(hoverOnProduct(productName));
         String actualProductName= driver.element().getText(productName(productName));
         String actualProductPrice= driver.element().getText(productPrice(productName));
         LogsManager.info("Actual product name: "+actualProductName,"with price: "+actualProductPrice);
         driver.validation().Equals(actualProductName,productName,"Product name is not as expected");
         driver.validation().Equals(actualProductPrice,productPrice,"Product price is not as expected");
        return this;
     }

     @Step("Validate product added to cart")
    public ProductsPage validateItemAddedLabel(String expectedText){
        String actualText= driver.element().getText(itemAddedLabel);
        LogsManager.info("Actual product added message: "+actualText);
        driver.verification().Equals(actualText,expectedText,"Product added message is not as expected");
        return this;
     }

}
