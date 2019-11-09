package files;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	public static XmlPath rawToXML(Response r) {
		XmlPath x = new XmlPath(r.asString());
		return x;
	}

	public static  JsonPath rawToJson(Response r) {
		JsonPath js= new JsonPath(r.asString());
		return js;
	}

}
