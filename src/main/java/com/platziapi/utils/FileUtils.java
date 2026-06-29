package com.platziapi.utils;

import com.platziapi.utils.manager.LogsManager;
import com.platziapi.utils.report.AllureConstants;

import java.io.File;
import java.io.IOException;

import static java.lang.System.getProperty;
import static org.apache.commons.io.FileUtils.copyFile;

public class FileUtils {
    private static final String USER_DIR = getProperty("user.dir")+ File.separator;
    private FileUtils() {
        // Prevent instantiation
    }

    // Renaming
    public static void renameFile(String oldName, String newName) {
        try {
            var targetFile = new File(oldName);
            String targetDirectory = targetFile.getParentFile().getAbsolutePath();
            File newFile = new File(targetDirectory + File.separator + newName);
            if (!targetFile.getPath().equals(newFile.getPath())) {
                copyFile(targetFile, newFile);
                org.apache.commons.io.FileUtils.forceDeleteOnExit(targetFile);
                LogsManager.info("Target File Path: \"" + oldName + "\", file was renamed to \"" + newName + "\".");
            } else {
                LogsManager.info(("Target File Path: \"" + oldName + "\", already has the desired name \"" + newName + "\"."));
            }
        } catch (IOException e) {
            LogsManager.error("renameFile() failed: " + e.getMessage());
        }
    }


    //creating Directory
    public static void createDirectory(String path) {
        try {
            File file = new File(USER_DIR+ path);
            if (!file.exists())
            {
                file.mkdirs();
                LogsManager.info("Directory created: " + path);
            }
        }
        catch (Exception e) {
            LogsManager.error("Failed to create directory: " + path, e.getMessage());
        }
    }

    //force delete
    public static void forceDelete(File file) {
        try {
            org.apache.commons.io.FileUtils.forceDeleteOnExit(file);
            LogsManager.info("File deleted: " + file.getAbsolutePath());
        } catch (IOException e) {
            LogsManager.error("Failed to delete file: " + file.getAbsolutePath(), e.getMessage());
        }
    }

    // cleaning Directory
    public static void cleanDirectory(File file)
    {
        try {
            org.apache.commons.io.FileUtils.deleteQuietly(file); //in use > skip
        }
        catch (Exception e) {
            LogsManager.error("Failed to clean directory: " + file.getAbsolutePath(), e.getMessage());
        }
    }


    //check if the file exists
    public static boolean isFileExists( String filaName) {
        File file = new File(AllureConstants.DOWNLOAD_PATH.toString() + filaName);
        return file.exists();
    }

    //wait for file to be downloaded
    public static boolean isFileExists(String fileName, int numberOfRetries) {
        boolean isFileExist = false;
        int i = 0;
        while (i < numberOfRetries) {
            try {
                String filePath = AllureConstants.DOWNLOAD_PATH.toString() ;
                isFileExist = (new File(filePath + File.separator + fileName)).getAbsoluteFile().exists();
            } catch (Exception e) {
                LogsManager.error( e.getMessage());
            }
            if (!isFileExist) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    LogsManager.error(e.getMessage());
                }
            }
            i++;
        }
        return isFileExist;
    }
}