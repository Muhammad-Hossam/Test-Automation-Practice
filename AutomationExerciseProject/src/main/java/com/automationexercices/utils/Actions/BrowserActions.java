package com.automationexercices.utils.Actions;

import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class BrowserActions {
    private final WebDriver driver;


    public BrowserActions(WebDriver driver){
        this.driver=driver;
    }

    public void maximize(){
        driver.manage().window().maximize();
    }

    public String getCurrentUrl(){
        String url = driver.getCurrentUrl();
        LogsManager.info("Current URL is: " + url);
        return url;
    }

     public void navigateTo(String url){
        driver.navigate().to(url);
        LogsManager.info("Navigated to URL: " + url);
    }

    public void openNewWindow(){
        driver.switchTo().newWindow(WindowType.WINDOW);
        LogsManager.info("Opened new window");
    }

    public void navigateBack(){
        driver.navigate().back();
        LogsManager.info("Navigated back");
    }

    public void navigateForward(){
        driver.navigate().forward();
        LogsManager.info("Navigated forward");
    }

    public void closeCurrentWindow(){
        driver.close();
        LogsManager.info("Closed current window");
    }

     public void refresh(){
        driver.navigate().refresh();
        LogsManager.info("Refreshed current page");
    }

}
