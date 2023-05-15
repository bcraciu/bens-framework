package com.spotify.tests.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spotify.utilities.HelperMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

import static com.spotify.utilities.EncryptDecrypt.decryptString;
import static com.spotify.utilities.EncryptDecrypt.encryptString;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


public class ClientCredentialsFlow extends HelperMethods {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ClientCredentialsFlow.class);
    ResponseEntity<String> response;
    HttpHeaders headers = new HttpHeaders();
    String token;
    String artistName;
    String trackName;
    @Autowired
    @Qualifier("restTemplateWithSSL")
    private RestTemplate restTemplate;
    @Autowired
    private HelperMethods helperMethods;
    @Value("${spotify.accounts.service}")
    private String spotifyAccountsService;
    @Value("${client.id}")
    private String clientID;
    @Value("${client.secret}")
    private String clientSecret;
    @Value("${api.base}")
    private String apiBase;

    @Given("User has access to Spotify API")
    public void userHasAccessToSpotifyAPI() throws Exception {
        String encryptedSecret = encryptString(clientSecret);
        String secretDecrypted = decryptString(encryptedSecret);
        String tokenURL = spotifyAccountsService + "/api/token";
        String encodedValue = getBase64Encoded(clientID, secretDecrypted);

        headers.add("Authorization", "Basic " + encodedValue);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        fileMap.add("grant_type", "client_credentials");

        response = restTemplate.exchange(tokenURL, POST, new HttpEntity<>(fileMap, headers), String.class);
        Assert.assertEquals("Status not ok", 200, response.getStatusCode().value());

        JsonObject jsonObjectFromResponse = JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject();

        token = getElemAsString(jsonObjectFromResponse, "access_token");
        LOG.info(token);
        String tokenType = getElemAsString(jsonObjectFromResponse, "token_type");
        Assert.assertEquals("Token type is not bearer", "Bearer", tokenType);
    }

    @When("User is searching for a track")
    public void userIsSearchingForATrack() {
        String apiURL = apiBase + "/search?";
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(token);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiURL)
                .queryParam("q", "coldplay%20up%20and%20up")
                .queryParam("type", "track")
                .queryParam("market", "US")
                .queryParam("limit", "1")
                .queryParam("offset", "0")
                .encode()
                .toUriString();

//        Map<String, String> params = new HashMap<>();
//        params.put("q", "coldplay%20up%20and%20up");
//        params.put("type", "track");
//        headers.add("q", "coldplay%2520up%2520and");
//        headers.add("type", "track");
//        headers.add("market", "GB");
//        headers.add("limit", "10");
//        headers.add("offset", "5");
        // headers.set("Authorization", "Bearer " + token);
        //headers.setContentType(MediaType.APPLICATION_JSON);
        //response = restTemplate.exchange(apiURL, GET, new HttpEntity<>(headers), String.class, params);

        response = restTemplate.exchange(urlTemplate, GET, new HttpEntity<>(headers), String.class);
        Assert.assertEquals("Status not ok", 200, response.getStatusCode().value());

        String body = response.getBody();
        //LOG.info(body);

        JsonObject bodyAsJson = JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject();
        JsonObject tracks = bodyAsJson.getAsJsonObject("tracks");
        JsonArray items = tracks.getAsJsonArray("items");
        JsonObject firstItem = items.get(0).getAsJsonObject();
        trackName = firstItem.getAsJsonObject().getAsJsonPrimitive("name").toString().replaceAll("\"", "");
        JsonArray artists = firstItem.getAsJsonArray("artists");
        JsonObject artistsJson = artists.get(0).getAsJsonObject();
        artistName = artistsJson.getAsJsonObject().getAsJsonPrimitive("name").toString().replaceAll("\"", "");
        LOG.info("Track name is: " + trackName + " " + "// Artist name is: " + artistName);
    }

    @Then("The track is validated")
    public void theTrackIsValidated() {
        Assert.assertEquals("Artist name is NOT Coldplay ..", "Coldplay", artistName);
        Assert.assertEquals("Track name is NOT Up and Up ..", "Up&Up", trackName);
    }


    @When("User skips to the next track")
    public void userSkipsToTheNextTrack() {
    }

    @Then("The next track is playing")
    public void theNextTrackIsPlaying() {
    }
}
