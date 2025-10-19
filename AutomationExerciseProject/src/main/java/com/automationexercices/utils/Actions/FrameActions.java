package com.automationexercices.utils.Actions;

import com.automationexercices.utils.WaitManager;
import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrameActions {
        private final WebDriver driver;
        private final WaitManager waitManager;


        public FrameActions(WebDriver driver){
            this.driver=driver;
            this.waitManager =  new WaitManager(driver);
        }


        /**
         * Switches to the frame by index.
         * @param index The index of the frame.
         */
        public void switchToFrameByIndex(int index){
            waitManager.fluentWait().until(d ->
            {
                try {
                    d.switchTo().frame(index);
                    LogsManager.info("Switched to frame by index: "+ index);
                    return true;
                }
                catch (Exception e) {
                    return false;
                }
            });
        }


        /**
         * Switches to the frame by name or ID.
         * @param nameOrId The name or ID of the frame.
         */
        public void switchToFrameByNameOrId(String nameOrId){
            waitManager.fluentWait().until(d ->
            {
                try {
                    d.switchTo().frame(nameOrId);
                    LogsManager.info("Switched to frame by name or ID: "+ nameOrId);
                    return true;
                }
                catch (Exception e) {
                    return false;
                }
            });
        }

    /**
     * Switches to  a frame by WebElement.
     * @param frameLocator The locator of the frame.
     */
    public void switchToFrameByElement(By frameLocator){
        waitManager.fluentWait().until(d ->
        {
            try {
                d.switchTo().frame(d.findElement(frameLocator));
                LogsManager.info("Switched to frame by WebElement: "+ frameLocator);
                return true;
            }
            catch (Exception e) {
                return false;
            }
        });
    }


    /**
     * Switches to the default content.
     */
    public void switchToDefaultContent(){
        waitManager.fluentWait().until(d ->
        {
            try {
                d.switchTo().defaultContent();
                LogsManager.info("Switched to default content");
                return true;
            }
            catch (Exception e) {
                return false;
            }
        });
    }


}
