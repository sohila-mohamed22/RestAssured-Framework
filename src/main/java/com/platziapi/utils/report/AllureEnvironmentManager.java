package com.platziapi.utils.report;


import com.platziapi.utils.manager.LogsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;

import static java.lang.System.getProperty;

public class AllureEnvironmentManager {
    private static final String ENV_FILE_PATH = "test-output/allure-results/environment.properties";

    public static void setEnvironment(){
        File envFile = new File(ENV_FILE_PATH);
        try {
            String content = "OS_Platform=" + getProperty("os.name") + "\n" +
                    "Java Version=" + getProperty("java.runtime.version") + "\n" ;
            FileUtils.writeStringToFile(envFile, content, "UTF-8");
        } catch (Exception e) {
            LogsManager.error("Error creating environment.properties: " + e.getMessage());
        }
        LogsManager.info("Allure Environment variables set.");
        AllureBinaryManager.downloadAndExtract();

    }
}
