package com.heroiclabs.mezon.model;

import lombok.Data;
import java.util.Map;

@Data
public class ApiAccountApp {
    public ApiAccountApp(String token){
        this.token = token;
    }
    // The app ID
    private String appid;

    // The app name
    private String appname;

    // The account token when creating apps to access their profile API.
    private String token;

    // Extra information that will be bundled in the session token.
    private Map<String, String> vars;
}
