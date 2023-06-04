package definitions;

import com.sei.findgo.FindGoApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FindGoApplication.class)
public class StepDefinitions {

    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    String port;

    private static Response response;

    private String token;

    private static ResponseEntity<String> responseEntity;
    private static List<?> list;

    public String JWTTestKeyAdmin() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "jay@example.com");
        jsonObject.put("password", "password5");
        request.header("Content-Type", "application/json");
        response = request.body(jsonObject.toString()).post(BASE_URL + port + "/api/users/login");
        return response.jsonPath().getString("message");
    }

    public String JWTTestKeyManager() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "marie@example.com");
        jsonObject.put("password", "password4");
        request.header("Content-Type", "application/json");
        response = request.body(jsonObject.toString()).post(BASE_URL + port + "/api/users/login");
        return response.jsonPath().getString("message");
    }


    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
    }

    @When("I enter valid registration details \\(username, email, password)")
    public void iEnterValidRegistrationDetailsUsernameEmailPassword() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "example34");
        requestBody.put("email", "example34@example.com");
        requestBody.put("password", "password5");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/users/register");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("I should be successfully registered")
    public void iShouldBeSuccessfullyRegistered() {
        Assert.assertEquals(200, response.getStatusCode());
    }

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

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Given("there are multiple users in the system")
    public void thereAreMultipleUsersInTheSystem() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(JWTTestKeyAdmin());
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/auth/users", HttpMethod.GET, entity, String.class);
        list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
        Assert.assertEquals(200, response.getStatusCode());
    }


    @When("the client requests to get all users")
    public void theClientRequestsToGetAllUsers() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(JWTTestKeyAdmin());
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/auth/users", HttpMethod.GET, entity, String.class);
        list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
    }

    @Then("the response should contain a list of all users")
    public void theResponseShouldContainAListOfAllUsers() {
        Assert.assertTrue(list.size() > 0);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Given("user is an Admin")
    public void userIsAnAdmin() throws JSONException {
        RequestSpecification request = RestAssured.given();
        token = JWTTestKeyAdmin();
        request.header("Authorization", "Bearer " + token);
    }

    @When("I search for a user by Id")
    public void iSearchForAUserById() {
        RestAssured.baseURI = BASE_URL + port + "/api/auth/users/1";
        RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token);
        response = request.get();
    }

    @Then("the response should contain the user details")
    public void theResponseShouldContainTheUserDetails() {
        Assert.assertNotNull(String.valueOf(response));
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("I update a users details")
    public void iUpdateAUsersDetails() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        request.header("Authorization", "Bearer "+ token);
        requestBody.put("userName", "Tim");
        requestBody.put("email", "Rodriguez@example.com");
        requestBody.put("password", "password35");
        requestBody.put("role", "Manager");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/auth/users/1");

    }

    @Then("the user should be successfully updated")
    public void theUserShouldBeSuccessfullyUpdated() {
        Assert.assertNotNull(String.valueOf(response));
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("I delete a user")
    public void iDeleteAUser() {
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer "+ token);
        response = request.delete(BASE_URL + port + "/api/auth/users/1");
    }

    @Then("the user should be successfully deleted")
    public void theUserShouldBeSuccessfullyDeleted() {
        Assert.assertNotNull(String.valueOf(response));
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("user creates a store")
    public void userCreatesAStore() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        request.header("Authorization", "Bearer "+ token);
        requestBody.put("storeName", "Circuit City");
        requestBody.put("description", "Electronics Store");
        requestBody.put("city", "Cerritos");
        requestBody.put("map", "floorPlan.png");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/stores");
    }

    @Then("the store should successfully be added")
    public void theStoreShouldSuccessfullyBeAdded() {
        Assert.assertNotNull(String.valueOf(response));
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Given("Manager is logged in and a list of store is available")
    public void managerIsLoggedInAndAListOfStoreIsAvailable() throws JSONException {
        RequestSpecification request = RestAssured.given();
        token = JWTTestKeyManager();
        request.header("Authorization", "Bearer " + token);
    }




    @Given("the user wants to find all stores")
    public void theUserWantsToFindAllStores() {
    }


    @When("the user sends a request to get all stores")
    public void theUserSendsARequestToGetAllStores() {
        responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/stores", HttpMethod.GET, null, String.class);
        list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
    }

    @Then("the response should contain a list of all stores")
    public void theResponseShouldContainAListOfAllStores() {
        Assert.assertTrue(list.size() > 0);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Given("the user wants to get all products")
    public void theUserWantsToGetAllProducts() {

    }

    @When("the user sends a request to get all products")
    public void theUserSendsARequestToGetAllProducts() {
        responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/products", HttpMethod.GET, null, String.class);
        list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
    }

    @Then("the response should contain a list of all products")
    public void theResponseShouldContainAListOfAllProducts() {
        Assert.assertTrue(list.size() > 0);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
