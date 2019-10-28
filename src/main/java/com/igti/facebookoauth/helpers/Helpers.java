package com.igti.facebookoauth.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.igti.facebookoauth.entity.AccessToken;
import com.igti.facebookoauth.entity.Cliente;
import com.igti.facebookoauth.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

public class Helpers {

    Gson gson = new Gson();
    JsonParser parser = new JsonParser();
    Properties prop = new Properties();

    final String facebookUrlAccessToken = "https://graph.facebook.com/v4.0/oauth/access_token";
    final String facebookUrlTokenInfo = "https://graph.facebook.com/debug_token";

    public String getAccessToken(String id, String state, String code) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<AccessToken> response
                = restTemplate.getForEntity(facebookUrlAccessToken +
                "?client_id=" + ApplicationProperties.INSTANCE.getClientId() + "&redirect_uri=http://localhost:8080/conta/cadastro/" +
                id + "" + "&state=" + state + "&client_secret=" +
                ApplicationProperties.INSTANCE.getClientSecret() + "&code=" + code, AccessToken.class);

        AccessToken body = response.getBody();

        return body.getAccess_token();
    }

    public String getUserId(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<User> responseTokenInfo
                = restTemplate.getForEntity(facebookUrlTokenInfo +
                "?input_token=" + accessToken + "&access_token=" +
                ApplicationProperties.INSTANCE.getAccessToken(), User.class);

        User body = responseTokenInfo.getBody();

        String jsonElement = gson.toJson(body);
        JsonObject obj = parser.parse(jsonElement).getAsJsonObject();

        JsonObject data = obj.get("data").getAsJsonObject();

        return data.get("user_id").getAsString();
    }

    public String getUserName(String accessToken, String userId) {
        RestTemplate restTemplate = new RestTemplate();
        String facebookUrlUserInfo = "https://graph.facebook.com/" + userId + "/?access_token=" + accessToken;

        ResponseEntity<Cliente> responseUserInfo
                = restTemplate.getForEntity(facebookUrlUserInfo, Cliente.class);

        Cliente body = responseUserInfo.getBody();

        String jsonElement = gson.toJson(body);
        JsonObject obj = parser.parse(jsonElement).getAsJsonObject();

        return obj.get("name").getAsString();
    }
}
