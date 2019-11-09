package APItesting.APItesting;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
@Test
public class oAuthTest {

	public void oAuthTestm() throws InterruptedException {
		  System.setProperty("webdriver.chrome.driver", "/Users/shridharvenkatesh/Desktop/test answer/UITest/drivers/chromedriver");
		  WebDriver driver = new ChromeDriver();
	//	  driver.get("https://accounts.google.com/signin/oauth/oauthchooseaccount?client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&as=Mcf2dFDMCaX7liSf_477Mg&destination=https%3A%2F%2Frahulshettyacademy.com&approval_state=!ChQ4S1JDQTRhS1RJblYtSklKS1NNSBIfTTl4Umd1ZjdkWWdXOEhuU1JuY2dubXB1R3pLMjRoWQ∙AJDr988AAAAAXb6Z-555uiK4XlLwYqsHu6sF9modmbb4&oauthgdpr=1&xsrfsig=ChkAeAh8T_6pBYGxXCNhoxa78blBLb_L5rKQEg5hcHByb3ZhbF9zdGF0ZRILZGVzdGluYXRpb24SBXNvYWN1Eg9vYXV0aHJpc2t5c2NvcGU&flowName=GeneralOAuthFlow");
	driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
	driver.findElement(By.cssSelector("input[type='email']")).sendKeys("prams5491@gmail.com",Keys.ENTER);
	Thread.sleep(3000);
	driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Boston65$",Keys.ENTER);
	Thread.sleep(3000);
	String url = driver.getCurrentUrl();
	System.out.println(url);
	String p = url.split("code=")[1];
	String code = p.split("&scope=")[0];
	System.out.println(code);
	
	
	
	String response =

            given().
            urlEncodingEnabled(false).
              queryParams("code",code).
              queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
              queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
                   queryParams("grant_type", "authorization_code")

                    .queryParams("state", "verifyfjdss")

                    .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

                 // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")

                   

                    .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")

                    .when().log().all()

                    .post("https://www.googleapis.com/oauth2/v4/token").asString();

//System.out.println(response);

JsonPath jsonPath = new JsonPath(response);

String accessToken = jsonPath.getString("access_token");

System.out.println(accessToken);

String r2=    given().contentType("application/json").queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)

.when()

       .get("https://rahulshettyacademy.com/getCourse.php")

.asString();

System.out.println(r2);


	}
}



/*
https://accounts.google.com/o/oauth2/v2/auth?
	scope=https://www.googleapis.com/auth/userinfo.email&
		auth_url=https://accounts.google.com/o/oauth2/v2/auth&
			client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&
			response_type=code&
			redirect_uri=https://rahulshettyacademy.com/getCourse.php");
			
			
			
			https://rahulshettyacademy.com/getCourse.php?
			code=4%2FswHgw0vMGYusSc-kHI0ceJG0Mk4jh03iu_6Yyu230bABnBGOdLjTLfRLvmTA9cG3nzsLYLHKVPZvGiXGIoSKejU&
			scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&
			session_state=9a102ccf844897f231574c80430a858ed1395d6a..24cf&prompt=none#
P
			
*/

