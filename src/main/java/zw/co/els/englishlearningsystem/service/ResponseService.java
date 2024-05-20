package zw.co.els.englishlearningsystem.service;

import org.json.JSONArray;
import org.json.JSONObject;
public class ResponseService {
    public static String extractText(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray candidatesArray = jsonObject.getJSONArray("candidates");
        JSONObject firstCandidate = candidatesArray.getJSONObject(0);
        JSONObject contentObject = firstCandidate.getJSONObject("content");
        JSONArray partsArray = contentObject.getJSONArray("parts");

        JSONObject firstPart = partsArray.getJSONObject(0);

        String text = firstPart.getString("text");

        return text;
    }
}
