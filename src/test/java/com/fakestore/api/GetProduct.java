package com.fakestore.api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

public class GetProduct {

	@Test
	public void getProductTest_BDD_Style_1() {
		
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
		given()
		   .when()  
		       .get("/products")
		          .then()
		              .assertThat()
		                   .statusCode(200)
		                        .and()
		                             .body("$.size", equalTo(20))
		                                 .and()   
		                                     .body("$.id", is(notNullValue()))
		                                         .and()
		                                             .body("title", hasItem("Mens Cotton Jacket"));
		
	}
}
