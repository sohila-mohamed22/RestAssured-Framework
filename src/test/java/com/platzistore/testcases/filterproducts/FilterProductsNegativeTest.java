package com.platzistore.testcases.filterproducts;

import com.platziapi.models.response.ProductResponse;
import com.platziapi.services.ProductServices;
import com.platziapi.utils.StatusCode;
import com.platzistore.testcases.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

public class FilterProductsNegativeTest extends BaseTest {

    @Test
    public void filterProductsByNotExistingPriceTC(){
        List<ProductResponse> filteredResponse = ProductServices.filterProductByPrice(12025, StatusCode.OK);

        verification.assertTrue(filteredResponse.isEmpty(), "This price exists");
    }


}
