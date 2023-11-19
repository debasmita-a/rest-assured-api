package com.product.api;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.fakestore.api.Product;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductAPITest {


	@Test
	public void getProductTest_BDD_Style_1() {
		
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
		Response response = given()
		   .when()  
		       .get("/products");
		//json to pojo mapping -- deserialization
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Product product[] = mapper.readValue(response.body().asString(), Product[].class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
