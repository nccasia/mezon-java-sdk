package com.heroiclabs.mezon.api;

import com.google.gson.*;
import com.heroiclabs.mezon.model.ApiAuthenticateRequest;
import com.heroiclabs.mezon.model.ApiClanDescList;
import com.heroiclabs.mezon.session.ApiSession;
import lombok.Getter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.Base64;

@Getter
public class MezonApi {

    private final String apiKey;
    private final String basePath;
    private final int timeoutMs;

    public MezonApi(String apiKey, String basePath, int timeoutMs) {
        this.apiKey = apiKey;
        this.basePath = basePath;
        this.timeoutMs = timeoutMs;
    }

    /**
     * List clan descriptions.
     */
    public CompletableFuture<ApiClanDescList> listClanDescs(String bearerToken) {
        return CompletableFuture.supplyAsync(() -> {
            String urlPath = "/v2/clandesc";
            String fullUrl = buildFullUrl(basePath, urlPath, new HashMap<>());

            HttpURLConnection connection = null;
            try {
                connection = createConnection(fullUrl, "GET", bearerToken, null);

                int responseCode = connection.getResponseCode();
                if (responseCode == 204) {
                    return null; // No content
                } else if (responseCode >= 200 && responseCode < 300) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        String response = readResponse(reader);
                        return deserializeResponse(response);
                    }
                } else {
                    throw new RuntimeException("Request failed with status: " + responseCode);
                }
            } catch (IOException e) {
                throw new RuntimeException("Request failed: " + e.getMessage(), e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        });
    }

    /**
     * Authenticate user.
     */
    public CompletableFuture<ApiSession> mezonAuthenticate(String basicAuthUsername, String basicAuthPassword, ApiAuthenticateRequest body) {
        return CompletableFuture.supplyAsync(() -> {
            if (body == null) {
                throw new IllegalArgumentException("'body' is a required parameter but is null or undefined.");
            }

            String urlPath = "/v2/apps/authenticate/token";
            String fullUrl = buildFullUrl(basePath, urlPath, new HashMap<>());

            String bodyJson = buildBodyJson(body);

            HttpURLConnection connection = null;
            try {
                connection = createConnection(fullUrl, "POST", basicAuthUsername, basicAuthPassword);
                writeRequestBody(connection, bodyJson);

                int responseCode = connection.getResponseCode();
                if (responseCode == 204) {
                    return null;
                } else if (responseCode >= 200 && responseCode < 300) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        String response = readResponse(reader);
                        return parseApiSession(response);
                    }
                } else {
                    throw new RuntimeException("Failed with HTTP code: " + responseCode);
                }
            } catch (IOException e) {
                throw new RuntimeException("Request failed: " + e.getMessage(), e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        });
    }

    /**
     * Helper method to create a connection.
     */
    private HttpURLConnection createConnection(String fullUrl, String method, String bearerToken, Map<String, String> options) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(fullUrl).openConnection();
        connection.setRequestMethod(method);
        connection.setConnectTimeout(timeoutMs);
        connection.setReadTimeout(timeoutMs);

        if (bearerToken != null && !bearerToken.isEmpty()) {
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
        }

        if (options != null) {
            for (Map.Entry<String, String> entry : options.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        return connection;
    }

    private HttpURLConnection createConnection(String fullUrl, String method, String basicAuthUsername, String basicAuthPassword) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(fullUrl).openConnection();
        connection.setRequestMethod(method);
        connection.setConnectTimeout(timeoutMs);
        connection.setReadTimeout(timeoutMs);
        connection.setDoOutput(true);

        String basicAuth = "Basic " + Base64.getEncoder().encodeToString((basicAuthUsername + ":" + basicAuthPassword).getBytes());
        connection.setRequestProperty("Authorization", basicAuth);
        connection.setRequestProperty("Content-Type", "application/json");

        return connection;
    }

    /**
     * Helper method to write JSON body to request.
     */
    private void writeRequestBody(HttpURLConnection connection, String bodyJson) throws IOException {
        try (OutputStream os = connection.getOutputStream()) {
            os.write(bodyJson.getBytes("utf-8"));
        }
    }

    /**
     * Build full URL with query parameters.
     */
    private String buildFullUrl(String basePath, String urlPath, Map<String, Object> queryParams) {
        StringBuilder fullUrl = new StringBuilder(basePath + urlPath);
        if (queryParams != null && !queryParams.isEmpty()) {
            fullUrl.append("?");
            queryParams.forEach((key, value) -> fullUrl.append(key).append("=").append(value).append("&"));
            fullUrl.deleteCharAt(fullUrl.length() - 1); // Remove the trailing "&"
        }
        return fullUrl.toString();
    }

    /**
     * Read the response from the BufferedReader.
     */
    private String readResponse(BufferedReader reader) throws IOException {
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        return response.toString();
    }

    /**
     * Deserialize JSON response to ApiClanDescList.
     */
    private ApiClanDescList deserializeResponse(String json) {
        return new Gson().fromJson(json, ApiClanDescList.class);
    }

    /**
     * Build the body JSON for authentication.
     */
    private String buildBodyJson(ApiAuthenticateRequest body) {
        return new Gson().toJson(body);
    }

    /**
     * Parse JSON response to ApiSession.
     */
    private ApiSession parseApiSession(String json) {
        return new Gson().fromJson(json, ApiSession.class);
    }
}
