package com.igti.facebookoauth.helpers;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum ApplicationProperties {
    INSTANCE;

    private final Properties properties;

    ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("credentials.properties"));
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public String getClientId() {
        return properties.getProperty("client_id");
    }

    public String getClientSecret() {
        return properties.getProperty("client_secret");
    }

    public String getAccessToken() {
        return properties.getProperty("access_token");
    }
}
