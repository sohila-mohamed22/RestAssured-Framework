package com.platziapi.utils.report;


import com.platziapi.utils.FileUtils;
import com.platziapi.utils.OSUtils;
import com.platziapi.utils.TerminalUtils;
import com.platziapi.utils.manager.TimeManager;
import com.platziapi.utils.manager.LogsManager;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.platziapi.utils.report.AllureConstants.HISTORY_FOLDER;
import static com.platziapi.utils.report.AllureConstants.RESULTS_HISTORY_FOLDER;
import static java.lang.System.getProperty;

public class AllureReportGenerator {
    //Generate Allure report
    //--single-file - generate single file report
    public static void generateReports(boolean isSingleFile) {
        Path outputFolder = isSingleFile ? AllureConstants.REPORT_PATH : AllureConstants.FULL_REPORT_PATH;
        // allure generate -o reports --single-file --clean
        List<String> command = new ArrayList<>(List.of(
                AllureBinaryManager.getExecutable().toString(),
                "generate",
                AllureConstants.RESULTS_FOLDER.toString(),
                "-o", outputFolder.toString(),
                "--clean"
        ));
        if (isSingleFile) command.add("--single-file");
        TerminalUtils.executeTerminalCommand(command.toArray(new String[0]));
    }

    //rename report file to AllureReport_timestamp.html
    public static String renameReport() {
        String newFileName = AllureConstants.REPORT_PREFIX + TimeManager.getTimestamp() + AllureConstants.REPORT_EXTENSION; // AllureReport_20250720_211230.html
        FileUtils.renameFile(AllureConstants.REPORT_PATH.resolve(AllureConstants.INDEX_HTML).toString(), newFileName);
        return newFileName;
    }

    //open Allure report in browser
    public static void openReport(String reportFileName) {

        Path reportPath = AllureConstants.REPORT_PATH.resolve(reportFileName);
        switch (OSUtils.getCurrentOS()) {
            case WINDOWS -> TerminalUtils.executeTerminalCommand("cmd.exe", "/c", "start", reportPath.toString());
            case MAC, LINUX -> TerminalUtils.executeTerminalCommand("open", reportPath.toString());
            default -> LogsManager.warn("Opening Allure Report is not supported on this OS.");
        }
    }

    //copy history folder to results folder
    public static void copyHistory() {
        try {
            org.apache.commons.io.FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULTS_HISTORY_FOLDER.toFile());
        } catch (Exception e) {
            LogsManager.error("Error copying history files", e.getMessage());
        }
    }

}