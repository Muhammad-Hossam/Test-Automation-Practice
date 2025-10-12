package Pages;

import bots.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {

    //variables
    private WebDriver driver;
    private ActionsBot actionsBot;

    //locators
    private final By addToCartButton= By.xpath("//div[.='Sauce Labs Backpack']//following::button[1]");
    private final By cartIcon =By.xpath("//*[@data-icon=\"shopping-cart\"]//following::span");


    //constructor
    public HomePage(WebDriver driver){
        this.driver=driver;
        this.actionsBot= new ActionsBot(driver);
    }

    //actions
    public HomePage addToCart(){

        actionsBot.click(addToCartButton);

        return this;
    }

    //validations
    public CartPage validateCartIcon(String expectedValue){
        String cartIconText = actionsBot.getText(cartIcon);
        Assert.assertEquals(cartIconText,expectedValue);
        return new CartPage(driver);
    }

}
