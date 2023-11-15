package specification_concept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class ResponseSpecBuilderTest {

	public static ResponseSpecification get_res_spec_200_OK() {
		ResponseSpecification res_spec_200_OK = new ResponseSpecBuilder()
		       .expectContentType(ContentType.JSON)
		            .expectStatusCode(200)
		                 .expectHeader("Server", "cloudflare")
		                     .build();
		
		return res_spec_200_OK;
	}
	
	@Test
	public void get_user_res_200_spec_Test() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		    .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
		     .when().log().all()
		        .get("/public/v2/users")
		            .then()
		               .assertThat()
		                     .spec(get_res_spec_200_OK());
	}
	
}
