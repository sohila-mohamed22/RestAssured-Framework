package com.platziapi.services;

import com.platziapi.specs.ResponseSpecs;
import com.platziapi.utils.StatusCode;
import com.platziapi.utils.datamanager.DataProvider;
import com.platziapi.utils.helper.QueryParams;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import com.platziapi.models.request.ProductRequest;
import com.platziapi.models.response.ProductResponse;
import com.platziapi.specs.RequestSpecs;
import com.platziapi.utils.helper.Constants;
import com.platziapi.utils.manager.LogsManager;
import com.platziapi.utils.helper.RestHelper;

import java.util.HashMap;
import java.util.List;

/**
 * Service layer for Product endpoints.
 *
 * Responsibilities:
 * - Get all products
 * - Get product by ID
 * - Create product
 * - Update product
 * - Delete product
 *
 * Contains endpoint-specific business operations
 * and uses RestHelper to perform requests.
 */
public class ProductServices {

    @Step("Getting all products")
    public static List<ProductResponse> getProducts(StatusCode statusCode){
        return RestHelper.restGet(RequestSpecs.requestSpec(), Constants.PRODUCT_ENDPOINT, ResponseSpecs.responseSpec(statusCode.getCode()), new TypeRef<List<ProductResponse>>() {});
    }

    @Step("Getting products with offset: {offset} and limit: {limit}")
    public static List<ProductResponse> getProducts(int offset, int limit, StatusCode statusCode){
        HashMap<String,String> parameters = QueryParams.createQueryParams("offset", String.valueOf(offset), "limit", String.valueOf(limit));
        return RestHelper.restGet(RequestSpecs.requestSpec(parameters), Constants.PRODUCT_ENDPOINT, ResponseSpecs.responseSpec(statusCode.getCode()), new TypeRef<List<ProductResponse>>() {});
    }


    @Step("Getting single product by id: {id}")
    public static ProductResponse getProductById(int id, StatusCode statusCode){
        LogsManager.info("Getting single product by id: " + id);
        return RestHelper.restGet(RequestSpecs.requestSpec(), Constants.PRODUCT_ENDPOINT + "/" + id, ResponseSpecs.responseSpec(statusCode.getCode()), ProductResponse.class) ;
    }

    @Step("Getting related products by id: {id}")
    public static Object getRelatedProductById(int id, StatusCode statusCode){
        LogsManager.info("Getting related products by id: " + id);
        return statusCode == StatusCode.OK ? RestHelper.restGet(RequestSpecs.requestSpec(), Constants.PRODUCT_ENDPOINT + "/" + id + "/related", ResponseSpecs.responseSpec(statusCode.getCode()), new TypeRef<List<ProductResponse>>() {}) :
                RestHelper.restGet(RequestSpecs.requestSpec(), Constants.PRODUCT_ENDPOINT + "/" + id + "/related", ResponseSpecs.responseSpec(statusCode.getCode()), ProductResponse.class);
    }


    @Step("Getting single product by slug: {slug}")
    public static ProductResponse getProductBySlug(String slug, StatusCode statusCode){
        LogsManager.info("Getting not-related product by slug: " + slug);
        return  RestHelper.restGet(RequestSpecs.requestSpec(), Constants.PRODUCT_ENDPOINT + "/slug/" + slug, ResponseSpecs.responseSpec(statusCode.getCode()), ProductResponse.class) ;
    }

    @Step("Getting related products by slug: {slug}")
    public static Object getRelatedProductBySlug(String slug, StatusCode statusCode){
        LogsManager.info("Getting related product by slug: " + slug);
        return statusCode == StatusCode.OK ? RestHelper.restGet(RequestSpecs.requestSpec(), Constants.PRODUCT_ENDPOINT + "/slug/" + slug + "/related", ResponseSpecs.responseSpec(statusCode.getCode()), new TypeRef<List<ProductResponse>>() {}) :
                RestHelper.restGet(RequestSpecs.requestSpec(), Constants.PRODUCT_ENDPOINT + "/slug/" + slug + "/related", ResponseSpecs.responseSpec(statusCode.getCode()), ProductResponse.class);
    }

    @Step("Creating product body with category id: {categoryId}")
    public static ProductRequest createProductBody(int categoryId){
        return createProductBody(DataProvider.getTitle(), categoryId);
    }

    @Step("Creating product with title: {title} and category id: {categoryId}")
    public static ProductRequest createProductBody(String title, int categoryId){
        return ProductRequest.builder()
                .title(title)
                .price(DataProvider.getPrice())
                .description(DataProvider.getDescription())
                .categoryId(categoryId)
                .images(DataProvider.getImages())
                .build();
    }
    @Step("Creating product: {product}")
    public static ProductResponse createProduct(ProductRequest product, StatusCode statusCode){
        LogsManager.info("Creating product: " + product);
        return RestHelper.restPost(RequestSpecs.requestSpec(), Constants.PRODUCT_ENDPOINT, product, ResponseSpecs.responseSpec(statusCode.getCode()), ProductResponse.class);
    }

    @Step("Updating product: {product} | id: {id}")
    public static ProductResponse updateProduct(int id, ProductRequest product, StatusCode statusCode){
        LogsManager.info("Updating product: " + product);
        return RestHelper.restPut(RequestSpecs.requestSpec(), Constants.PRODUCT_ENDPOINT + "/" + id, product, ResponseSpecs.responseSpec(statusCode.getCode()), ProductResponse.class);
    }

    @Step("Deleting product: {id}")
    public static Object deleteProduct(int id, StatusCode statusCode){
        LogsManager.info("Deleting product: " + id);
        return statusCode == StatusCode.OK ? RestHelper.restDelete(RequestSpecs.requestSpec(), Constants.PRODUCT_ENDPOINT + "/" + id, ResponseSpecs.responseSpec(statusCode.getCode())) :
                RestHelper.restDelete(RequestSpecs.requestSpec(), Constants.PRODUCT_ENDPOINT + "/" + id, ResponseSpecs.responseSpec(statusCode.getCode()), ProductResponse.class);
    }

    public static List<ProductResponse> filterProductByPrice(int price, StatusCode statusCode){
        LogsManager.info("Filtering product by price: " + price);
        HashMap<String,String> parameters = QueryParams.createQueryParams("price", String.valueOf(price));
        return RestHelper.restGet(RequestSpecs.requestSpec(parameters), Constants.PRODUCT_ENDPOINT , ResponseSpecs.responseSpec(statusCode.getCode()), new TypeRef<List<ProductResponse>>() {});
    }


    public static List<ProductResponse> filterProductByPrice(int minPrice, int maxPrice, StatusCode statusCode){
        LogsManager.info("Filtering product by price range min: " + minPrice, "max: " + maxPrice);
        HashMap<String,String> parameters = QueryParams.createQueryParams("price_min", String.valueOf(minPrice), "price_max", String.valueOf(maxPrice));
        return RestHelper.restGet(RequestSpecs.requestSpec(parameters), Constants.PRODUCT_ENDPOINT , ResponseSpecs.responseSpec(statusCode.getCode()), new TypeRef<List<ProductResponse>>() {});
    }



}
