package com.platziapi.customelisteners;

import com.platziapi.utils.FileUtils;
import com.platziapi.utils.manager.LogsManager;
import com.platziapi.utils.report.AllureAttachmentManager;
import com.platziapi.utils.report.AllureConstants;
import com.platziapi.utils.report.AllureEnvironmentManager;
import com.platziapi.utils.report.AllureReportGenerator;
import com.platziapi.validations.Validation;
import org.testng.*;

public class TestNGListener implements ISuiteListener, IExecutionListener, ITestListener, IInvokedMethodListener {
    public void onStart(ISuite suite) {
        suite.getXmlSuite().setName("Platzi Fake Store API Tests");
    }

    public void onExecutionStart() {
        LogsManager.info("Test Execution started");
        cleanTestOutputDirectories();
        LogsManager.info("Directories cleaned");
        AllureEnvironmentManager.setEnvironment();
        LogsManager.info("Allure environment set");
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if(method.isTestMethod()){
            AllureAttachmentManager.attachLogs();
            Validation.assertAll(testResult);
        }
    }

    public void onExecutionFinish() {
        AllureReportGenerator.copyHistory();
        AllureReportGenerator.generateReports(false);
        AllureReportGenerator.generateReports(true);
        AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
        LogsManager.info("Test Execution Finished");
    }

    public void onTestSuccess(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " passed");
    }

    public void onTestFailure(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " failed");
    }

    public void onTestSkipped(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " skipped");
    }




    // cleaning and creating dirs (logs, screenshots, recordings,allure-results)
    private void cleanTestOutputDirectories() {
        // Implement logic to clean test output directories
        FileUtils.cleanDirectory(AllureConstants.RESULTS_FOLDER.toFile());

    }


}
