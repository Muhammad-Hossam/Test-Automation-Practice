package com.core.tests;

import com.core.drivers.GUIDriver;
import com.core.drivers.WebDriverProvider;
import com.core.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {

    protected GUIDriver driver;




    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
