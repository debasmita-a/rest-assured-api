package com.pet.api;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.Pet.Category;
import com.pet.api.Pet.Tag;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreatePetTest {
	
	@Test
	public void createPetTest() {
		RestAssured.baseURI = "https://petstore.swagger.io";
		Category category = new Category(11, "Chihuahua");	
		List<String> photoUrls = Arrays.asList("https://www.dog.com", "https://www.dog01.com");
		
		Tag tag1 = new Tag(1, "White");
		Tag tag2 = new Tag(2, "Black");
		List<Tag> tags = Arrays.asList(tag1, tag2);
		
		Pet pet = new Pet(101, category, "Jhunjhun", photoUrls, tags, "available");
		Response response = RestAssured.given()
		           .contentType(ContentType.JSON)
		           .body(pet)
		           .when().log().all()
		           .post("/v2/pet");
		
		response.prettyPrint();
		System.out.println(response.getStatusCode());
		
		//deserialization
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Pet petResponse = mapper.readValue(response.getBody().asString(), Pet.class);
			
			Assert.assertEquals(pet.getId(), petResponse.getId());
			Assert.assertEquals(pet.getName(), petResponse.getName());
			Assert.assertEquals(pet.getCategory().getId(), petResponse.getCategory().getId());
			Assert.assertEquals(pet.getCategory().getName(), petResponse.getCategory().getName());
			System.out.println(petResponse.getPhotoUrls());
			
			System.out.println(petResponse.getTags().get(0).getId());
			System.out.println(petResponse.getTags().get(0).getName());
			
			System.out.println(petResponse.getTags().get(1).getId());
			System.out.println(petResponse.getTags().get(1).getName());
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createPet_With_BuilderPattern_Test() {
		RestAssured.baseURI = "https://petstore.swagger.io";
		
		Category category = new Category(11, "Chihuahua");	
		List<String> photoUrls = Arrays.asList("https://www.dog.com", "https://www.dog01.com");
		
		Tag tag1 = new Tag(1, "White");
		Tag tag2 = new Tag(2, "Black");
		List<Tag> tags = Arrays.asList(tag1, tag2);
		
		//Pet pet = new Pet(101, category, "Jhunjhun", photoUrls, tags, "available");
		
		Pet pet = new Pet.PetBuilder().id(202)
				                      .category(category)
				                      .name("Jhunjhun")
				                      .photoUrls(photoUrls)
				                      .tags(tags)
				                      .status("available").build();
		
		Response response = RestAssured.given()
		           .contentType(ContentType.JSON)
		           .body(pet)
		           .when().log().all()
		           .post("/v2/pet");
		
		response.prettyPrint();
		System.out.println(response.getStatusCode());
		
		//deserialization
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Pet petResponse = mapper.readValue(response.getBody().asString(), Pet.class);
			
			Assert.assertEquals(pet.getId(), petResponse.getId());
			Assert.assertEquals(pet.getName(), petResponse.getName());
			Assert.assertEquals(pet.getCategory().getId(), petResponse.getCategory().getId());
			Assert.assertEquals(pet.getCategory().getName(), petResponse.getCategory().getName());
			System.out.println(petResponse.getPhotoUrls());
			
			System.out.println(petResponse.getTags().get(0).getId());
			System.out.println(petResponse.getTags().get(0).getName());
			
			System.out.println(petResponse.getTags().get(1).getId());
			System.out.println(petResponse.getTags().get(1).getName());
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
