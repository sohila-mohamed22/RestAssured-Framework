package com.platzistore.testcases.products;

import com.platziapi.models.request.ProductRequest;
import com.platziapi.models.response.ProductResponse;
import com.platziapi.utils.StatusCode;
import com.platzistore.testcases.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.platziapi.services.ProductServices;
import com.platziapi.utils.datamanager.DataProvider;

import java.util.List;
import java.util.Random;


@Epic("Platzi Fake Store API Tests")
@Feature("Products Tests")
@Story("Positive Tests")
@Severity(SeverityLevel.NORMAL)
@Owner("Sohaila")
public class ProductPositiveTest extends BaseTest {

    @Description("Get all products")
    @Test
    public void getAllProductsTC(){

        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        validation.assertTrue(products.getFirst().getId() > 0, "Invalid product ID");
        validation.assertTrue(products.getFirst().getPrice() > 0, "Invalid product price");
        validation.assertFalse(products.getFirst().getImages().isEmpty(), "Images list is empty");
        validation.assertNotNull(products.getFirst().getCategory(), "Category is null");
        validation.assertTrue(products.getFirst().getCategory().getId() > 0, "Invalid category ID");

        //  Null checks
        validation.assertNotNull(products.getFirst().getCategory(), "Category is null");
        validation.assertNotNull(products.getFirst().getTitle(), "Title is null");

    }

    @Description("Get single product by valid id")
    @Test
    public void getSingleProductByValidIdTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        verification.assertFalse(products.isEmpty(), "Products list is empty");

        // pick a random product
        Random random = new Random();
        ProductResponse expected = products.get(random.nextInt(products.size()));

        ProductResponse actual = ProductServices.getProductById(expected.getId(), StatusCode.OK);

