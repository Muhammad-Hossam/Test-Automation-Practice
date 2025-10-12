package com.core.customlisteners;

import com.core.FileUtils;
import com.core.drivers.WebDriverProvider;
import com.core.media.ScreenshotsManager;
import com.core.utils.dataReader.PropertyReader;
import com.core.utils.logs.LogsManager;
import com.core.utils.report.AllureAttachmentManager;
import com.core.utils.report.AllureConstants;
import com.core.utils.report.AllureEnvironmentManager;
import com.core.utils.report.AllureReportGenerator;
import com.core.validations.Validation;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.security.Provider;

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
            Validation.assertAll();
            if(testResult.getInstance() instanceof WebDriverProvider provider){
                driver = provider.getWebDriver();
            }
            switch (testResult.getStatus()){
                case ITestResult.SUCCESS-> ScreenshotsManager.takeScreenShot(driver,"passed-"+testResult.getName());
                case ITestResult.FAILURE-> ScreenshotsManager.takeScreenShot(driver,"Failed-"+testResult.getName());
                case ITestResult.SKIP-> ScreenshotsManager.takeScreenShot(driver,"skipped-"+testResult.getName());
            }
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
        FileUtils.cleanDirectory(new File(LogsManager.LOGS_PATH));
    }

    private void createTestOutputDirectories(){
        FileUtils.createDirectory(ScreenshotsManager.SCREENSHOT_PATH);
    }



}
