package Pages;

import bots.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage {

    private WebDriver driver;
    private ActionsBot actionsBot;

    //locators
    private final By removeButton = By.xpath("//div[.='Sauce Labs Backpack']//following::button");
    private final By checkOutButton = By.cssSelector(".cart_footer [href*='checkout']");
    private final By continueShoppingButton = By.cssSelector(".cart_footer .btn_secondary");
    private final By cartIcon = By.cssSelector("[data-icon=\"shopping-cart\"]");

    //constructor
    public CartPage(WebDriver driver){
        this.driver=driver;
        this.actionsBot= new ActionsBot(driver);
    }

    //actions

    public CartPage cartPageNavigate(){
        actionsBot.click(cartIcon);
        return this;
    }
    public CartPage removeItem(){
        actionsBot.click(removeButton);
        return this;
    }

     public CartPage continueShopping(){
        actionsBot.click(continueShoppingButton);
        return this;
    }


     public CartPage checkOut(){
        actionsBot.click(checkOutButton);
        return this;
    }

//validations
    public CartPage validateCartPageNavigate(String expectedUrl){
        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
        return this;
    }

    public CartPage validateContinueShopping(String expectedUrl){
        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
        return this;
    }

     public CheckOutPage1 validateCheckOut(String checkoutUrl) {
        Assert.assertEquals(driver.getCurrentUrl(),checkoutUrl);
        return new CheckOutPage1(driver);
    }
}

