package com.n1netails.n1netails.kuda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.n1netails.n1netails.kuda.exception.TailHttpException;
import com.n1netails.n1netails.kuda.internal.TailConfig;
import com.n1netails.n1netails.kuda.model.TailModel;
import com.n1netails.n1netails.kuda.model.TailRequest;
import lombok.Getter;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Tail Service
 * @author shahid foy
 */
public class TailService {

    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson

    @Getter
    private static final TailService instance = new TailService();

    /**
     * Tail Service Constructor
     */
    private TailService() {}

    /**
     * Send tail alert to N1netails Api
     * @param alert tail alert
     */
    public void send(TailModel alert) {
        CompletableFuture.runAsync(() -> {
            String message = alert.getMessage();
            String level = alert.getLevel();

            Optional<String> apiUrlOpt = TailConfig.getApiUrl();
            Optional<String> tokenOpt = TailConfig.getToken();

            // Check to see if tail is configured if not log out one time warning about missing configurations.
            boolean configured = TailConfig.isConfigured();
            // If configs are not set up then default to logging out message
            if (!apiUrlOpt.isPresent() || !tokenOpt.isPresent()) {
                // TAIL LOG
                System.out.println("[TAIL][" + level + "] " + message);
                return;
            }

            // TAIL LOG
            System.out.println("[TAIL][" + level + "] " + message);
            // Proceed with real sending logic
            String apiUrl = apiUrlOpt.get();
            String token = tokenOpt.get();

            // Send post request
            try {
                TailRequest tailRequest = TailRequest.builder()
                        .title(message)
                        .description(alert.getDescription())
                        .details(alert.getDetails())
                        .level(alert.getLevel())
                        .type(alert.getType())
                        .metadata(alert.getTags())
                        .build();

                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("N1ne-Token", token);

                String payload;
                try {
                    objectMapper.registerModule(new JavaTimeModule());
                    payload = objectMapper.writeValueAsString(tailRequest);
                } catch (Exception e) {
                    throw new TailHttpException("Failed to serialize tail request", 400);
                }
                try (OutputStream os = connection.getOutputStream()) {
                    os.write(payload.getBytes());
                    os.flush();
                }

                int responseCode = connection.getResponseCode();
                if (responseCode != 204 && responseCode != 200) {
                    throw new TailHttpException("N1netails API responded with HTTP status: " + responseCode, responseCode);
                }
            } catch (Exception e) {
                System.out.println("There was an issue with sending the tail to the n1netails-api: " + e.getMessage());
            }
        });
    }
}
