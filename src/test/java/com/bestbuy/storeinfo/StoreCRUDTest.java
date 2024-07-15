package com.bestbuy.storeinfo;

import com.bestbuy.info.StoreSteps;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class StoreCRUDTest {

    static String name = "Dunelm Store" + TestUtils.getRandomValue();
    static String type = "Furniture" + TestUtils.getRandomValue();
    static String address = "32, Studley villa" + TestUtils.getRandomValue();
    static String address2 = "";
    static String city = "Solihull";
    static String state = "West mid";
    static String zip = TestUtils.getRandomValue();
    static double lat = 44.898855;
    static double lng = -36.556651;
    static String hours = "Mon: 9-10; Tue: 9-10; Wed: 9-10; Thurs: 9-10; Fri: 9-10; Sat: 11-6; Sun: 12-5";

    static int storeId;

    @Steps
    StoreSteps storeSteps;

    @Title("Create a store")
    @Test
    public void test001() {
        ValidatableResponse response = storeSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours);
        response.statusCode(201);
        response.log().body();

        storeId = response.extract().path("id");
    }

    @Title("Get store  info by id")
    @Test
    public void test002(){
        ValidatableResponse response = storeSteps.getStoreById(storeId);
        response.statusCode(200);
    }

    @Title("Test003 - Updating store info by id and verify updated details")
    @Test
    public void test003(){
        String uname = "Update " + StoreCRUDTest.name;
        String address = "Update Prime Add1";

        ValidatableResponse response = storeSteps.updateStoreInfoById(storeId,uname, type, address, address2, city, state, zip, lat, lng, hours);
        response.statusCode(200);
    }

    @Title("Deleting store")
    @Test
    public void test004(){
        storeSteps.deleteStoreById(storeId).statusCode(200);
        storeSteps.getStoreById(storeId).statusCode(404);
    }

}
