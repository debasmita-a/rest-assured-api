package POSTAPIs;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingAuthAPI {

	@Test
	public void getBookingAuthToken_WithJSONString_Test(){
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
		    .contentType(ContentType.JSON)
		       .body("{\r\n" + 
		       		"    \"username\" : \"admin\",\r\n" + 
		       		"    \"password\" : \"password123\"\r\n" + 
		       		"}")
		          .when()
		             .post("/auth")
		                 .then()	
		                      .assertThat()
		                            .statusCode(200)
		                                  .extract().path("token");
		
		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);
	}
	
	@Test
	public void getBookingAuthToken_WithJSONFile_Test(){
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
		    .contentType(ContentType.JSON)
		       .body(new File("./src/test/resources/testdata/basicauth.json"))
		          .when()
		             .post("/auth")
		                 .then()	
		                      .assertThat()
		                            .statusCode(200)
		                                  .extract().path("token");
		
		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);                                                                                                                                                                                                              
	}
	
	//Post call - add a user -- 201 Created, User ID -- Assert(201, body)
	//Get call - user Id -- 200 OK, 
	
	@Test
	public void addUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//add user - POST
		int userId = given().log().all()
		    .contentType(ContentType.JSON)
		    .body(new File("./src/test/resources/testdata/adduser.json"))
		    .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
		.when()
		    .post("/public/v2/users")
		.then().log().all()
		    .assertThat()
		        .statusCode(201)
		        .and()
		        .body("name", equalTo("Debasmita"))
		        .extract()
		            .path("id");
		          
		System.out.println(userId);
		
		//get the same user and verify- GET
		given().log().all()
				  .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
				     .when().log().all()
				       .get("/public/v2/users/"+userId)
				          .then().log().all()
				             .assertThat()
				             .statusCode(200)
                                   .body("id", equalTo(userId));
		
	}
}
