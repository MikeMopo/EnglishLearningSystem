package zw.co.els.englishlearningsystem.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenAIClient {
    public static String getAIResponse(String question) throws IOException, URISyntaxException, InterruptedException {
        String apiKey = "AIzaSyBACnsip0ujadWJUmk4kNlJjU-NW-uqjUI";
        String jsonPayload = "{\"contents\":[{\"parts\":[{\"text\":\"" + question + "\"}]}]}";
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + apiKey;

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}