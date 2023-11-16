package POSTAPIs;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserTest {

	@Test
	public void addUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		//1. Post -- create a user
		int userID = given()
		   .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
		       .contentType(ContentType.JSON)
		           .body(new File("./src/test/resources/testdata/adduser.json"))
		                .when()  
		                    .post("/public/v2/users")
		                         .then()
		                              .assertThat()
		                                   .statusCode(201)
		                                        .extract().path("id");
		
		//2. GET -- validate same user ID
		
		given()
		   .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
	           .contentType(ContentType.JSON)
	               .get("/public/v2/users/" + userID)
	                    .then()
	                        .assertThat()  
	                            .statusCode(200);
	                                
	}
}
