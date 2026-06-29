package com.platzistore.testcases.AuthAWT;

import com.platziapi.models.request.AuthRequest;
import com.platziapi.models.response.AuthResponse;
import com.platziapi.services.AuthAWTServices;
import com.platziapi.utils.StatusCode;
import com.platzistore.testcases.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class LoginNegativeTest extends BaseTest {

    @Description("Login with invalid credentials")
    @Test
    public void loginWithInvalidCredentialsTC(){
        AuthRequest request = AuthAWTServices.createAuthRequest("john12@mail.com", "changeme") ;
        AuthResponse authResponse = AuthAWTServices.loginWithValidCredentials(request, StatusCode.UNAUTHORIZED);

        validation.assertEquals(authResponse.getMessage(), "Unauthorized", "Message does not match");
        validation.assertEquals(authResponse.getStatusCode(), "401", "Status code does not match");
    }

    @Description("Login with invalid Access Token")
    @Test
    public void loginWithInvalidAccessTokenTC(){
        AuthResponse authResponse = AuthAWTServices.retrieveUserProfileByAccessToken("123", StatusCode.UNAUTHORIZED);

        validation.assertEquals(authResponse.getMessage(), "Unauthorized", "Message does not match");
        validation.assertEquals(authResponse.getStatusCode(), "401", "Status code does not match");
    }
}
