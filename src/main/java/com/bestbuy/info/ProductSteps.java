package com.bestbuy.info;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class ProductSteps {

    @Step("Create a product with name: {0}, type: {1}, price: {2}, upc: {3}, shipping: {4}, description: {5}, manufacturer: {6}, model: {7}, url: {8}, image: {9}")
    public ValidatableResponse createProduct(String name, String type, double price, String upc, double shipping, String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, upc, shipping, description, manufacturer, model, url, image);
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post(EndPoints.CREATE_PRODUCT)
                .then();
    }

    @Step("Get product info by productId: {0}")
    public ValidatableResponse getProductById(int id){
        return SerenityRest.given()
                .pathParam("productID", id)
                .when()
                .get(EndPoints.GET_PRODUCT_BY_ID)
                .then();
    }

    @Step("Update product info by productId: {0}")
    public ValidatableResponse updateProductById(int id, String name, String type){
        ProductPojo productPojo = ProductPojo.getPatchProductPojo(name, type);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .pathParam("productID", id)
                .when()
                .body(productPojo)
                .patch(EndPoints.PATCH_PRODUCT_BY_ID)
                .then();
    }

    @Step("Delete product by id: {0}")
    public ValidatableResponse deleteProductById(int id) {

        return SerenityRest.given()
                .pathParam("productID", id)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

}
