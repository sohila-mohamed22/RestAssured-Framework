package com.platziapi.services;

import com.platziapi.models.response.CategoryResponse;
import com.platziapi.specs.RequestSpecs;
import com.platziapi.specs.ResponseSpecs;
import com.platziapi.utils.StatusCode;
import com.platziapi.utils.helper.Constants;
import com.platziapi.utils.helper.RestHelper;
import com.platziapi.utils.manager.LogsManager;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;

import java.util.List;

public class CategoryServices {
    @Step("Getting all categories")
    public static List<CategoryResponse> getCategories(StatusCode statusCode){
        LogsManager.info("Getting all categories");
        return RestHelper.restGet(RequestSpecs.requestSpec(), Constants.CATEGORY_ENDPOINT, ResponseSpecs.responseSpec(statusCode.getCode()), new TypeRef<List<CategoryResponse>>() {});
    }

    @Step("Getting single category by id: {id}")
    public static CategoryResponse getCategoryById(int id, StatusCode statusCode){
        LogsManager.info("Getting single Category by id: " + id);
        return RestHelper.restGet(RequestSpecs.requestSpec(), Constants.CATEGORY_ENDPOINT + "/" + id, ResponseSpecs.responseSpec(statusCode.getCode()), CategoryResponse.class) ;
    }
}
