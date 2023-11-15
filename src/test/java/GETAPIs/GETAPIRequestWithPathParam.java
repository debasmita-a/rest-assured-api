package GETAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GETAPIRequestWithPathParam {
	
	//Query parameters : Always available in key-value pairs and are defined in documentation : ?
	//Path paramaters : <any key> <value> - can be created in postman,: /

	@Test
	public void getCircuitDataAPIWith_YearTest() {
		RestAssured.baseURI = "http://ergast.com/";
		
		given().log().all()
		       .pathParam("year", "2019")
		             .when().log().all()
		                    .get("/api/f1/{year}/circuits.json")
		                           .then().log().all()
		                                .assertThat()
		                                     .statusCode(200)
		                                          .and()
		                                               .body("MRData.total", equalTo("21"))
		                                                   .and()
		                                                       .body("MRData.CircuitTable.season", equalTo("2019"))
		                                                           .and()  
		                                                                .body("MRData.CircuitTable.Circuits.circuitId", hasSize(21));
		                          
	}

	@DataProvider
	public Object[][] getCircuitYearData() {
		return new Object[][] {
			{"2016", 21},
			{"2017", 20},
			{"1966", 9},
			{"2023", 22}
		};
	}
	
	@Test(dataProvider = "getCircuitYearData")
	public void getCircuitDataAPIWith_Year_DataProviderTest(String seasonYear, int totalCicuits) {
		RestAssured.baseURI = "http://ergast.com/";
		
		given().log().all()
		       .pathParam("year", seasonYear)
		             .when().log().all()
		                    .get("/api/f1/{year}/circuits.json")
		                           .then().log().all()
		                                .assertThat()
		                                     .statusCode(200)		                                      
		                                                   .and()
		                                                       .body("MRData.CircuitTable.season", equalTo(seasonYear))
		                                                           .and()  
		                                                                .body("MRData.CircuitTable.Circuits.circuitId", hasSize(totalCicuits));
		                          
	}
	
}
