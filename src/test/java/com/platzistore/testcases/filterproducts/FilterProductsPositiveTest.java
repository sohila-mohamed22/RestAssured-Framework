package com.platzistore.testcases.filterproducts;

import com.platziapi.models.response.ProductResponse;
import com.platziapi.services.ProductServices;
import com.platziapi.utils.StatusCode;
import com.platzistore.testcases.BaseTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class FilterProductsPositiveTest extends BaseTest {

    @Test
    public void filterProductsByExistingPriceTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        verification.assertFalse(products.isEmpty(), "Products list is empty");

        // pick a random product
        Random random = new Random();
        int randomPrice = products.get(random.nextInt(products.size())).getPrice();

        List<ProductResponse> filteredResponse = ProductServices.filterProductByPrice(randomPrice, StatusCode.OK);

        for(ProductResponse response : filteredResponse ){
            validation.assertEquals(response.getPrice(), randomPrice, "Price does not match");
        }

    }

    @Test
    public void filterProductsByExistingPriceRangeTC(){
        int minPrice = 100 ;
        int maxPrice = 500;

        List<ProductResponse> filteredResponse = ProductServices.filterProductByPrice(minPrice, maxPrice, StatusCode.OK);

        for(ProductResponse response : filteredResponse ){
            validation.assertTrue((response.getPrice() >= minPrice && response.getPrice() <= maxPrice), "Price does not match");
        }
    }

}
