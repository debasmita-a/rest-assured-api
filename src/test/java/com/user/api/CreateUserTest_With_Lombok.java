package com.user.api;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest_With_Lombok {

	public static String getRandomEmailId() {
		return "usertest" + System.currentTimeMillis() + "@gmail.com";
	}
	
	/*
	 * @Test public void createUserTest() { RestAssured.baseURI =
	 * "https://gorest.co.in";
	 * 
	 * User user = new User("Debasmita", getRandomEmailId(), "female", "inactive");
	 * //1. POST : User create Response postResponse =
	 * RestAssured.given().log().all() .contentType(ContentType.JSON) .header(
	 * "Authorization","Bearer 34d6c28443cbc825f4244dd534bc37aa851fed28e853cb80a51923b195d9b29f"
	 * ) .body(user) //serialization -- with help of Jackson API -- POJO -->JSON
	 * .when().log().all() .post("/public/v2/users");
	 * 
	 * Integer userId = postResponse.jsonPath().get("id");
	 * 
	 * //2. GET : verify the Get API with userId Response getResponse =
	 * RestAssured.given().log().all() .contentType(ContentType.JSON) .header(
	 * "Authorization","Bearer 34d6c28443cbc825f4244dd534bc37aa851fed28e853cb80a51923b195d9b29f"
	 * ) .when().log().all() .get("/public/v2/users/" + userId);
	 * 
	 * //deserialization/unmarshelling -- JSON --> POJO ObjectMapper mapper = new
	 * ObjectMapper();
	 * 
	 * try { User userResponse = mapper.readValue(getResponse.getBody().asString(),
	 * User.class); System.out.println("User id : " + userResponse.getId() +
	 * " User email : " + userResponse.getEmail() + " User name : " +
	 * userResponse.getName()); Assert.assertEquals(userId, userResponse.getId());
	 * Assert.assertEquals(user.getName(), userResponse.getName());
	 * Assert.assertEquals(user.getEmail(), userResponse.getEmail());
	 * 
	 * } catch (JsonParseException e) { e.printStackTrace(); } catch
	 * (JsonMappingException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */
	@Test
	public void createUser_with_BuilderPattern_Test() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User.UserBuilder() 
		          .name("Debasmita")
		          .email(getRandomEmailId())
		          .status("active")
		          .gender("female")
		          .build();
		
		//1. POST : User create
		Response postResponse = RestAssured.given().log().all()
		           .contentType(ContentType.JSON)
		           .header("Authorization","Bearer 34d6c28443cbc825f4244dd534bc37aa851fed28e853cb80a51923b195d9b29f")
		           .body(user) //serialization -- with help of Jackson API -- POJO -->JSON
		           .when().log().all()
		           .post("/public/v2/users");
		
		Integer userId = postResponse.jsonPath().get("id");
		
		//2. GET : verify the Get API with userId
		Response getResponse = RestAssured.given().log().all()
		           .contentType(ContentType.JSON)
		           .header("Authorization","Bearer 34d6c28443cbc825f4244dd534bc37aa851fed28e853cb80a51923b195d9b29f")
		           .when().log().all()
		           .get("/public/v2/users/" + userId);
		
		//deserialization/unmarshelling -- JSON --> POJO
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			User userResponse = mapper.readValue(getResponse.getBody().asString(), User.class);
			System.out.println("User id : " + userResponse.getId() + " User email : " + userResponse.getEmail() + " User name : " + userResponse.getName());
			Assert.assertEquals(userId, userResponse.getId());
			Assert.assertEquals(user.getName(), userResponse.getName());
			Assert.assertEquals(user.getEmail(), userResponse.getEmail());
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
