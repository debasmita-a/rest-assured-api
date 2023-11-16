package jsonPathValidator;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JSONPathTest {

	@Test
	public void getCircuitDataAPI_with_Year_Test() {
		RestAssured.baseURI = "http://ergast.com";
		
		Response response = given()
		   .pathParams("year", "2019")
		       .when()
		           .get("/api/f1/{year}/circuits.json");
		              
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		
		int totalCicuits = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");
		System.out.println(totalCicuits);
		
		List<String> countryList = JsonPath.read(jsonResponse, "$..Circuits..country");
		System.out.println(countryList.size());
		System.out.println(countryList);
	}
	
	@Test
	public void getProductTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given()
		   .when()  
		        .get("/products");
		
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		
		List<String> titleList = JsonPath.read(jsonResponse, "$..title");
		System.out.println(titleList.size());
		System.out.println(titleList);
		
		//print all titles for which price is > 150
		//with 2 attributes
		List<Map<String, Object>> productListWithPrice = JsonPath.read(jsonResponse, "$..[?(@.price>150)].['title','price']");
		System.out.println(productListWithPrice);
		
		for(Map<String, Object> e : productListWithPrice) {
			String title = (String) e.get("title");
			Object price = (Object) e.get("price");
			
			System.out.println("title : " + title);
			System.out.println("price : " + price);
			System.out.println("--------------------------");
		}
		
		//with 3 attributes $..[?(@.price>150)].[id,title,price]
		List<Map<String, Object>> productListWith3attr = JsonPath.read(jsonResponse, "$..[?(@.price>150)].['id','title','price']");
		System.out.println(productListWith3attr);
		
		for(Map<String, Object> e : productListWith3attr) {
			Integer id = (Integer) e.get("id");
			String title = (String) e.get("title");
			Object price = (Object) e.get("price");
			
			System.out.println("id : " + id);
			System.out.println("title : " + title);
			System.out.println("price : " + price);
			System.out.println("--------------------------");
		}
	}
}
