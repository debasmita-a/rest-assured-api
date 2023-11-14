package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIRequestWithoutBDDTest {

	RequestSpecification request;

	//NON-BDD Approach:
	
	@BeforeTest
	public void setUpRequest() {
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "Bearer 5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f");
	}
	
	@Test
	public void getAllUsersAPITest() {
		 //key-value pair
		Response response = request.get("/public/v2/users"); //get will return the Response obj
		//fetch the status code :
		int statusCode = response.statusCode();
		System.out.println("Status code is: "+statusCode);
		
		//verification point :
		Assert.assertEquals(statusCode, 200);
		
		//fetch status msg :
		String statusMsg = response.statusLine();
		System.out.println(statusMsg);
		Assert.assertTrue(statusMsg.contains("200 OK"));
		
		//fetch the body
		response.prettyPrint();
		
		//fetch response headers
		String contentType = response.getHeader("Content-Type");
		System.out.println(contentType);
		
		//fetch all headers
		List<Header> headers = response.headers().asList();
		for(Header h : headers) {
			System.out.println("Header name: "+ h.getName() + " ------ Header value: "+ h.getValue());
		}
	}
	
	@Test
	public void getAllUsersAPIWithQueryParamsTest() {
		request.queryParam("name", "Surya");
		request.queryParam("status", "active");
		
		//Response
		Response response = request.get("/public/v2/users"); //get will return the Response obj
		//=======================
		//fetch the status code :
		int statusCode = response.statusCode();
		System.out.println("Status code is: "+statusCode);
		
		//verification point :
		Assert.assertEquals(statusCode, 200);
		
		//fetch status msg :
		String statusMsg = response.statusLine();
		System.out.println(statusMsg);
		Assert.assertTrue(statusMsg.contains("200 OK"));
		
		//fetch the body
		response.prettyPrint();
				
	}
	
	@Test
	public void getAllUsersAPIWithQueryParam_WithHashMapTest() {
		Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap.put("name", "Surya");
		queryParamMap.put("status", "active");
		request.queryParams(queryParamMap);
		
		//Response
		Response response = request.get("/public/v2/users"); //get will return the Response obj
		//=======================
		//fetch the status code :
		int statusCode = response.statusCode();
		System.out.println("Status code is: "+statusCode);
		
		//verification point :
		Assert.assertEquals(statusCode, 200);
		
		//fetch status msg :
		String statusMsg = response.statusLine();
		System.out.println(statusMsg);
		Assert.assertTrue(statusMsg.contains("200 OK"));
		
		//fetch the body
		response.prettyPrint();
				
	}
}
