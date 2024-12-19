package com.bda;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;

import org.testng.annotations.BeforeTest;
import com.bda.fakestore.ProductService;

/**
 * Unit test for simple App.
 * 
 */
public class AppTestCart {
    ProductService productService;

    @BeforeTest
    public void setup() {
        productService = new ProductService();
    }

    @Test // allcart
    public void getAllCartTest() {
        Response response = new ProductService().getAllCart().getResponse();
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK", "Correct status code returned");
    }

    @Test // Singleatest
    public void singleCartTest() {
        String actual = productService.setCartDetail("5").asJSON().get("date");
        String expected = "2020-03-01T00:00:00.000Z";
        Assert.assertEquals(actual, expected);
    }

    @Test // Limitresult
    public void cartLimitTest() {
        String result = productService.setCartLimit("5").getResponseBody().asString();
        System.out.println(result);
    }

    @Test // Sortresults
    public void cartOrderLimitTest() {
        String result = productService.setCartSortAndLimit("desc", "2").getResponseBody().asString();
        System.out.println(result);
    }

    @Test // Daterange
    public void cartinDateRangeTest() {
        String result = productService.setCartDateRange("2020-03-01", "2020-03-10").getResponseBody().asString();
        System.out.println(result);
    }

    @Test // UserCart
    public void userCartTest() {
        String result = productService.setUserCart("2").getResponseBody().asString();
        System.out.println(result);
    }

    @Test // addnewcart
    public void newCartTest() {
        JSONObject body = new JSONObject();
        body.put("userId", 11);
        body.put("date", "2024-12-01");

        HashMap<String, Object> product1 = new HashMap<>();
        product1.put("productId", 18);
        product1.put("quantity", 1);

        HashMap<String, Object> product2 = new HashMap<>();
        product2.put("productId", 2);
        product2.put("quantity", 4);

        String hasil = productService.createCart(body, product1, product2).getResponseBody().asString();
        System.out.println(hasil);
    }

}