        validation.assertEquals(actual.getId(), expected.getId(), "Invalid product ID");
        validation.assertEquals(actual.getTitle(), expected.getTitle(), "Invalid product title");
        validation.assertEquals(actual.getPrice(), expected.getPrice(), "Invalid product price");
        validation.assertEquals(actual.getImages(), expected.getImages(), "Invalid product images");
        validation.assertEquals(actual.getCategory(), expected.getCategory(), "Invalid product category");
    }

    @Description("Get related products by valid id")
    @Test
    public void getRelatedProductsByValidIdTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        verification.assertFalse(products.isEmpty(), "Products list is empty");

        // pick a random product
        Random random = new Random();
        ProductResponse expected = products.get(random.nextInt(products.size()));

        @SuppressWarnings("unchecked")
        List<ProductResponse> actual = (List<ProductResponse>) ProductServices.getRelatedProductById(expected.getId(), StatusCode.OK);

        Assert.assertFalse(actual.isEmpty(), "Related products list is empty");

        for(ProductResponse product : actual){
            validation.assertEquals(product.getCategory().getId(), expected.getCategory().getId(), "Invalid product category ID of product ID: " + product.getId());
            validation.assertEquals(product.getCategory().getName(), expected.getCategory().getName(), "Invalid product category name of product ID: " + product.getId());
            validation.assertEquals(product.getCategory().getImage(), expected.getCategory().getImage(), "Invalid product category image of product ID: " + product.getId());
            validation.assertEquals(product.getCategory().getSlug(), expected.getCategory().getSlug(), "Invalid product category slug of product ID: " + product.getId());
        }

    }

    @Description("Get single product by valid slug")
    @Test
    public void getSingleProductByValidSlugTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        verification.assertFalse(products.isEmpty(), "Products list is empty");

        // pick a random product
        Random random = new Random();
        ProductResponse expected = products.get(random.nextInt(products.size()));


        ProductResponse actual = ProductServices.getProductBySlug(expected.getSlug(), StatusCode.OK);

        validation.assertEquals(actual.getId(), expected.getId(), "Invalid product ID");
        validation.assertEquals(actual.getTitle(), expected.getTitle(), "Invalid product title");
        validation.assertEquals(actual.getSlug(), expected.getSlug(), "Invalid product slug");

    }

    @Description("Get related products by valid slug")
    @Test
    public void getRelatedProductsByValidSlugTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        Assert.assertFalse(products.isEmpty(), "Products list is empty");

        // pick a random product
        Random random = new Random();
        ProductResponse expected = products.get(random.nextInt(products.size()));

        @SuppressWarnings("unchecked")
        List<ProductResponse> actual = (List<ProductResponse>) ProductServices.getRelatedProductBySlug(expected.getSlug(), StatusCode.OK);

        Assert.assertFalse(actual.isEmpty(), "Related products list is empty");

        for(ProductResponse product: actual){
            validation.assertEquals(product.getCategory().getId(), expected.getCategory().getId(), "Invalid product category ID of product ID: " + product.getId());
            validation.assertEquals(product.getCategory().getName(), expected.getCategory().getName(), "Invalid product category name of product ID: " + product.getId());
            validation.assertEquals(product.getCategory().getImage(), expected.getCategory().getImage(), "Invalid product category image of product ID: " + product.getId());
            validation.assertEquals(product.getCategory().getSlug(), expected.getCategory().getSlug(), "Invalid product category slug of product ID: " + product.getId());
        }
    }

    @Description("Create product with valid data")
    @Test
    public void createProductWithValidDataTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        verification.assertFalse(products.isEmpty(), "Products list is empty");

        // pick a random product
        Random random = new Random();
        ProductResponse randomProduct = products.get(random.nextInt(products.size()));

        ProductRequest product = ProductRequest.builder()
                .title(DataProvider.getTitle())
                .price(DataProvider.getPrice())
                .description(DataProvider.getDescription())
                .categoryId(randomProduct.getCategory().getId())
                .images(DataProvider.getImages())
                .build();

        ProductResponse actual = ProductServices.createProduct(product, StatusCode.CREATED);

        validation.assertEquals(actual.getTitle(), product.getTitle(), "Invalid product title");
        validation.assertEquals(actual.getPrice(), product.getPrice(), "Invalid product price");
        validation.assertEquals(actual.getImages(), product.getImages(), "Invalid product images");
        validation.assertEquals(actual.getCategory().getId(), product.getCategoryId(), "Invalid product category ID");
    }

    @Description("Update product with valid id data")
    @Test
    public void updateProductWithValidIdDataTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        verification.assertFalse(products.isEmpty(), "Products list is empty");

        // pick a random product
        Random random = new Random();
        ProductResponse expected = products.get(random.nextInt(products.size()));

        ProductRequest productRequest = ProductServices.createProductBody(expected.getCategory().getId());

        ProductResponse actual = ProductServices.updateProduct(expected.getId(), productRequest, StatusCode.OK);

        validation.assertEquals(actual.getId(), expected.getId(), "Invalid product ID");
        validation.assertEquals(actual.getTitle(), productRequest.getTitle(), "Invalid product title");
        validation.assertEquals(actual.getPrice(), productRequest.getPrice(), "Invalid product price");
        validation.assertEquals(actual.getImages(), productRequest.getImages(), "Invalid product images");
        validation.assertEquals(actual.getCategory().getId(), productRequest.getCategoryId(), "Invalid product category ID");

    }

    @Description("Delete existing product")
    @Test
    public void deleteExistingProductTC(){
        List<ProductResponse> products = ProductServices.getProducts(StatusCode.OK);

        Assert.assertFalse(products.isEmpty(), "Products list is empty");

        // pick a random product
        Random random = new Random();
        int randomId = products.get(random.nextInt(products.size())).getId();

        String actual = (String) ProductServices.deleteProduct(randomId, StatusCode.OK);

        validation.assertTrue(actual.contains("true"), "Product deletion failed");
    }

    @Description("Retrieve products in manageable chunks")
    @Test
    public void retrieveProductsInManageableChunksTC(){
        List<ProductResponse> page1 = ProductServices.getProducts(0, 20, StatusCode.OK);

        List<ProductResponse> page2 = ProductServices.getProducts(20, 20, StatusCode.OK);

        List<ProductResponse> page3 = ProductServices.getProducts(40, 20, StatusCode.OK);

        // 1. size check
        validation.assertEquals(page1.size(), 20, "Page 1 should contain 20 items");
        validation.assertEquals(page2.size(), 20, "Page 2 should contain 20 items");
        validation.assertEquals(page3.size(), 20, "Page 3 should contain 20 items");

        // 2. no overlap check
        validation.assertNotEquals(
                page1.getFirst().getId(),
                page2.getFirst().getId(),
                "First item of Page 1 should not match first item of Page 2 (pagination overlap detected)"
        );

        validation.assertNotEquals(
                page2.getFirst().getId(),
                page3.getFirst().getId(),
                "First item of Page 2 should not match first item of Page 3 (pagination overlap detected)"
        );
    }

}
