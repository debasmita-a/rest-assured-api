package com.rest.api.get;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GETBDDAPI {

	//Rest Assured BDD
	/*
	 * given()
	 * when()
	 * then()
	 * and()
	 */
	
	@Test
	public void getAPITest_1() {
		
		given().log().all()
		.when().log().all()
		.get("http://ergast.com/api/f1/2017/circuits.json")
		.then().log().all()
		   .assertThat()
		    .body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
	}
	
	@Test
	public void getAPITest_2() {
		Response response = 
				given().log().all()
				.when().log().all()
				.get("http://ergast.com/api/f1/2017/circuits.json");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void getAPICircuitTest_contentLength() {
		RestAssured.baseURI = "http://ergast.com";
		
		given().log().all()
		.when().log().all()
		  .get("api/f1/2017/circuits")
		.then().log().all()
		  .assertThat()
		     .statusCode(200)
		.and()
		   .contentType(ContentType.XML)
		.and()
		   .header("Content-Length", "5921");
		  
	}
	
	@Test
	public void 
}
