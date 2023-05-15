package com.spotify;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes = {AutomationApplication.class})
@CucumberOptions(
        features = {"classpath:features/"},
        tags = "@SpotifyTests",
        glue = "com.spotify.tests.selenium",
        monochrome = true,
        plugin = {"pretty",
                "json:/target/cucumber-reports/test.json",
                "html:/target/cucumber-reports/report.html"})

public class TestRunner {
}

