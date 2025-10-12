package utils;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Allure;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static java.nio.file.Files.newInputStream;

public class AllureUtils {

    //clean allure result folder before each test
    public static void cleanAllureResults(){
        //it waits untill the read and write ends
        FileUtils.deleteQuietly(new File("test-output/allure-results"));
    }


    public static void attachScreanShotsAllure(String screenName,String screenPath){
        try{
            Allure.addAttachment(screenName, newInputStream(Path.of(screenPath)));

        } catch (Exception e){
            System.out.println("Error"+e.getMessage());
        }
    }

    public static void setEnvironmentInfo(){
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", System.getProperty("os.name"))
                        .put("Browser", System.getProperty("browserType"))
                        .put("JDK Version", System.getProperty("java.runtime.version"))
                        .put("URL", System.getProperty("baseUrl"))
                        .build(), PropertyReader.getProperty("user.dir")
                        + File.separator + "test-output/allure-results"+File.separator
        );
    }
}
