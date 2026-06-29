package com.platziapi.utils.report;

import com.platziapi.utils.manager.LogsManager;
import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;


public class AllureAttachmentManager {

    public static void attachLogs() {
        try {
            File logFile = new File(LogsManager.LOGS_PATH +"logs.log");
            if (logFile.exists()) {
                Allure.attachment("logs.log", Files.readString(logFile.toPath()));
            }
            LogsManager.info("Logs attached");
        } catch (Exception e) {
            LogsManager.error("Error attaching logs", e.getMessage());
        }
    }

}