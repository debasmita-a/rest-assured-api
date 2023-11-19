package PUTAPIs;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateUserTest {

	//userId --> create user : POST 
	//update user + body : PUT
	
	public static String getRandomEmailId() {
		return "put_api_test" + System.currentTimeMillis() + "@gmail.com";
	}
	
	@Test
	public void updateUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//1. Create User : POST
		
		User user = new User.UserBuilder()
				            .name("Debasmita")
				            .email(getRandomEmailId())
				            .gender("female")
				            .status("active")
				            .build();
		
		Response postResponse = RestAssured.given().log().all()
		           .header("Authorization","Bearer 34d6c28443cbc825f4244dd534bc37aa851fed28e853cb80a51923b195d9b29f") 
		           .body(user)
		           .when().log().all()
		           .post("/public/v2/users");
		           
		Integer userId = postResponse.getBody().jsonPath().get("id");
		
		System.out.println(userId);
		
		//2. Update User : PUT
		
		user.setName("DA");
		user.setStatus("inactive");
		
		RestAssured.given().log().all()
		           .header("Authorization","Bearer 34d6c28443cbc825f4244dd534bc37aa851fed28e853cb80a51923b195d9b29f") 
		           .body(user)
		           .when().log().all()
		           .post("/public/v2/users/" + userId)
		           .then()
		           .statusCode(200)
		           .and()
		           .assertThat()
		           .body("id", equalTo(user.getId()))
		           .body("name", equalTo(user.getName()));
	 		   
	}
}
