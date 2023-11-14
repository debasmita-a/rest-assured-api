package GETAPIs;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GETAPIRequestWithBDDTest {

	@Test
	public void getProductsTest() {
		//Builder-pattern
		given().log().all()
		   .when().log().all()
		     .get("https://fakestoreapi.com/products")
		        .then().log().all()
		            .assertThat()
		                .statusCode(200)
		                .contentType(ContentType.JSON)
		                .header("Connection", "keep-alive"); 
	}
}
