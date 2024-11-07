package com.PS29729.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@RestController
public class LocationController {

    @GetMapping("/api/provinces")
    public Map<String, Object> getProvinces(HttpServletRequest request) {
        String url = "https://open.oapi.vn/location/provinces?page=0&size=64";
        return fetchData(url);
    }

    @GetMapping("/api/districts/{provinceId}")
    public Map<String, Object> getDistricts(@PathVariable String provinceId, HttpServletRequest request) {
        String url = "https://open.oapi.vn/location/districts/" + provinceId + "?page=0&size=30";
        return fetchData(url);
    }
    @GetMapping("/api/wards/{Id}")
    public Map<String, Object> getWards(@PathVariable String Id, HttpServletRequest request) {
        String url = "https://open.oapi.vn/location/wards/" + Id + "?page=0&size=30";
        return fetchData(url);
    }

    private Map<String, Object> fetchData(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.toString(), Map.class);
            } else {
                System.out.println("GET request not worked: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
