package com.spotify.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @GetMapping("/")
    String login() {
        return "This is the page for the successful login";
    }
    @GetMapping("/callback")
    public String code(){
        return "Code should be displayed in the Url";
    }

}
