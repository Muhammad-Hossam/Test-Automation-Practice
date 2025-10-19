package com.automationexercices;

import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import org.apache.logging.log4j.LogManager;

import java.io.File;

import static org.apache.commons.io.FileUtils.copyFile;

public class FileUtils {
    private static final String USER_DIR= PropertyReader.getProperty("user.dir")+File.separator;

    private FileUtils(){
    }


    //Renaming
    public static void renameFile(String oldName, String newName){
        try {
            var targetFile=new File(oldName);
            String targetDirectory= targetFile.getParentFile().getAbsolutePath();
            File newFile=new File(targetDirectory+File.separator+newName);
            if (!targetFile.getPath().equals(newFile.getPath())){
                copyFile(targetFile,newFile);
                org.apache.commons.io.FileUtils.deleteQuietly(targetFile);
                LogsManager.info("Target File path " + oldName + " is renamed to " + newName);
            }
            else {
                LogsManager.info("File " + oldName + " is already renamed to " + newName);
            }
        }catch (Exception e){
            LogsManager.error("Error while renaming file: " + e.getMessage());
        }
    }


    //create directory
    public static void createDirectory(String path){
        try {
            File file=new File(USER_DIR+path);
            if (!file.exists()){
                file.mkdirs();
                LogsManager.info("Directory created: " + path);
            }
        }
        catch (Exception e){
            LogsManager.error("Error while creating directory: " + e.getMessage());
        }
    }

    //force delete
    public static void forceDelete(File file){
        try {
            LogManager.shutdown();
            org.apache.commons.io.FileUtils.forceDelete(file);
            LogsManager.info("File deleted: " + file.getPath());
        }
        catch (Exception e){
            LogsManager.error("Error while deleting file: " + e.getMessage());
        }
    }



    //clean directory
    public static void cleanDirectory(File file){
        try {
            org.apache.commons.io.FileUtils.deleteQuietly(file);

        }
        catch (Exception e){
            LogsManager.error("Error while cleaning directory: " + e.getMessage());
        }
    }


    //check if file exists
    public static boolean isFileExists(String fileName) {
        String downloadPath=System.getProperty(("user.dir"))+File.separator+ "/src/test/resources/downloads/";
        File file = new File(downloadPath + fileName);
        return file.exists();
    }
}
