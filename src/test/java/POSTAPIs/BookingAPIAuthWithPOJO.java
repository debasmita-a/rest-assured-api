package POSTAPIs;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

public class BookingAPIAuthWithPOJO {

	@Test
	public void getBookingAuthToken_WithJSONString_Test(){
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		Credentials cred = new Credentials("admin", "password123");
		String tokenId = given().log().all()
		    .contentType(ContentType.JSON)
		       .body(cred)
		          .when().log().all()
		             .post("/auth")
		                 .then().log().all()	
		                      .assertThat()
		                            .statusCode(200)
		                                  .extract().path("token");
		
		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);
	}
	
}
//POJO- plain old java object
//can't inherit from any class or interface
//oop - encapsulation -- getters and setters
//Encapsulation-- private members of the class (methods, properties) will be accessed by public methods 
//POJO -- all private class variables -- public getters and setters methods to access and update them
//Serialization -- converting any java object to other formats - files, texts, json--any kind of media entity
//Deserialization -- json to POJO