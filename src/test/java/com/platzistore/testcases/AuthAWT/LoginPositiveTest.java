package com.platzistore.testcases.AuthAWT;

import com.platziapi.models.request.AuthRequest;
import com.platziapi.models.response.AuthResponse;
import com.platziapi.services.AuthAWTServices;
import com.platziapi.utils.StatusCode;
import com.platzistore.testcases.BaseTest;
import org.testng.annotations.Test;

public class LoginPositiveTest extends BaseTest {

    @Test
    public void loginWithValidCredentialsTC(){
        AuthResponse authResponse = AuthAWTServices.loginWithValidCredentials(AuthAWTServices.createAuthRequest("john@mail.com", "changeme"), StatusCode.CREATED);
        validation.assertNotNull(authResponse, "Auth response is null");
        validation.assertNotNull(authResponse.getAccess_token(), "Access token is null");
        validation.assertNotNull(authResponse.getRefresh_token(), "Refresh token is null");
    }

    @Test
    public void retrieveUserProfileByAccessTokenTC(){
        AuthRequest request = AuthAWTServices.createAuthRequest("john@mail.com", "changeme") ;
        AuthResponse authResponse = AuthAWTServices.loginWithValidCredentials(request, StatusCode.CREATED);

        AuthResponse authResponse1 = AuthAWTServices.retrieveUserProfileByAccessToken(authResponse.getAccess_token(), StatusCode.OK);

        validation.assertEquals(authResponse1.getEmail(), request.getEmail(), "Email does not match");
        validation.assertEquals(authResponse1.getPassword(), request.getPassword(), "Password does not match");
    }
}
