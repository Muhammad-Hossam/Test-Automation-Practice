package com.automationexercices.media;

import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.logs.LogsManager;
import com.automationexercices.utils.report.AllureAttachmentManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotsManager {
    public static final String SCREENSHOT_PATH = "test-output/screenshots/";

    //take full page screenshot
    public static void takeScreenShot(WebDriver driver,String screenshotName){
        try{
            File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File dest= new File(SCREENSHOT_PATH+screenshotName+"-"+ TimeManager.getTimestamp()+".png");
            FileUtils.copyFile(src, dest);

            AllureAttachmentManager.attachScreenshot(screenshotName,dest.getAbsolutePath());

            LogsManager.info("Capturing screenshot successfully");
        } catch (Exception e){
            LogsManager.error("Error capturing screenshot: "+e.getMessage());
        }
    }




    //take element screenshot
    public static void takeElementScreenShot(WebDriver driver,By locator){
        try{
            String areaName=driver.findElement(locator).getAccessibleName();
            File src= driver.findElement(locator).getScreenshotAs(OutputType.FILE);
            File dest= new File(SCREENSHOT_PATH+areaName+"-"+ TimeManager.getTimestamp()+".png");
            FileUtils.copyFile(src, dest);

            String screenshotName=areaName+"-"+ TimeManager.getTimestamp();
            AllureAttachmentManager.attachScreenshot(screenshotName,dest.getAbsolutePath());

            LogsManager.info("Capturing element screenshot successfully");
        } catch (Exception e){
            LogsManager.error("Error capturing element screenshot: "+e.getMessage());
        }
    }

}
