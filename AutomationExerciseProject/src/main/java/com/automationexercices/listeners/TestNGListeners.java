package com.automationexercices.listeners;
import com.google.common.collect.ImmutableMap;

import com.automationexercices.FileUtils;
import com.automationexercices.drivers.WebDriverProvider;
import com.automationexercices.media.ScreenshotsManager;
import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import com.automationexercices.utils.report.AllureAttachmentManager;
import com.automationexercices.utils.report.AllureConstants;
import com.automationexercices.utils.report.AllureEnvironmentManager;
import com.automationexercices.utils.report.AllureReportGenerator;
import com.automationexercices.validations.Validation;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import io.qameta.allure.Allure;
import io.qameta.allure.model.TestResultContainer;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TestNGListeners implements IExecutionListener, IInvokedMethodListener, ITestListener {


    @Override
    public void onExecutionStart() {
        LogsManager.info("Execution started");
        cleanTestOutputDirectories();
        LogsManager.info("Test output directories cleaned");
        createTestOutputDirectories();
        LogsManager.info("Test output directories created");
        PropertyReader.loadProperties();
        LogsManager.info("Properties loaded");
        AllureEnvironmentManager.setEnvironmentInfo();
        LogsManager.info("Allure environment info set");

    }
    @Override
    public void onExecutionFinish() {
        AllureReportGenerator.generateReports(false);
        AllureReportGenerator.copyHistory();
        AllureReportGenerator.generateReports(true);
        AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
        LogsManager.info("Execution finished");
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            LogsManager.info(method.getTestMethod().getMethodName()+" "+ " About to execute " );
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = null;
        if (method.isTestMethod()) {
                if(testResult.getInstance() instanceof WebDriverProvider provider){
                    driver = provider.getWebDriver();
                }
                switch (testResult.getStatus()){
                    case ITestResult.SUCCESS-> ScreenshotsManager.takeScreenShot(driver,"passed-"+testResult.getName());
                    case ITestResult.FAILURE-> ScreenshotsManager.takeScreenShot(driver,"Failed-"+testResult.getName());
                    case ITestResult.SKIP-> ScreenshotsManager.takeScreenShot(driver,"skipped-"+testResult.getName());
                }
            Validation.assertAll(testResult);
            AllureAttachmentManager.attachLogs();

        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsManager.info(result.getMethod().getMethodName() +" "+ "passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsManager.info(result.getMethod().getMethodName() +" "+ "Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsManager.info(result.getMethod().getMethodName() +" "+ "skipped");
    }



    private void cleanTestOutputDirectories(){
        FileUtils.cleanDirectory(AllureConstants.RESULTS_FOLDER.toFile());
        FileUtils.cleanDirectory(new File(ScreenshotsManager.SCREENSHOT_PATH));
        FileUtils.cleanDirectory(new File("src/test/resources/downloads"));
        FileUtils.forceDelete(new File(LogsManager.LOGS_PATH));
    }

    private void createTestOutputDirectories(){
        FileUtils.createDirectory(ScreenshotsManager.SCREENSHOT_PATH);
        FileUtils.createDirectory("src/test/resources/downloads");
    }



}
