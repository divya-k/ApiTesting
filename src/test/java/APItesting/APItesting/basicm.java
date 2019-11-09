package APItesting.APItesting;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class basicm{
	
	public String getAccessToken() {

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
	
	@Test
	public void createPet() {
		String accesstoken = getAccessToken();
		String r3= given().contentType("application/json").
				queryParams("access_token", accesstoken).
				body("{"+
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
			
				when()
		       .post("https://petstore.swagger.io/v2/pet").
		       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response().asString();

		System.out.println(r3);
		
		JsonPath js= new JsonPath(r3);
		int id=js.get("id");
		System.out.println(id);
	//return id;
		
		
		String k = "https://petstore.swagger.io/v2/pet/"+id;
		System.out.println(k);
		String r2= given().contentType("application/json").
				queryParams("access_token", accesstoken).
			
				when()
				
		       .get(k).
		       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response().asString();

		System.out.println(r2);
		
		
		
		
	}




}




	


