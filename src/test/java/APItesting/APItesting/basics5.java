package APItesting.APItesting;
import org.testng.annotations.Test;
import files.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
@Test
public class basics5 {
	public void getPlaceAPI() {
		
		RestAssured.baseURI="https://maps.googleapis.com";
		
	Response res = given().
	    param("location","-33.8670522,151.1957362").
	    param("radius","500").
	    param("key","AIzaSyADAr8elXtLNF0x1GYd76PLNV_ghJK-jAo").log().all().
	    when().
	    get("/maps/api/place/nearbysearch/json").
	    then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	    body("results[0].name",equalTo("Sydney")).and().
	    body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
	    header("Server","scaffolding on HTTPServer2").and().log().body().
	    extract().response();
	
	JsonPath js = ReusableMethods.rawToJson(res);
	int count = js.get("results.size()");
	
	System.out.println(count);
	for(int i=0; i<count; i++) {
	Object k = js.get("results["+i+"].name");
		System.out.println(k);
		
	}
	    
	}
}




	


