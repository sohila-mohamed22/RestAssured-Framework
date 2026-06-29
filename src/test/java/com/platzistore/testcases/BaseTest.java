package com.platzistore.testcases;

import com.platziapi.utils.datamanager.JsonReader;
import com.platziapi.validations.Validation;
import com.platziapi.validations.Verification;

public abstract class BaseTest {
    protected Validation validation;
    protected Verification verification;
    protected JsonReader jsonReader;

    public BaseTest() {
        validation = new Validation();
        verification = new Verification();
    }
}
