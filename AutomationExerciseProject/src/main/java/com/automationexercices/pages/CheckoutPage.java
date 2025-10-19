package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage {
    private final GUIDriver driver;
    private final String pageEndpoint = "/checkout";


    public CheckoutPage(GUIDriver driver) {
        this.driver = driver;
    }

    //locators
    //delivery address Box
    private final By deliveryAddressName = By.xpath("//ul[@id='address_delivery']/li[@class='address_firstname address_lastname']");
    private final By companyName = By.xpath("//ul[@id='address_delivery']/li[@class='address_address1 address_address2'][1]");
    private final By address1 = By.xpath("//ul[@id='address_delivery']/li[@class='address_address1 address_address2'][2]");
    private final By address2 = By.xpath("//ul[@id='address_delivery']/li[@class='address_address1 address_address2'][3]");
    private final By cityStateZipcode = By.xpath("//ul[@id='address_delivery']/li[@class='address_city address_state_name address_postcode']");
    private final By deliveryCountry = By.xpath("//ul[@id='address_delivery']/li[@class='address_country_name']");
    private final By deliveryPhone = By.xpath("//ul[@id='address_delivery']/li[@class='address_phone']");

    //billing address Box
    private final By billingName = By.xpath("//ul[@id='address_invoice']/li[@class='address_firstname address_lastname']");
    private final By billingCompanyName = By.xpath("//ul[@id='address_invoice']/li[@class='address_address1 address_address2'][1]");
    private final By billingAddress1 = By.xpath("//ul[@id='address_invoice']/li[@class='address_address1 address_address2'][2]");
    private final By billingAddress2 = By.xpath("//ul[@id='address_invoice']/li[@class='address_address1 address_address2'][3]");
    private final By billingCityStateZipcode = By.xpath("//ul[@id='address_invoice']/li[@class='address_city address_state_name address_postcode']");
    private final By billingCountry = By.xpath("//ul[@id='address_invoice']/li[@class='address_country_name']");
    private final By billingPhone = By.xpath("//ul[@id='address_invoice']/li[@class='address_phone']");
    private final By placeOrderButton = By.xpath("//div/a[.='Place Order']");

    //actions
    @Step("Navigate to Checkout Page")
    public CheckoutPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + pageEndpoint);
        return this;
    }

    @Step("Click on Place Order")
    public PaymentPage clickOnPlaceOrderButton() {
        driver.element().click(placeOrderButton);
        return new PaymentPage(driver);
    }


    //validations
    @Step("Validate Delivery Address")
    public CheckoutPage validateDeliveryAddress(String title, String fname, String lname, String company, String address1, String address2,
                                                String city, String state, String zipcode, String country, String phone) {
        driver.validation().Equals(driver.element().getText(deliveryAddressName), (title + ". " + fname + " " + lname), "Delivery Address Name is not correct")
                .Equals(driver.element().getText(this.companyName), company, "Company Name is not correct")
                .Equals(driver.element().getText(this.address1), address1, "Address 1 is not correct")
                .Equals(driver.element().getText(this.address2), address2, "Address 2 is not correct")
                .Equals(driver.element().getText(cityStateZipcode), (zipcode + " " + city + " " + state), "City, State, Zipcode is not correct")
                .Equals(driver.element().getText(deliveryCountry), country, "Country is not correct")
                .Equals(driver.element().getText(deliveryPhone), phone, "Phone is not correct");
        return this;
    }

    @Step("Validate Billing Address")
    public CheckoutPage validateBillingAddress(String title, String fname, String lname, String company, String address1, String address2,
                                                String city, String state, String zipcode, String country, String phone) {
        driver.validation().Equals(driver.element().getText(billingName), (title + ". " + fname + " " + lname), "Delivery Address Name is not correct")
                .Equals(driver.element().getText(billingCompanyName), company, "Company Name is not correct")
                .Equals(driver.element().getText(billingAddress1), address1, "Address 1 is not correct")
                .Equals(driver.element().getText(billingAddress2), address2, "Address 2 is not correct")
                .Equals(driver.element().getText(billingCityStateZipcode), (zipcode + " " + city + " " + state), "City, State, Zipcode is not correct")
                .Equals(driver.element().getText(billingCountry), country, "Country is not correct")
                .Equals(driver.element().getText(billingPhone), phone, "Phone is not correct");
        return this;
    }
}
