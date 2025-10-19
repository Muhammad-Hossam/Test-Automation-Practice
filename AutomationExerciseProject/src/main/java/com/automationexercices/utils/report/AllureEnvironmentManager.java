package com.automationexercices.utils.report;

import com.automationexercices.utils.logs.LogsManager;
import com.google.common.collect.ImmutableMap;

import java.io.File;

import static com.automationexercices.utils.dataReader.PropertyReader.getProperty;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AllureEnvironmentManager {

    public static void setEnvironmentInfo(){
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", getProperty("os.name"))
                        .put("Browser", getProperty("browserType"))
                        .put("JDK Version", getProperty("java.runtime.version"))
                        .put("Execution Type", getProperty("executionType"))
                        .put("URL", getProperty("baseUrlWeb"))
                        .build(), String.valueOf(AllureConstants.RESULTS_FOLDER)+File.separator

        );
        LogsManager.info("Allure environment info set successfully");
        AllureBinaryManager.downloadAndExtract();
    }
}
