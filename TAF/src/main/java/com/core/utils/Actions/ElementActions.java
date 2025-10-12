package com.core.utils.Actions;

import com.core.utils.WaitManager;
import com.core.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class ElementActions {
    private final WebDriver driver;
    private WaitManager waitMange;

    //Constructor
    public ElementActions(WebDriver driver){
        this.driver=driver;
        this.waitMange =new WaitManager(driver);
    }

    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }


    //Clicking with action encapsulated inside the wait
    public void click(By locator){
        waitMange.fluentWait().until(d->
                {
                    try {
                        WebElement element= d.findElement(locator);
                        scrollToElementJS(locator);
                        element.click();
                        LogsManager.info("Clicked on element: {}"+ locator);
                        return true;

                    } catch (Exception e){
                        return false;
                    }
                }
        );
    }


    //Typing
    public void type(By locator,String text){
        waitMange.fluentWait().until(d->
                {
                    try {
                        WebElement element= d.findElement(locator);
                        scrollToElementJS(locator);
                        element.clear();
                        element.sendKeys(text);
                        LogsManager.info("Typed '{}' into element: {}", text + locator);
                        return true;

                    } catch (Exception e){
                        return false;
                    }
                }
        );

    }


    //GettingText
    public String getText(By locator){
        return waitMange.fluentWait().until(d->
                {
                    try {
                        WebElement element= d.findElement(locator);
                        scrollToElementJS(locator);
                        String msg= element.getText();
                        LogsManager.info("Retrieved text '{}' from element: {}", msg+ locator);
                        return !msg.isEmpty() ? msg : null;
                    } catch (Exception e){
                        return null;
                    }
                }
        );

    }


    //upload file
    public void uploadFile(By locator,String filePath){
        String fileAbsolute= System.getProperty("user.dir"+ File.separator+filePath);
        waitMange.fluentWait().until(d->
                {
                    try {
                        WebElement element= d.findElement(locator);
                        scrollToElementJS(locator);
                        element.sendKeys(fileAbsolute);
                        LogsManager.info("Uploaded file '{}' to element: {}", fileAbsolute+ locator);
                        return true;

                    } catch (Exception e){
                        return false;
                    }
                }
        );
    }


    //scroll to an element using js
    public void scrollToElementJS(By locator){
        ((org.openqa.selenium.JavascriptExecutor)driver)
                .executeScript("""
                        arguments[0].scrollIntoView({behaviour:"auto", block: "center",inline:"center"});""",findElement(locator));
    }

}
