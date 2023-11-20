package DELETEAPI;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import DELETEAPI.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteUserTest {

	// 1. Create user : POST 201
	// 2. Delete user : DELETE 204
	// 3. Get the user : GET 404

	public static String getRandomEmailId() {
		return "put_api_test" + System.currentTimeMillis() + "@gmail.com";
	}

	@Test
	public void deleteUserAPITest() {
		RestAssured.baseURI = "https://gorest.co.in";

		// 1. Create User : POST
		
		User user = new User.UserBuilder()
				            .name("Debasmita")
				            .email(getRandomEmailId())
				            .gender("female")
				            .status("active")
				            .build();

		Response postResponse = RestAssured.given().log().all()
				.header("Authorization", "Bearer 34d6c28443cbc825f4244dd534bc37aa851fed28e853cb80a51923b195d9b29f")
				.body(user).when().log().all().post("/public/v2/users");
		
		postResponse.prettyPrint();

		Integer userId = postResponse.jsonPath().get("id");

		System.out.println(userId);
		
		// 2. Delete User : DELETE
		
		RestAssured.given().log().all()
		.header("Authorization", "Bearer 34d6c28443cbc825f4244dd534bc37aa851fed28e853cb80a51923b195d9b29f")
		.when()
		.delete("/public/v2/users/" + userId)
		.then()
		.assertThat()
		.statusCode(204);
		
		// 3. Get User : GET
		
		RestAssured.given().log().all()
		.header("Authorization", "Bearer 34d6c28443cbc825f4244dd534bc37aa851fed28e853cb80a51923b195d9b29f")
		.when()
		.get("/public/v2/users/" + userId)
		.then()
		.assertThat()
		.statusCode(404)
		.and()
		.body("message", equalTo("Resource Not Found"));
	}
}
