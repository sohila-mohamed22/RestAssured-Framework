package com.platziapi.validations;

import com.platziapi.models.response.CategoryResponse;
import com.platziapi.utils.manager.LogsManager;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class Validation {
    private static  SoftAssert softAssert = new SoftAssert();
    private static boolean used = false; // Flag to track usage

    public void assertTrue(boolean condition, String message) {
        used = true;
        softAssert.assertTrue(condition,message);
    }

    public void assertFalse(boolean condition, String message) {
        used = true;
        softAssert.assertFalse(condition,message);
    }

    public void assertEquals(Object actual ,Object expected, String message) {
        used = true;
        softAssert.assertEquals(actual,expected,message);
    }

    public void assertNotNull(Object condition, String message) {
        used = true;
        softAssert.assertNotNull(condition,message);
    }

    public void assertNotEquals(Object object1, Object object2, String message) {
        used = true;
        softAssert.assertNotEquals(object1,object2,message);
    }

    public static void assertAll(ITestResult result){
        if (!used) return; // If no assertions were made, do nothing
        try {
            softAssert.assertAll();
        }
        catch (AssertionError e)
        {
            LogsManager.error("Assertion failed:", e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        }
        finally {
            softAssert = new SoftAssert(); // Reset the soft assert instance
        }
    }

}
