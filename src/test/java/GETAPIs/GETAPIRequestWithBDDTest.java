package GETAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GETAPIRequestWithBDDTest {

	@Test
	public void getProductsAPITest() {
		//Builder-pattern
		given().log().all()
		   .when().log().all()
		     .get("https://fakestoreapi.com/products")
		        .then().log().all()
		            .assertThat()
		                .statusCode(200)
		                  .and()
		                    .contentType(ContentType.JSON)
		                       .and()
		                          .header("Connection", "keep-alive")
		                             .and()
		                                .body("$.size()", equalTo(20)) //entire Response body is represented by $
		                                   .and()
		                                      .body("id", is(notNullValue())) //each and every id
		                                         .and()
		                                            .body("title", hasItem("Opna Women's Short Sleeve Moisture")); //each and every tilte
	}
	
	@Test
	public void getUsersAPITest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		  .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
		     .when().log().all()
		       .get("/public/v2/users")
		          .then().log().all()
		             .assertThat()
		             .statusCode(200)
		             .and()
		             .contentType(ContentType.JSON)
		             .and()
		             .body("$.size()", equalTo(10));
		             
	}
	
	@Test
	public void getProductAPIWithQueryParamsTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
		  .queryParam("limit", 2)
		     .when().log().all()
		        .get("/products")
		            .then().log().all()
		               .assertThat()
		                  .statusCode(200)
		                  .and()
		                  .contentType(ContentType.JSON);
	}
	
	@Test
	public void getUserWithQueryParamsTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		  .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
		     .queryParam("name", "surya")
		        .and()
		           .queryParam("status", "active")
		              .when().log().all()
		                 .get("/public/v2/users")
		                    .then().log().all()
		                       .assertThat()
		                          .contentType(ContentType.JSON);
	}
	
	@Test
	public void getProductDataAPIWith_Extract_BodyTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given().log().all()
		                           .queryParam("limit", 2)
		                                 .when().log().all()  
		                                        .get("/products");
		JsonPath  js = response.jsonPath();
		
		//get id of the first product
		
		int firstProductId = js.getInt("[0].id");
		System.out.println("First product id is : "+ firstProductId);
		
		String firstProductTitle = js.getString("[0].title");
		System.out.println("First product title is : "+ firstProductTitle);
		
		float firstProductPrice = js.getFloat("[0].price");
		System.out.println("First product price is : "+ firstProductPrice);
		
		int firstProductCount = js.getInt("[0].rating.count");
		System.out.println("First product count is : "+ firstProductCount);
	}
	
	@Test
	public void getProductDataAPIWith_Extract_Body_WithArrayTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given().log().all()
		                           .queryParam("limit", 10)
		                                 .when().log().all()  
		                                        .get("/products");
		JsonPath  js = response.jsonPath();
		//collect all id
		
		List<Integer> productIds = js.getList("id", Integer.class);
		List<String> productTitles = js.getList("title");	
		List<Float> productRates = js.getList("rating.rate", Float.class);
		//List<Object> productRates = js.getList("rating.rate");
		List<Integer> productCount = js.getList("rating.count");
		
		for(int i =0; i<productIds.size(); i++) {
			int id = productIds.get(i);
			String title = productTitles.get(i);
			float rate = productRates.get(i);
			//Object rate = productRates.get(i);
			int count = productCount.get(i);		
			System.out.println("Product id: "+ id + " Product title: "+ title + " Product rate: "+ rate + " Product count: "+ count);
		}
	}
	
	@Test
	public void getUserDataAPIWith_Extract_Body_WithArrayTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given().log().all()
				                   .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
		                                 .when().log().all()  
		                                        .get("/public/v2/users");
		JsonPath  js = response.jsonPath();
		//collect all id
		
		List<Integer> userIds = js.getList("id", Integer.class);
		List<String> names = js.getList("name");	
		List<String> emails = js.getList("email");
		List<String> gender = js.getList("gender");
		List<String> status = js.getList("status");
		
		for(int i =0; i<userIds.size(); i++) {
			int id = userIds.get(i);
			String name = names.get(i);
			String email = emails.get(i);
					
			System.out.println("Id: "+ id + " Name: "+ name + " Email: "+ email );
		}
	}
	
	@Test
	public void getUserDataAPIWith_Extract_Body_WithJSONTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given().log().all()
				                   .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
		                                 .when().log().all()  
		                                        .get("/public/v2/users/5711529");
		JsonPath  js = response.jsonPath();
		System.out.println("Id: "+ js.getInt("id") + " Name: "+ js.getString("name") + " Email: "+ js.getString("email"));
		}
	
	@Test
	public void getUserDataAPIWith_Extract_Body_WithJSON_ExtractTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		int id = given().log().all()
				                   .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
		                                 .when().log().all()  
		                                        .get("/public/v2/users/5711529")
		                                              .then()
		                                                  .extract().path("id"); //can be used to fetch single values from response e.g. tokens, to delete an user
		System.out.println("Id is : "+ id);
	
	}
	
}
