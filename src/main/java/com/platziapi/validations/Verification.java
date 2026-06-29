package com.platziapi.validations;

import org.testng.Assert;

public class Verification{



    public void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition,message);
    }

    public void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition,message);
    }


    public void assertEquals(String actual ,String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }
}
