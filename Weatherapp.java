package com.example.weather;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WeatherApp {
    public static void main(String[] args) {
        WeatherService weatherService = new WeatherService();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a location: ");
        try {
            String location = reader.readLine();
            JsonNode weatherData = weatherService.getWeather(location);
            if (weatherData != null) {
                String cityName = weatherData.get("name").asText();
                double temperature = weatherData.get("main").get("temp").asDouble();
                String description = weatherData.get("weather").get(0).get("description").asText();

                System.out.printf("Location: %s\n", cityName);
                System.out.printf("Temperature: %.2f °C\n", temperature);
                System.out.printf("Weather: %s\n", description);
            } else {
                System.out.println("Could not fetch weather data.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}