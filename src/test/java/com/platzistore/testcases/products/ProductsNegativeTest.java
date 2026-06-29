package com.platzistore.testcases.products;

import com.platziapi.models.request.ProductRequest;
import com.platziapi.models.response.ProductResponse;
import com.platziapi.services.ProductServices;
import com.platziapi.utils.StatusCode;
import com.platziapi.utils.datamanager.DataProvider;
import com.platziapi.utils.datamanager.JsonReader;
import com.platziapi.utils.manager.TimeManager;
import com.platzistore.testcases.BaseTest;
import io.qameta.allure.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Epic("Platzi Fake Store API Tests")
@Feature("Products Tests")
@Story("Negative Tests")
@Severity(SeverityLevel.NORMAL)
@Owner("Sohaila")
public class ProductsNegativeTest extends BaseTest {

    @Description("Get single product by not existing id")
    @Test
    public void getSingleProductByNotExistsIdTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        verification.assertFalse(products.isEmpty(), "Products list is empty");

        Set<Integer> existingIds = products.stream()
                .map(ProductResponse::getId)
                .collect(Collectors.toSet());

        int randomId ;
        do {
            randomId = DataProvider.getId();
        } while (existingIds.contains(randomId));

        ProductResponse response = ProductServices.getProductById(randomId, StatusCode.BAD_REQUEST);

        validation.assertTrue((response.getName()).contains(jsonReader.getJsonData("name")), "Name not found");
        validation.assertTrue((response.getMessage()).contains(jsonReader.getJsonData("message")), "Message not found");
    }

    @Description("Get single product by invalid id")
    @Test
    public void getSingleProductByInvalidIdTC(){
         int invalidId = -1 ;

        ProductResponse response = ProductServices.getProductById(invalidId, StatusCode.BAD_REQUEST);

        validation.assertTrue((response.getName()).contains(jsonReader.getJsonData("errors.notFound.name")), "Name not found");
        validation.assertTrue((response.getMessage()).contains(jsonReader.getJsonData("errors.notFound.message")), "Message not found");
    }

    @Description("Get related products by not existing id")
    @Test
    public void getRelatedProductsByNotExistingIdTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        verification.assertFalse(products.isEmpty(), "Products list is empty");

        Set<Integer> existingIds = products.stream()
                .map(ProductResponse::getId)
                .collect(Collectors.toSet());

        int randomId ;
        do {
            randomId = DataProvider.getId();
        } while (existingIds.contains(randomId));

        ProductResponse response = (ProductResponse) ProductServices.getRelatedProductById(randomId, StatusCode.BAD_REQUEST);

        validation.assertTrue((response.getName()).contains(jsonReader.getJsonData("errors.notFound.name")), "Name not found");
        validation.assertTrue((response.getMessage()).contains(jsonReader.getJsonData("errors.notFound.name")), "Message not found");
    }

    @Description("Get single product by invalid slug")
    @Test
    public void getSingleProductByInvalidSlugTC(){
        // Use a guaranteed invalid slug
        String invalidSlug = DataProvider.getDescription();

        // Call API expecting failure
        ProductResponse response = ProductServices.getProductBySlug(invalidSlug, StatusCode.BAD_REQUEST);



        validation.assertTrue((response.getName()).contains(jsonReader.getJsonData("errors.notFound.name")), "Name not found");
        validation.assertTrue((response.getMessage()).contains(jsonReader.getJsonData("errors.notFound.message")), "Message not found");
    }

    @Description("Get related products by not existing slug")
    @Test
    public void getRelatedProductsByNotExistingSlugTC(){
        // Use a guaranteed invalid slug
        String invalidSlug = DataProvider.getDescription();

        // Call API expecting failure
        ProductResponse response = (ProductResponse) ProductServices.getRelatedProductBySlug(invalidSlug, StatusCode.BAD_REQUEST);


        validation.assertTrue((response.getName()).contains(jsonReader.getJsonData("errors.notFound.name")), "Name not found");
        validation.assertTrue((response.getMessage()).contains(jsonReader.getJsonData("errors.notFound.message")), "Message not found");
    }

    @Description("Create product with existing title")
    @Test
    public void createProductWithExistingTitleTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        verification.assertFalse(products.isEmpty(), "Products list is empty");

        // pick a random product
        Random random = new Random();
        ProductResponse randomProduct = products.get(random.nextInt(products.size()));

        ProductRequest product = ProductServices.createProductBody(randomProduct.getTitle(), randomProduct.getCategory().getId());

        ProductResponse response = ProductServices.createProduct(product, StatusCode.BAD_REQUEST);

        validation.assertTrue((response.getName()).contains(jsonReader.getJsonData("errors.duplicateSlug.name")), "Name not found");
        validation.assertTrue((response.getMessage()).contains(jsonReader.getJsonData("errors.duplicateSlug.message")), "Message not found");
    }

    @Description("Create product with not existing category id")
    @Test
    public void createProductWithNotExistingCategoryIdTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        verification.assertFalse(products.isEmpty(), "Products list is empty");

        // pick a random product
        Random random = new Random();
        ProductResponse randomProduct = products.get(random.nextInt(products.size()));

        ProductRequest product = ProductServices.createProductBody(Integer.parseInt(String.valueOf(TimeManager.getSimpleTimestamp()).substring(0, 4)));

        ProductResponse response = ProductServices.createProduct(product, StatusCode.BAD_REQUEST);

        validation.assertTrue((response.getName()).contains(jsonReader.getJsonData("errors.notFound.name")), "Name not found");
        validation.assertTrue((response.getMessage()).contains(jsonReader.getJsonData("errors.notFound.message")), "Message not found");
    }

    @Description("Delete not existing product")
    @Test
    public void deleteNotExistingProductTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        verification.assertFalse(products.isEmpty(), "Products list is empty");

        Set<Integer> existingIds = products.stream()
                .map(ProductResponse::getId)
                .collect(Collectors.toSet());

        int randomId ;
        do {
            randomId = DataProvider.getId();
        } while (existingIds.contains(randomId));

        ProductResponse response = (ProductResponse) ProductServices.deleteProduct(randomId, StatusCode.BAD_REQUEST);

        validation.assertTrue((response.getName()).contains(jsonReader.getJsonData("errors.notFound.name")), "Name not found");
        validation.assertTrue((response.getMessage()).contains(jsonReader.getJsonData("errors.notFound.message")), "Message not found");
    }



    // configuration
    @BeforeClass
    public void setup(){
        jsonReader = new JsonReader("product-data");
    }

}
