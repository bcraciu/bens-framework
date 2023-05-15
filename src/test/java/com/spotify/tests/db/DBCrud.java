package com.spotify.tests.db;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spotify.config.AuthorizationServerConfig;
import com.spotify.utilities.HelperMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpMethod.*;

public class DBCrud {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DBCrud.class);

    @Autowired
    private HelperMethods helperMethods;
    @Autowired
    private AuthorizationServerConfig springSecurityConfig;
    @Value("${spotify.accounts.service}")
    private String spotifyAccountsService;
    @Value("${client.id}")
    private String clientID;
    @Value("${client.secret}")
    private String clientSecret;
    @Value("${testDataPath}")
    private String testDataPath;
    @Value("${localhost.url}")
    private String localhost;
    @Autowired
    @Qualifier("restTemplateWithSSL")
    private RestTemplate restTemplate;
    ResponseEntity<String> response;
    HttpHeaders headers = new HttpHeaders();
    public String user = "autotest.bcraciu@gmail.com";
    public String pass = "13Framework*";
    public String servicePath;
    public String filePath;
    public String responseReceived;
    int i;
    JsonObject jsonObject;

    @Given("Connection with the DB is established")
    public void connectionWithTheDBIsEstablished() throws IOException {
        filePath = testDataPath + "saveNewPersonBody";
        headers.setBasicAuth(user, pass);
        headers.setContentType(MediaType.TEXT_PLAIN);

        response = restTemplate.exchange(localhost, GET, new HttpEntity<>(headers), String.class);
        Assert.assertEquals("This status is not expected", 200, response.getStatusCode().value());
    }

    @When("User is saving new persons")
    public void userIsSavingNewPersons() throws IOException {

        servicePath = localhost + "/db/savePerson";
        filePath = testDataPath + "saveNewPersonBody";
        headers.setBasicAuth(user, pass);
        headers.setContentType(MediaType.APPLICATION_JSON);

        JsonArray jsonArray = HelperMethods.getJsonArray(testDataPath + "saveNewPersonBody");
        int jsonCount = jsonArray.size();
        for (i = 0; i < jsonCount; i++) {
            jsonObject = (JsonObject) jsonArray.get(i);
            LOG.info(jsonObject.get("personalCode").toString());
            String randomInt = RandomStringUtils.randomNumeric(3);
            String randomAlphabetic = RandomStringUtils.randomAlphabetic(15);
            JsonElement changedCode = JsonParser.parseString(randomInt);
            jsonObject.remove("personalCode");
            jsonObject.add("personalCode", changedCode);
            JsonElement changedName = JsonParser.parseString(randomAlphabetic);
            jsonObject.remove("full_name");
            jsonObject.add("full_name", changedName);

            response = restTemplate.exchange(servicePath, POST, new HttpEntity<>(jsonObject.toString(), headers), String.class);
            Assert.assertEquals("This status is not expected", 200, response.getStatusCode().value());

            responseReceived = response.getBody();
            LOG.info("For request: " + (i + 1) + " the response is: " + response.getBody());
        }

        //Asserting if the third entry corresponds with the data inserted
        servicePath = localhost + "/db/3";
        headers.setBasicAuth(user, pass);
        headers.setContentType(MediaType.TEXT_PLAIN);
        response = restTemplate.exchange(servicePath, GET, new HttpEntity<>(headers), String.class);
        Assert.assertEquals("This status is not expected", 200, response.getStatusCode().value());

        Assert.assertEquals(jsonObject.toString(), response.getBody());
    }

    @And("Updates a person")
    public void updatesAPerson() throws IOException {
        servicePath = localhost + "/db/updatePerson";
        JsonObject jsonBody = HelperMethods.getJsonObject(testDataPath + "updatePersonBody");
        headers.setBasicAuth(user, pass);
        headers.setContentType(MediaType.APPLICATION_JSON);

        response = restTemplate.exchange(servicePath, PUT, new HttpEntity<>(jsonBody.toString(), headers), String.class);
        responseReceived = response.getBody();

        //Asserting if the third entry is updated
        servicePath = localhost + "/db/3";
        headers.setBasicAuth(user, pass);
        headers.setContentType(MediaType.TEXT_PLAIN);
        response = restTemplate.exchange(servicePath, GET, new HttpEntity<>(headers), String.class);
        Assert.assertEquals("This status is not expected", 200, response.getStatusCode().value());

        Assert.assertEquals(jsonBody.toString(), response.getBody());
    }

    @And("Waits for the DB to be available {int}")
    public void waitsForTheDBToBeAvailable(int seconds) throws InterruptedException {
        Thread.sleep(1000L * seconds);
    }

    @And("Deletes a person with id {string}")
    public void deletesAPerson(String id) throws IOException {
        servicePath = localhost + "/db/deletePerson/" + id;
        headers.setBasicAuth(user, pass);
        headers.setContentType(MediaType.APPLICATION_JSON);

        response = restTemplate.exchange(servicePath, DELETE, new HttpEntity<>(headers), String.class);
        Assert.assertEquals("This status is not expected", 200, response.getStatusCode().value());
    }

    @Then("Data inserted in the Table is asserted")
    public void dataInsertedInTheTableIsAsserted() throws Throwable {
        servicePath = localhost + "/db/getData";
        headers.setBasicAuth(user, pass);
        headers.setContentType(MediaType.TEXT_PLAIN);
        response = restTemplate.exchange(servicePath, GET, new HttpEntity<>(headers), String.class);
        Assert.assertEquals("This status is not expected", 200, response.getStatusCode().value());
        LOG.info(response.getBody());
        JsonArray responseArray = JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonArray();
        for (int i = 0; i < responseArray.size(); i++) {
            JsonObject jsonObj = (JsonObject) responseArray.get(i);
            JsonElement jsonElem = jsonObj.get("id");
            if (jsonElem.getAsInt() == 2) {
                throw new Throwable("Id 2 should not be present");
            }
        }
    }

        //replace non-word character .replace("\\W", "")
        //JsonObject jsonObjectResponse = JsonParser.parseString(responseReceived).getAsJsonObject();


//        LOG.info("Request send is: " + response.toString());
//        LOG.info(response.getStatusCode().toString());
//        LOG.info(response.getBody());

//        alternative call
//        LOG.info("Redirected url is " + helperMethods.getRedirectedURL(servicePath));
//
//        HttpClient client = HttpClient.newBuilder()
//                .build();
//        HttpRequest request = HttpRequest.newBuilder()
//                .GET()
//                .uri(new URI("http://localhost:8888/db/savePerson"))
//                .header("Authorization", getBasicAuthenticationHeader(user, pass))
//                .build();
//        HttpResponse<String> response2 = client.send(request, HttpResponse.BodyHandlers.ofString());
//        LOG.info(String.valueOf(response2));
    }
