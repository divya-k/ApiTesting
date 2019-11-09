

package APItesting.APItesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class StaticJson {

	@Test()
	public void addBook() throws IOException
	{
		RestAssured.baseURI="http://216.10.245.166";
	Response resp = given().
		body(GenerateStringFromResource("/Users/shridharvenkatesh/Documents/JavaEclipseWorkspace/APItesting/Addbookdetails.json")).
		when().
		post("/Library/Addbook.php").
		then().
		assertThat().
		statusCode(200).and().contentType(ContentType.JSON)
	.and().extract().response();
	
	JsonPath js = ReusableMethods.rawToJson(resp);
	String id = js.get("ID");
	System.out.println(id);
	}		
	
	
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
}
