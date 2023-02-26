package com.spotify.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;

public class ManageDriver {

    @Value("${browser}")
    private String browserUsed;
    @Value("${selenium.headless}")
    private Boolean headless;

    public static WebDriver driver;
    public WebDriverWait wait;

//    public ManageDriver() {
//    }

//    private boolean isDriverLoaded() {
//        return driver != null;
//    }

    public WebDriver getDriver() {
        initialiseDriver();
        return driver;
    }
    private void initialiseDriver() {
        //String browserChoosen = this.browserChoosen;
//        if (!isEmpty(driver)) {
//            closeDriver();
//        }
        if ("chrome".equals(browserUsed)) {
            setChromeDriver();
        } else if ("firefox".equals(browserUsed)) {
            setFirefoxDriver();
        }

        // Navigate
//      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        //goToBaseUrl();
    }

    private void setChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--allow-insecure-localhost");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);

//        Headless mode
//        if (TRUE.equals(headless)) {
//            chromeOptions.addArguments("--headless");
//        }
    }

    private void setFirefoxDriver() {
        driver = new FirefoxDriver();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxBinary firefoxBinary = new FirefoxBinary();

        // Headless mode
//        if (TRUE.equals(headless)) {
//            firefoxBinary.addCommandLineOptions("--headless");
//            firefoxOptions.addArguments("--width=1920");
//            firefoxOptions.addArguments("--height=1080");
//        }
        firefoxOptions.setBinary(firefoxBinary);
        driver = new FirefoxDriver(firefoxOptions);
        //return driver;
    }

    public void closeDriver() {
        if (driver == null) {
            return;
        }
        driver.quit();
        driver = null;
    }
}