package zw.co.els.englishlearningsystem.service;

import org.springframework.beans.factory.annotation.Value;
import zw.co.els.englishlearningsystem.utils.Config;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenAIClient {

    private static Config config = Config.getInstance();
    private static String apiKey = config.getProperty("openai.apiKey");
    private static String apiEndpoint = config.getProperty("openai.apiEndpoint");
    public static String getAIResponse(String question) throws IOException, URISyntaxException, InterruptedException {

        var jsonPayload = "{\"contents\":[{\"parts\":[{\"text\":\"" + question + "\"}]}]}";
        var endpoint = apiEndpoint + apiKey;

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