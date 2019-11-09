package APItesting.APItesting;

import org.json.JSONObject;
import org.seleniumhq.jetty9.server.Authentication;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class shribabu {
	@Test
	public void getAccessToken() {
		
		
		Response response =
		         given()
		.header("Authorization","test:abc123")
		.contentType("application/x-www-form-urlencoded")
		.formParam("grant_type","implicit")
		.formParam("redirect_uri","https://petstore.swagger.io/oauth/authorize")
		.formParam("response_type","token")
		.formParam("client_id", "test")
		        .when()
		 .post("http://petstore.swagger.io/oauth/login.jsp");

		 JSONObject jsonObject = new JSONObject(response.getBody());
		 
		//String accessToken = jsonObject.get("access_token").toString();
		//String tokenType = jsonObject.get("state").toString();
		 System.out.print(jsonObject.toString());
				
					
	}

}
