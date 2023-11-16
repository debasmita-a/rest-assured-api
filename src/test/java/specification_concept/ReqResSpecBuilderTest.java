package specification_concept;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqResSpecBuilderTest {

	public static RequestSpecification user_req_spec() {
		
		RequestSpecification requestSpec = new RequestSpecBuilder() //not top casting
		   .setBaseUri("https://gorest.co.in")
		   .setContentType(ContentType.JSON)
		   .addHeader("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
		   .build();
		
		return requestSpec;
	}
	
	public static ResponseSpecification get_res_spec_200_OK() {
		ResponseSpecification res_spec_200_OK = new ResponseSpecBuilder()
		       .expectContentType(ContentType.JSON)
		            .expectStatusCode(200)
		                 .expectHeader("Server", "cloudflare")
		                     .build();
		
		return res_spec_200_OK;
	}
	
	@Test
	public void getUserData_With_Spec() {
		given()
		   .spec(user_req_spec())
		       .when()
		           .get("/public/v2/users")
		                .then()
		                     .spec(get_res_spec_200_OK());
	}
}
