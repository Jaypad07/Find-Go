package definitions;

import com.sei.findgo.FindGoApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
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
        response = request.body(jsonObject.toString()).post(BASE_URL + port + "/api/users/login");
        return response.jsonPath().getString("message");
    }


//    @Given("I am on the registration page")
//    public void iAmOnTheRegistrationPage() throws JSONException {
//    }
//
//    @When("I enter valid registration details \\(username, email, password)")
//    public void iEnterValidRegistrationDetailsUsernameEmailPassword() throws JSONException {
//        RequestSpecification request = RestAssured.given();
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("username", "example34");
//        requestBody.put("email", "example34@example.com");
//        requestBody.put("password", "password5");
//        request.header("Content-Type", "application/json");
//        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/users/register");
//        Assert.assertEquals(200, response.getStatusCode());
//    }
//
//    @Then("I should be successfully registered")
//    public void iShouldBeSuccessfullyRegistered() throws JSONException {
//        Assert.assertEquals(200, response.getStatusCode());
//    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
    }

    @When("I enter valid login credentials \\(username, password)")
    public void iEnterValidLoginCredentialsUsernamePassword() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "jay@example.com");
        requestBody.put("password", "password5");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/users/login");
        Assert.assertEquals(200, response.getStatusCode());
    }
}
