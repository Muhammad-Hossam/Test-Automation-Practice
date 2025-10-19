package com.automationexercices.utils.report;

import com.automationexercices.utils.OSUtils;
import com.automationexercices.utils.TerminalUtils;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.automationexercices.utils.dataReader.PropertyReader.getProperty;
import static com.automationexercices.utils.report.AllureConstants.HISTORY_FOLDER;
import static com.automationexercices.utils.report.AllureConstants.RESULTS_FOLDER;

public class AllureReportGenerator {

    //Generate Allure report
    //--single-file
    public static void generateReports(boolean isSingleFile) {
        Path outputFolder = isSingleFile ? AllureConstants.REPORT_PATH : AllureConstants.FULL_REPORT_PATH;
        //allure generate -o reports --single-file --clean
        List<String> command = new ArrayList<>(List.of(
                AllureBinaryManager.getExecutable().toString(),
                "generate",
                RESULTS_FOLDER.toString(),
                "-o",
                outputFolder.toString(),
                "--clean"
        ));
        if (isSingleFile) command.add("--single-file");
        TerminalUtils.executeTerminalCommand(command.toArray(new String[0]));
    }

    //rename report file to AllureReport_timestamp.html
    public static String renameReport(){
        String newFileName = AllureConstants.REPORT_PREFIX + TimeManager.getTimestamp()+AllureConstants.REPORT_EXTENSION;
        com.automationexercices.FileUtils.renameFile(AllureConstants.REPORT_PATH.resolve(AllureConstants.INDEX_HTML).toString(),newFileName);
        return newFileName;
    }


    //open Allure report in browser
    public static void openReport(String reportFileName){
        if(!getProperty("executionType").toLowerCase().contains("local")) return;
        Path reportPath = AllureConstants.REPORT_PATH.resolve(reportFileName);
        switch (OSUtils.getCurrentOS()){
            case WINDOWS -> TerminalUtils.executeTerminalCommand("cmd.exe","/c","start " + reportPath.toString());
            case MAC, LINUX-> TerminalUtils.executeTerminalCommand("open", reportPath.toString());
            default -> LogsManager.warn("OS not supported to open Allure report");
        }
    }

    //copy history folder to results folder
    public static void copyHistory(){
        try{
            FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULTS_FOLDER.toFile());
        }
        catch (Exception e){
            LogsManager.error("Error while copying history folder to results folder" , e.getMessage());
        }
    }
}
