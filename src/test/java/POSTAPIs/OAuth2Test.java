package POSTAPIs;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class OAuth2Test {
	
	public static String access_token;
	
	@BeforeTest //TestNG methods should not return anything.
	public void getAccessToken() {
		
		//1. POST -- OAuth token
		RestAssured.baseURI = "https://test.api.amadeus.com";
		
		access_token = given()
		  // .header("Content-Type", "application/x-www-form-urlencoded")
				.contentType(ContentType.URLENC)
		       .formParam("grant_type", "client_credentials")
		       .formParam("client_id", "gn93qGmTK44TEX0SbGSoEmOGxA2bAQTo")
		       .formParam("client_secret", "mRnqKJP6ociGelNg")
		           .when()
		                .post("/v1/security/oauth2/token")
		                    .then()
		                        .assertThat()
		                            .statusCode(200)
		                                 .extract().path("access_token");
		System.out.println(access_token);
	}

	@Test
	public void getFlightPriceListTest() {
		
		//2. GET -- flight info
		List<Float> priceList = given()
	        .header("Authorization", "Bearer " + access_token)
		        .queryParam("origin", "PAR")
		        .queryParam("maxPrice", 200)
		            .when()
		               .get("/v1/shopping/flight-destinations")
		                   .then()
		                       .assertThat()
		                            .statusCode(200)
		                                // .body("data[0].type", equalTo("flight-destination"));
		                                 .extract()
		                                     .path("data.price.total");
		
		System.out.println(priceList);  
	}
	
	@Test
	public void getFlightInfoListTest() {
		
		//2. GET -- flight info
		List<String> originList = given()
	        .header("Authorization", "Bearer " + access_token)
		        .queryParam("origin", "PAR")
		        .queryParam("maxPrice", 200)
		            .when()
		               .get("/v1/shopping/flight-destinations")
		                   .then()
		                       .assertThat()
		                            .statusCode(200)
		                                // .body("data[0].type", equalTo("flight-destination"));
		                                 .extract()
		                                     .path("data.origin");
		
		System.out.println(originList);  
	}
}
