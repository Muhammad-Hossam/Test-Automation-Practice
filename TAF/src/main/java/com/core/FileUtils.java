package com.core;

import com.core.utils.dataReader.PropertyReader;
import com.core.utils.logs.LogsManager;

import java.io.File;

public class FileUtils {
    private static final String USER_DIR= PropertyReader.getProperty("user.dir")+File.separator;

    private FileUtils(){
    }


    //Renaming
    public static void renameFile(String oldName, String newName){
        try {
            File oldFile=new File(USER_DIR+oldName);
            File newFile=new File(USER_DIR+newName);
            if (oldFile.renameTo(newFile)){
                LogsManager.info("File renamed from " + oldName + " to " + newName);
            }
            else {
                LogsManager.error("Error while renaming file from " + oldName + " to " + newName);
            }
        }
        catch (Exception e){
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





    //clean directory
    public static void cleanDirectory(File file){
        try {
            org.apache.commons.io.FileUtils.deleteQuietly(file);

        }
        catch (Exception e){
            LogsManager.error("Error while cleaning directory: " + e.getMessage());
        }
    }


}
