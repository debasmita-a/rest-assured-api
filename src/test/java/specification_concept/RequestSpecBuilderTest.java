package specification_concept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest {

	public static RequestSpecification user_req_spec() {
		
		//The RequestSpecBuilder class doesn't implement RequestSpecification interface.
		//The build() method in RequestSpecBuilder returns a RequestSpecification object ref
		
		RequestSpecification requestSpec = new RequestSpecBuilder() //not top casting
		   .setBaseUri("https://gorest.co.in")
		   .setContentType(ContentType.JSON)
		   .addHeader("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
		   .build();
		
		return requestSpec;
	}
	
	@Test
	public void getUser_With_Request_Spec() {
		
		RestAssured.given().log().all()
		              .spec(user_req_spec())
		                  .get("/public/v2/users")
		                      .then().log().all()
		                         .assertThat()
		                             .statusCode(200);
	}
	
	@Test
	public void getUser_With_QueryParams_Request_Spec() {
		
		RestAssured.given().log().all()
		              .spec(user_req_spec())
		                   .queryParam("name", "Surya")
		                   .queryParam("staus", "active")
		                         .get("/public/v2/users")
		                                .then().log().all()
		                                     .assertThat()
		                                         .statusCode(200);
	}
}

