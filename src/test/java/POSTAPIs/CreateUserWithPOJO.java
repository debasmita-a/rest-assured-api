package POSTAPIs;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;

public class CreateUserWithPOJO {

	public static String getRandomEmailID() {
		return "apiAutomation"+System.currentTimeMillis()+"@gamil.com";
	}
	@Test
	public void addUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User("DebasmitaA", getRandomEmailID(), "female", "active");
		
		//1. Post -- create a user
		
		int userID = given().log().all()
		   .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
		       .contentType(ContentType.JSON)
		           .body(user)
		                .when().log().all()
		                    .post("/public/v2/users")
		                         .then().log().all()
		                              .assertThat()
		                                   .statusCode(201)
		                                        .body("name", equalTo(user.getName()))
		                                        .extract().path("id");
		
		//2. GET -- validate same user ID
		
		given().log().all()
		   .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
	           .contentType(ContentType.JSON)
	               .when().log().all()
	                   .get("/public/v2/users/" + userID)
	                    .then().log().all()
	                        .assertThat()  
	                            .statusCode(200)
	                                 .and()
	                                     .body("name", equalTo(user.getName()))
	                                         .and()
	                                            .body("status", equalTo(user.getStatus()));
	                                
	}
	
}
