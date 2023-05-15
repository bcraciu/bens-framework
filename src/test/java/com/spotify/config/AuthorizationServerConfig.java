package com.spotify.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import static com.spotify.utilities.EncryptDecrypt.decryptString;
import static com.spotify.utilities.EncryptDecrypt.encryptString;

@Import(OAuth2AuthorizationServerConfiguration.class)

@Configuration
public class AuthorizationServerConfig {
    @Value("${spotify.accounts.service}")
    private String spotifyAccountsService;
    @Value("${client.id}")
    private String clientID;
    @Value("${client.secret}")
    private String clientSecret;

    //filter chain for authorization server
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        //authorization server will redirect to (client) login page with the authorization code
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());
        http.exceptionHandling(
                e -> e.authenticationEntryPoint(
                        new LoginUrlAuthenticationEntryPoint("/login")));
        return http.formLogin().and().build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() throws Exception {
        String encryptedSecret = encryptString(clientSecret);
        String secretDecrypted = decryptString(encryptedSecret);

        RegisteredClient r1 = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(clientID)
                .clientSecret(secretDecrypted)
                .scope(OidcScopes.OPENID)
                .scope("user-modify-playback-state")
                .scope("user-read-private")
                .scope("user-read-email")
                .redirectUri("http://localhost:8888/callback")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .build();

        return new InMemoryRegisteredClientRepository(r1);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(2048);
        KeyPair kp = kg.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();

        RSAKey key = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet set = new JWKSet(key);
        return new ImmutableJWKSet<>(set);
    }

    //    @Bean
//    public ProviderSettings providerSettings(){
//        return ProviderSettings.builder().build();
//    }


    //authorization server manages the user
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        var u1 = User.withUsername("user")
//                .password("password")
//                .authorities("read")
//                .build();
//
//        return new InMemoryUserDetailsManager(u1);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }


}
