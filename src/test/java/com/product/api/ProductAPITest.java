package com.product.api;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.fakestore.api.Product;
import com.fakestore.api.ProductLombok;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductAPITest {


	@Test
	public void getProductTest_With_POJO() {
		
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
		Response response = given()
		   .when()  
		       .get("/products");
		//json to pojo mapping -- deserialization
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Product product[] = mapper.readValue(response.getBody().asString(), Product[].class);
			
			for(Product p : product) {
				System.out.println("Product ID : " + p.getId() + " Title is : " + p.getTitle() + " Price is : "+ p.getPrice() + 
						" Rating-rate is : " + p.getRating().getRate() + " Rating-count is : " + p.getRating().getCount());
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getProductTest_With_LombokAPI() {
		
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
		Response response = given()
		   .when()  
		       .get("/products");
		//json to pojo mapping -- deserialization/unmarshelling
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			ProductLombok product[] = mapper.readValue(response.getBody().asString(), ProductLombok[].class);
			
			for(ProductLombok p : product) {
				System.out.println("Product ID : " + p.getId() + " Title is : " + p.getTitle() + " Price is : "+ p.getPrice() + 
						" Rating-rate is : " + p.getRating().getRate() + " Rating-count is : " + p.getRating().getCount());
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getProductTest_With_Lombok_BuilderPattern() {
		
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
		given()
		   .when()
		      .get("/products");
		           
	}
}
