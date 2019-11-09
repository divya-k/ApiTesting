package APItesting.APItesting;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import files.ReusableMethods;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class createPlaceAPIxml {

	@Test
	public void createPlaceAPI() throws IOException
	{
		String postdata = GenerateStringFromResource("/Users/shridharvenkatesh/Documents/JavaEclipseWorkspace/APItesting/postdata.xml");
		RestAssured.baseURI="http://216.10.245.166";
		Response respon = given().
		
		queryParam("key","qaclick123").
		body(postdata).
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).extract().response();
	// Create a place =response (place id)
		
		// delete Place = (Request - Place id)	
		System.out.println(respon.asString());
		//XmlPath x = new XmlPath(respon.asString());
		
		XmlPath x = ReusableMethods.rawToXML(respon);
		x.get("response.place_id"); 
		System.out.println(x.get("response.place_id"));

	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
