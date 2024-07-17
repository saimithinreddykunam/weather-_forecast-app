package com.example.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class WeatherService {
    private final String apiKey = "YOUR_API_KEY";
    private final String baseUrl = "http://api.openweathermap.org/data/2.5/weather";

    public JsonNode getWeather(String location) throws IOException {
        String url = String.format("%s?q=%s&appid=%s&units=metric", baseUrl, location, apiKey);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readTree(response.getEntity().getContent());
            }
        }
    }
}