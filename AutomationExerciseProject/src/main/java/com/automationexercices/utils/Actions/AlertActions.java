package com.automationexercices.utils.Actions;

import com.automationexercices.utils.WaitManager;
import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;

public class AlertActions {
    private final WebDriver driver;
    private final WaitManager waitManager;


    public AlertActions(WebDriver driver){
        this.driver=driver;
        this.waitManager =  new WaitManager(driver);
    }

     /**
     * Accepts the alert.
     */
     public void acceptAlert(){
        waitManager.fluentWait().until(d ->
        {
            try {
                driver.switchTo().alert().accept();
                return true;
            }
            catch (Exception e) {
                LogsManager.error("Failed to accept alert: " , e.getMessage());
                return false;
            }
        });
    }

     /**
     * Dismisses the alert.
     */
     public void dismissAlert(){
        waitManager.fluentWait().until(d ->
        {
            try {
                driver.switchTo().alert().dismiss();
                return true;
            }
            catch (Exception e) {
                LogsManager.error("Failed to dismiss alert: " , e.getMessage());
                return false;
            }
        });
    }

     /**
     * Gets the text of the alert.
     * @return The alert text, or null if no alert is present.
     */
    public String getAlertText(){
        return waitManager.fluentWait().until(d ->
        {
            try {
                String text = driver.switchTo().alert().getText();
                return !text.isEmpty() ? text : null;
            }
            catch (Exception e) {
                LogsManager.error("Failed to get alert text: " , e.getMessage());
                return null;
            }
        });
    }


    /**
     * Sends keys to the alert.
     * @param keys The keys to send.
     */

     public void sendKeysToAlert(String keys){
        waitManager.fluentWait().until(d ->
        {
            try {
                driver.switchTo().alert().sendKeys(keys);
                return true;
            }
            catch (Exception e) {
                LogsManager.error("Failed to send keys to alert: " , e.getMessage());
                return false;
            }
        });
    }

}
