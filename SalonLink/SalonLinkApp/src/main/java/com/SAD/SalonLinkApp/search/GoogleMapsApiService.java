package com.SAD.SalonLinkApp.search;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class GoogleMapsApiService {

    public String calculateDistanceBetweenTwoAddresses(String apiKey, String origin, String destination) throws UnsupportedEncodingException {

        String encodedOrigin = URLEncoder.encode(origin, "UTF-8");
        String encodedDestination = URLEncoder.encode(destination, "UTF-8");

        String urlString = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="
                + encodedOrigin + "&destinations=" + encodedDestination + "&key=" + apiKey;

        try {
            // Creating HTTP connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Reading the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parsing the JSON response
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
            JsonArray rows = jsonObject.getAsJsonArray("rows");
            JsonArray elements = rows.get(0).getAsJsonObject().getAsJsonArray("elements");
            JsonObject element = elements.get(0).getAsJsonObject();
            JsonObject distanceObj = element.getAsJsonObject("distance");
            String distance = distanceObj.get("text").getAsString();
            conn.disconnect();
            return (distance);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Exception encountered";

    }




}
