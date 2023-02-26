package com.spotify.utilities;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:data.properties")
public class HelperMethods {
    public static Properties properties;
    String propertyValue = "";

    public String getProperty(String property) {
        try {
            properties = new Properties();
            String fileName = "data.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Data properties not found");
            }

            String dataProperty = properties.getProperty(property);
            this.propertyValue = dataProperty;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return propertyValue;
    }

    public String getKey(String key) {
        String value = "";
        try {
            FileInputStream fis = new FileInputStream("src//test//resources//data.properties");
            properties.load(fis);
            value = properties.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
}
