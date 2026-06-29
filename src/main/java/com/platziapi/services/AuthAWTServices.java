package com.platziapi.services;

import com.platziapi.models.request.AuthRequest;
import com.platziapi.models.response.AuthResponse;
import com.platziapi.specs.RequestSpecs;
import com.platziapi.specs.ResponseSpecs;
import com.platziapi.utils.StatusCode;
import com.platziapi.utils.datamanager.DataProvider;
import com.platziapi.utils.helper.Constants;
import com.platziapi.utils.helper.RestHelper;
import com.platziapi.utils.manager.LogsManager;
import io.qameta.allure.Step;

public class AuthAWTServices {

    @Step("Login with valid credentials")
    public static AuthResponse loginWithValidCredentials(AuthRequest authRequest, StatusCode statusCode){
        LogsManager.info("Login with valid credentials: " + authRequest);
        return RestHelper.restPost(RequestSpecs.requestSpec(), Constants.AUTH_ENDPOINT + "/login", authRequest, ResponseSpecs.responseSpec(statusCode.getCode()),  AuthResponse.class);
    }

    public static AuthResponse retrieveUserProfileByAccessToken(String accessToken, StatusCode statusCode){
        LogsManager.info("Retrieve user profile by access token: " + accessToken);
        return RestHelper.restGet(RequestSpecs.requestSpec(accessToken), Constants.AUTH_ENDPOINT + "/profile", ResponseSpecs.responseSpec(statusCode.getCode()), AuthResponse.class);
    }


    public static AuthRequest createAuthRequest(String email, String password){
        return AuthRequest
                .builder()
                .email(email)
                .password(password)
                .build();
    }
}
