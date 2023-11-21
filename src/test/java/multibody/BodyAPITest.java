package multibody;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BodyAPITest {

	@Test
	public void bodyWithText_Test() {
		
		RestAssured.baseURI = "https://httpbin.org";
		String payload = "hello world!";
		
		Response response = RestAssured.given()
		           .contentType(ContentType.TEXT)
		           .body(payload)
		           .when()
		           .post("/post");
		response.prettyPrint(); 
		System.out.println(response.jsonPath().getString("data"));
	}
	
	@Test
	public void bodyWithJavascript_Test() {
		
		RestAssured.baseURI = "https://httpbin.org";
		String payload = "function login(){\r\n" + 
				"    let x = 10;\r\n" + 
				"    let y = 20;\r\n" + 
				"    console.log(x+y);\r\n" + 
				"};";
		
		Response response = RestAssured.given()
		           .header("Content-Type", "application/javascript")
		           .body(payload)
		           .when()
		           .post("/post");
		response.prettyPrint(); 
		System.out.println(response.jsonPath().getString("data"));
	}
	
	@Test
	public void bodyWithHTML_Test() {
		
		RestAssured.baseURI = "https://httpbin.org";
		String payload = ""; //add an html payload
		
		Response response = RestAssured.given()
		           .contentType(ContentType.HTML)
		           .body(payload)
		           .when()
		           .post("/post");
		response.prettyPrint(); 
		System.out.println(response.jsonPath().getString("data"));
	}
	
	@Test
	public void bodyWithXML_Test() {
		
		RestAssured.baseURI = "https://httpbin.org";
		String payload = ""; //add an xml payload
		
		Response response = RestAssured.given()
		           .contentType(ContentType.XML)
		           .body(payload)
		           .when()
		           .post("/post");
		response.prettyPrint(); 
		System.out.println(response.jsonPath().getString("data"));
	}
	
	@Test
	public void bodyWithMultipart_Test() {
		
		RestAssured.baseURI = "https://httpbin.org";
		String payload = ""; //add an payload
		
		Response response = RestAssured.given()
		           .contentType(ContentType.MULTIPART)
		           .multiPart("name", "testing")
		           .multiPart("fileName", new File("")) //provide file : base64 encoding : b2a
		           .body(payload)
		           .when()
		           .post("/post");
		response.prettyPrint(); 
		System.out.println(response.jsonPath().getString("data"));
	}
	
	@Test
	public void bodyWithBinary_Test() {
		
		RestAssured.baseURI = "https://httpbin.org";
		String payload = ""; //add an xml payload
		
		Response response = RestAssured.given()
		           .header("Content-Type", "application/pdf") //or png etc
		           .body(new File("")) //pdf file path
		           .when()
		           .post("/post");
		response.prettyPrint(); 
		System.out.println(response.jsonPath().getString("data"));
	}
}
