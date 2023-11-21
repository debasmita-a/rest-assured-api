package schemavalidation;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidationTest {
    
    @Test
    public void createUserAPISchemaValidationTest(){
        
        RestAssured.baseURI = "https://gorest.co.in";

        User user = new User.UserBuilder()
                            .name("Debasmita")
                            .email("schema_validation_test" + System.currentTimeMillis() + "@gmail.com")
                            .gender("female")
                            .status("inactive")
                            .build();

        RestAssured.given().log().all()
                    .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
                    .contentType(ContentType.JSON)
                         .body(user)
                            .when()    
                                .post("/public/v2/users")
                                    .then().log().all()
                                        .assertThat()
                                             .statusCode(201)
                                                   .and()
                                                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createUserSchema.json"));
                                     
                            
                                               
    }
    
    @Test
    public void getUserAPISchemaValidationTest(){
        
        RestAssured.baseURI = "https://gorest.co.in";

        RestAssured.given().log().all()
                    .header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f")
                    .contentType(ContentType.JSON)
                            .when()    
                                .get("/public/v2/users")
                                    .then().log().all()
                                        .assertThat()
                                             .statusCode(200)
                                                   .and()
                                                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getUserSchema.json"));
                                     
                            
                                               
    }

}
