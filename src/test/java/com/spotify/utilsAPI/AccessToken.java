package com.spotify.utilsAPI;

import com.spotify.utilities.HelperMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.spotify.utilities.EncryptDecrypt.decryptString;
import static com.spotify.utilities.EncryptDecrypt.encryptString;

public class AccessToken {
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
    static HttpHeaders headers = new HttpHeaders();
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AccessToken.class);

    @Given("User is retrieving access token")
    public void userIsRetrievingAccessToken() throws Exception {
        String currentToken;
        String bearerValue;
        String tokenURL = spotifyAccountsService + "/api/token";

        String encryptedSecret = encryptString(clientSecret);
        String secretDecrypted = decryptString(encryptedSecret);
        String encodedValue = getBase64Encoded(clientID, secretDecrypted);

        OAuthClient client = new OAuthClient(new URLConnectionClient());
        OAuthClientRequest request = OAuthClientRequest.tokenLocation(tokenURL).
                setGrantType(GrantType.CLIENT_CREDENTIALS).buildBodyMessage();

        request.addHeader("Authorization", "Basic " + encodedValue);
        OAuthJSONAccessTokenResponse oAuthResponse = client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class);
        currentToken = oAuthResponse.getAccessToken();
        bearerValue = "Bearer " + currentToken;
        LOG.info(bearerValue);
    }

    @When("User is checking the API functionalities")
    public void userIsCheckingAPI() throws Exception {
    }

    @Then("User can do API calls")
    public void userCanDoAPICalls() throws Exception {

    }

    public String getBase64Encoded(String id, String password) {
        return Base64.getEncoder().encodeToString((id + ":" + password).getBytes(StandardCharsets.UTF_8));
    }
}
