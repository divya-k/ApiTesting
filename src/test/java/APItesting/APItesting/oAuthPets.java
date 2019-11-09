package APItesting.APItesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.Matchers.equalTo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.testng.annotations.Test;

import files.ReusableMethods;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class oAuthPets {
	
	public String getAccessToken() {
		
/*
		String response =

	            given().
	            	urlEncodingEnabled(false).
	            	queryParams("response_type", "token").
	            	queryParams("client_id", "test").	            	
	            	queryParam("scope", "read:pets").
	               queryParams("state", "verifyfjdss").              
	               when().log().all()
	             	.get("https://petstore.swagger.io/oauth/authorize").asString();
					System.out.println("ammu is "+ response.toString());
					String k = response.toString();
					
	*/				/*
					String response =	 given().
		            	urlEncodingEnabled(false).
		            	queryParams("response_type", "token").
		            	queryParams("client_id", "test").	            	
		            	queryParam("scope", "read:pets").
		               queryParams("state", "verifyfjdss").              
		               when().log().all()
		             	.post("https://petstore.swagger.io/oauth/authorize").asString();
						System.out.println("ammu is "+ response.toString());
						String k = response.toString();
						
					*/
				
					System.setProperty("webdriver.chrome.driver", "/Users/shridharvenkatesh/Desktop/test answer/UITest/drivers/chromedriver");
					 WebDriver driver = new ChromeDriver();
					 
					 driver.get("https://petstore.swagger.io/oauth/authorize?response_type=token&client_id=test&scope=read:pets&state=verifyfjdss");
	driver.findElement(By.id("allow")).click();
	driver.findElement(By.id("login")).click();
	driver.findElement(By.name("authorize")).click();
	
	String url = driver.getCurrentUrl();
	System.out.println(url);
	
	String p = url.split("access_token=")[1];
	String code = p.split("&token_type")[0];
	System.out.println(code);
	driver.close();
		return code;
	}
	/*
	

	 
*/
	@Test
public void createPet() {
	String accesstoken = getAccessToken();
	String r3= given().contentType("application/json").
			queryParams("access_token", accesstoken).body("{"+
					  "\"id\": 1002677,"+
					  "\"category\": {"+
					    "\"id\": 1,"+
					   "\"name\": \"Fish\""+
					  "},"+
					  "\"name\": \"minion Fish\","+
					  "\"photoUrls\": ["+
					    "\"goldfish\""+
					  "],"+
					  "\"tags\": ["+
					    "{"+
					    "\"id\": 1,"+
					      "\"name\": \"Fish tag\""+
					    "}"+
					  "],"+
					  "\"status\": \"available\""+
					"}").
			expect().defaultParser(Parser.JSON).when()
	       .post("https://petstore.swagger.io/v2/pet").
	       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response().asString();

	System.out.println(r3);
	
	JsonPath js= new JsonPath(r3);
	int id=js.get("id");
//return id;
}


}
