package APItesting.APItesting;

import org.json.JSONObject;
import org.seleniumhq.jetty9.server.Authentication;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class shribabu2 {
	@Test
	public void getAccessToken() {
		
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
					
				/*
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
				
					
	}

}
