package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {

private final GUIDriver driver;
private final String pageEndPoint = "/payment";

    public PaymentPage(GUIDriver driver) {
        this.driver = driver;
    }


    //locators
    private final By nameOnCard= By.cssSelector("[data-qa=\"name-on-card\"]");
    private final By cardNumber= By.cssSelector("[data-qa=\"card-number\"]");
    private final By cvc= By.cssSelector("[data-qa=\"cvc\"]");
    private final By expirationMonth= By.cssSelector("[data-qa=\"expiry-month\"]");
    private final By expirationYear= By.cssSelector("[data-qa=\"expiry-year\"]");
    private final By payAndConfirmButton= By.cssSelector("[data-qa=\"pay-button\"]");

    private final By orderPlacedMessage= By.xpath("//p[.='Congratulations! Your order has been confirmed!']");
    private final By downloadInvoiceButton= By.xpath("//a[.='Download Invoice']");


    //actions
    @Step("complete payment form")
   public PaymentPage fillCardInfo(String name, String number, String cvcNumber, String month, String year)
   {
       driver.element().type(nameOnCard,name)
       .type(cardNumber,number)
       .type(cvc,cvcNumber)
       .type(expirationMonth,month)
       .type(expirationYear,year).click(payAndConfirmButton);

       return this;
   }

    @Step("Download invoice")
   public PaymentPage downloadInvoice()
    {
        driver.element().click(downloadInvoiceButton);
        return this;
    }


    //validations

    @Step("verify payment success message")
   public PaymentPage varifyOrderPlaced (String expectedMessage)
    {
        driver.verification().Equals(driver.element().getText(orderPlacedMessage),
                expectedMessage,"Order placed issue");
        return this;
    }
    @Step("verify invoice downloaded")
    public PaymentPage verifyInvoiceDownloaded(String invoiceName)
    {
        driver.verification().assertFileExits(invoiceName," Invoice is not downloaded");
        return this;
    }
}
