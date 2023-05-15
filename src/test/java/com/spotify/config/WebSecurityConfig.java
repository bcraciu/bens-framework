package com.spotify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.asynchttpclient.util.HttpConstants.Methods.POST;

@Configuration  ////configuration of the users
public class WebSecurityConfig {
    //application security filter chain -- creating a default page with username and pass

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                //.loginPage("/login").permitAll()
                .and()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(new AntPathRequestMatcher("/db/check/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/db/savePerson", POST)).permitAll()
                        .anyRequest().authenticated()) //other URLs are only allowed authenticated users.
                .httpBasic();
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
//        http.formLogin().and().authorizeHttpRequests()
//                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
//                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/db/check/**")).permitAll()
//                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/db/getData/**")).permitAll()
//                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/callback/**")).permitAll()
//                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/db/{id}")).permitAll()
//                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/db/getData")).permitAll()
//                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/db/savePerson", POST)).permitAll()
//                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/db/updatePerson", PUT)).permitAll()
//                .and().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/db/deletePerson", DELETE)).permitAll();
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//
//                //             .formLogin().and().authorizeHttpRequests()
//                //
//                //            .requestMatchers("/login*", "/logout*", "/signin/**", "/signup/**", "/customLogin", "/user/registration*", "/registrationConfirm*", "/expiredAccount*", "/registration*", "/badUser*", "/user/resendRegistrationToken*", "/forgetPassword*",
//                //                                 "/user/resetPassword*", "/user/savePassword*", "/updatePassword*", "/user/changePassword*", "/emailError*", "/resources/**", "/old/user/registration*", "/successRegister*", "/qrcode*", "/user/enableNewLoc*")
//                //            .permitAll();
//                //
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/db/check/**", "/db/getData/**").permitAll()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login").successForwardUrl("/callback")
//                )
//                .logout(LogoutConfigurer::permitAll);
//
//
//        return http.build();
//    }

    @Bean
    public UserDetailsService userDetailsService() throws UsernameNotFoundException {

        var u1 = User.withUsername("autotest.bcraciu@gmail.com")
                .password("13Framework*")
                .authorities("read")
                .build();
//        var uds = new InMemoryUserDetailsManager();
//        uds.createUser(u1);
//        return uds;
        return new InMemoryUserDetailsManager(u1);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails firstUser = User.withUsername("autotest.bcraciu@gmail.com").password("13Framework*").build();
//        userDetailsManager.createUser(firstUser);
//        auth.userDetailsService(userDetailsManager);
//    }





//    @Bean
//    public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
//        return http.formLogin()
//                .and().authorizeHttpRequests().anyRequest().authenticated()
//                .and()
//                .build();
//    }


}
