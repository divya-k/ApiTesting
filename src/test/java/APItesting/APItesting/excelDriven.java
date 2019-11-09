package APItesting.APItesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class excelDriven {

	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
	Response resp = given().
		body(payLoad.AddLibrary(isbn,aisle)).
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
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		
		return new Object[][] {{"aqw","678"},{"kdj","123"},{"dsd","909"}};
	}

}
