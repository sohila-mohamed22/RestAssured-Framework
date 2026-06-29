package com.platzistore.testcases.categories;

import com.platziapi.models.request.ProductRequest;
import com.platziapi.models.response.CategoryResponse;
import com.platziapi.models.response.ProductResponse;
import com.platziapi.services.CategoryServices;
import com.platziapi.services.ProductServices;
import com.platziapi.utils.StatusCode;
import com.platziapi.utils.datamanager.DataProvider;
import com.platzistore.testcases.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;


@Epic("Platzi Fake Store API Tests")
@Feature("Products Tests")
@Story("Positive Tests")
@Severity(SeverityLevel.NORMAL)
@Owner("Sohaila")
public class CategoryPositiveTest extends BaseTest {

    @Description("Get all categories")
    @Test
    public void getAllCategoriesTC(){

        List<CategoryResponse> categories = CategoryServices.getCategories(StatusCode.OK);

        validation.assertFalse(categories.isEmpty(), "Categories list is empty");

    }

    @Description("Get single category by valid id")
    @Test
    public void getSingleCategoryByValidIdTC(){
        List<CategoryResponse> categories = CategoryServices.getCategories(StatusCode.OK);

        verification.assertFalse(categories.isEmpty(), "Categories list is empty");

        // pick a random product
        Random random = new Random();
        CategoryResponse expected = categories.get(random.nextInt(categories.size()));

        CategoryResponse actual = CategoryServices.getCategoryById(expected.getId(), StatusCode.OK);

        validation.assertEquals(actual.getId(), expected.getId(), "Invalid category ID");
        validation.assertEquals(actual.getName(), expected.getName(), "Invalid category name");
        validation.assertEquals(actual.getSlug(), expected.getSlug(), "Invalid category slug");
        validation.assertEquals(actual.getImage(), expected.getImage(), "Invalid category image");
    }






}
