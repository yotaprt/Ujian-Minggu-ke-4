package com.bda.fakestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ProductService {
  String baseURL = "https://fakestoreapi.com";
  private HashMap<String, String> collections;
  private Response response;
  private RequestSpecification httpRequest;

  public ProductService() {
    RestAssured.baseURI = baseURL;
    httpRequest = RestAssured.given();
  }

  // public ProductService setProductBasedOnCategory(String category) {
  // response = httpRequest.request(Method.GET, "/products/category/" + category);
  // return this;
  // }

  // public ProductService setProductDetail(String numId) {
  // response = httpRequest.request(Method.GET, "/products/" + numId);
  // return this;
  // }

  // public ProductService setProductOrderAndLimit(String order, String limit) {
  // response = httpRequest.queryParam("sort", order).queryParam("limit",
  // limit).request(Method.GET, "/products/");
  // return this;
  // }

  // public ProductService createProduct(JSONObject product) {
  // httpRequest.header("Content-Type", "application/json");
  // httpRequest.body(product.toJSONString());
  // response = httpRequest.post("/products");
  // return this;
  // }

  public ProductService getAllCart() {
    response = httpRequest.request(Method.GET, "/carts");
    return this;
  }

  public ProductService setCartDetail(String numId) {
    response = httpRequest.request(Method.GET, "/carts/" + numId);
    return this;
  }

  public ProductService setCartLimit(String limit) {
    response = httpRequest.queryParam("limit", limit).request(Method.GET, "/carts/");
    return this;
  }

  public ProductService setCartSortAndLimit(String order, String limit) {
    response = httpRequest.queryParam("sort", order).queryParam("limit", limit).request(Method.GET, "/carts/");
    return this;
  }

  public ProductService setCartDateRange(String startDate, String endDate) {
    response = httpRequest.queryParam("startdate", startDate).queryParam("enddate", endDate).request(Method.GET,
        "/carts/");
    return this;
  }

  public ProductService setUserCart(String userId) {
    response = httpRequest.request(Method.GET, "/carts/user/" + userId);
    return this;
  }

  public ProductService createCart(JSONObject body, HashMap<String, Object> product1,
      HashMap<String, Object> product2) {
    List<HashMap<String, Object>> products = new ArrayList<>();
    products.add(product1);
    products.add(product2);
    body.put("products", products);
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(body.toJSONString());
    response = httpRequest.post("/carts");
    return this;
  }

  public Response getResponse() {
    return response;
  }

  public ResponseBody getResponseBody() {
    return response.getBody();
  }

  public JsonPath asJSON() {
    return response.jsonPath();
  }

  public HashMap<String, String> getHeaders() {
    collections = new HashMap<String, String>();

    for (Header h : response.headers()) {
      collections.put(h.getName(), h.getValue());
    }

    return collections;
  }

  public String getContentTypeHeader() {
    return collections.get("Content-Type");
  }

  public String getServerHeader() {
    return collections.get("Server");
  }

}