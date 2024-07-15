package com.bestbuy.productinfo;

import com.bestbuy.info.ProductSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class ProductCRUDTest extends TestBase {

    static String name = TestUtils.generateName();
    static String type = TestUtils.generateType();
    static double price = TestUtils.generatePrice();
    static String upc = TestUtils.getRandomValue();
    static double shipping = TestUtils.generateShipping();
    static String description = "iPhone 14 pro; 1500 mpx camera; Slim body";
    static String manufacturer = TestUtils.generateManufacturer();
    static String model = "14 pro";
    static String url = TestUtils.generateUrl();
    static String image = TestUtils.generateImageUrl();

    static int productId;


    @Steps
    ProductSteps productSteps;

    @Title("Create Product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name, type, price, upc, shipping, description, manufacturer, model, url, image);
        response.statusCode(201);
        response.log().all();
        productId = response.extract().path("id");
    }

    @Title(" Get product info by id")
    @Test
    public void test002() {
        ValidatableResponse response = productSteps.getProductById(productId);
        response.statusCode(200);
        response.body("id", equalTo(productId));
    }

    @Title("Update product info by id")
    @Test
    public void test003() {
        String name2 = "Update name";
        String type2 = "Updated type";

        ValidatableResponse response = productSteps.updateProductById(9999690, name2, type2);
        response.statusCode(200);
        response.log().all();
//        response.body("name", equalTo(name2));
//        response.body("type", equalTo(type2));
    }

    @Title("Delete product info by id")
    @Test
    public void test004() {
        productSteps.deleteProductById(productId).statusCode(200);
        productSteps.getProductById(productId).statusCode(404);
    }

}
