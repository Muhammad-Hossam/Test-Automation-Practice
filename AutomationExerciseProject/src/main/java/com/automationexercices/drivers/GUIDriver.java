package com.automationexercices.drivers;

import com.automationexercices.utils.Actions.AlertActions;
import com.automationexercices.utils.Actions.BrowserActions;
import com.automationexercices.utils.Actions.ElementActions;
import com.automationexercices.utils.Actions.FrameActions;
import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import com.automationexercices.validations.Validation;
import com.automationexercices.validations.Verification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;


public class GUIDriver {
    private final String browserType= PropertyReader.getProperty("browserType");

    private  ThreadLocal<WebDriver> driverThreadLocal= new ThreadLocal<>();


    public GUIDriver(){
        Browser browser= Browser.valueOf(browserType.toUpperCase());
        LogsManager.info("Initializing driver =>" +browserType+ "<=");
        AbstractDriver abstractDriver=browser.getDriverFactory();
        WebDriver driver= ThreadGuard.protect(abstractDriver.createDriver());
        driverThreadLocal.set(driver);
    }

    public ElementActions element(){
        return new ElementActions(get());
    }

    public BrowserActions browser(){
        return new BrowserActions(get());
    }

    public FrameActions frame(){
        return new FrameActions(get());
    }

    public AlertActions alert(){
        return new AlertActions(get());
    }
    //Soft Assertions
    public Validation validation(){
        return new Validation(get());
    }

    //Hard Assertions
    public Verification verification(){
        return new Verification(get());
    }



    public  WebDriver get(){
        return driverThreadLocal.get();
    }
    public  void quitDriver(){
        driverThreadLocal.get().quit();
    }

}
