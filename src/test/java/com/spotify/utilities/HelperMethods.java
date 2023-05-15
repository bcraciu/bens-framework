package com.spotify.utilities;

import com.google.gson.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:data.properties")
public class HelperMethods {
    public static Properties properties;
    String propertyValue = "";
    static JsonObject aJsonObject;

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

    public static String getBase64Encoded(String id, String password) {
        return Base64.getEncoder().encodeToString((id + ":" + password).getBytes(StandardCharsets.UTF_8));
    }

    public static JsonObject createAJson(String json) {
        aJsonObject = new Gson().fromJson(json, JsonObject.class);
        return aJsonObject;
    }

    public static JsonObject getJsonObject(String filePath) throws IOException {
        JsonObject jObject;
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        jObject = JsonParser.parseReader(reader).getAsJsonObject();
        return jObject;
    }

    public static JsonArray getJsonArray(String filePath) throws IOException {
        JsonArray jArray;
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        jArray = JsonParser.parseReader(reader).getAsJsonArray();
        return jArray;
    }

    public String getElemAsString(JsonObject resultJsonObject, String elementName) {
        JsonElement element = resultJsonObject.get(elementName);
        return element.toString().replaceAll("\"", "");
    }

    public String getUrlLocation(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) (new URL(url).openConnection());
        con.setInstanceFollowRedirects(false);
        con.connect();
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);
        String location = con.getHeaderField("Location");
        System.out.println(location);
        return location;
    }

    public String getRedirectedURL(String url) throws IOException {
        URLConnection con = new URL(url).openConnection();
        System.out.println("orignal url: " + con.getURL());
        con.connect();
        System.out.println("connected url: " + con.getURL());
        InputStream inputStream = con.getInputStream();
        System.out.println("redirected url: " + con.getURL());
        inputStream.close();
        return con.getURL().toString();
    }

    public static String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

//    public static void updateBody(String filePath) throws IOException {
//    BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));
//    StringBuffer inputBuffer = new StringBuffer();
//
//    String line;
//        while ((line = reader.readLine()) != null) {
//        if (line.contains("swversion")) {
//            String oldSwVersion = line.substring(line.lastIndexOf("\"", line.lastIndexOf("\"") - 1) + 1, line.lastIndexOf("\""));
//            int newSwVersion = 0;
//            int year = Integer.parseInt(new SimpleDateFormat("yy").format(new java.util.Date()));
//
//            newSwVersion = Integer.parseInt(year + currentWeek + lastNo);
//            if (line.contains(","))
//                line = "\"swversion\": \"" + newSwVersion + "\",";
//            else
//                line = "\"swversion\": \"" + newSwVersion + "\"";
//        }
//        inputBuffer.append(line);
//        inputBuffer.append("\n");
//    }
//        reader.close();
//    FileOutputStream modifiedFile = new FileOutputStream(filePath.substring(filePath.lastIndexOf("\\") + 1));
//        modifiedFile.write(inputBuffer.toString().getBytes());
//        modifiedFile.close();
//
//}
}