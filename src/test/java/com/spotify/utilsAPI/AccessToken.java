package com.spotify.utilsAPI;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AccessToken {
    @Autowired
    @Qualifier("restTemplateWithSSL")
    private RestTemplate restTemplate;
    static HttpHeaders headers = new HttpHeaders();
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AccessToken.class);

    public static void main(String[] args) throws OAuthProblemException, OAuthSystemException {
        String clientID = "9dbe07b1fd2b4266af129096b449363b";
        String clientSecret = "c03449d1756a4748a16fcc65c1031efe";
        String tokenURL = "https://accounts.spotify.com/api/token";

        String currentToken;
        String bearerValue;
        String encodedValue = getBase64Encoded(clientID, clientSecret);


        OAuthClient client = new OAuthClient(new URLConnectionClient());
        OAuthClientRequest request = OAuthClientRequest.tokenLocation(tokenURL).
                setGrantType(GrantType.CLIENT_CREDENTIALS).buildBodyMessage();

        request.addHeader("Authorization", "Basic" + encodedValue);
        OAuthJSONAccessTokenResponse oAuthResponse = client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class);
        currentToken = oAuthResponse.getAccessToken();
        bearerValue = " Bearer " + currentToken;
        System.out.println(bearerValue);


    }

    public static String getBase64Encoded(String id, String password) {
        return Base64.getEncoder().encodeToString((id + ":" + password).getBytes(StandardCharsets.UTF_8));
    }
}
