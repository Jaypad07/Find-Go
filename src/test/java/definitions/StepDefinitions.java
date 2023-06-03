package definitions;

import com.sei.findgo.FindGoApplication;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FindGoApplication.class)
public class StepDefinitions {

    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    String port;

    private static Response response;

    public String JWTTestKey() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "obinna@example.com");
        jsonObject.put("password", "password3");
        request.header("Content-Type", "application/json");
        response = request.body(jsonObject.toString()).post(BASE_URL + port + "/api/auth/users/login");
        return response.jsonPath().getString("message");
    }
    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.get(BASE_URL + port + "/api/users/register");
        response.then().statusCode(200);
    }






}
