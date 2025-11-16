package com.n1netails.n1netails.kuda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.n1netails.n1netails.kuda.internal.TailConfig;
import com.n1netails.n1netails.kuda.model.TailModel;
import com.n1netails.n1netails.kuda.model.TailRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Tail Service
 * @author shahid foy
 */
@Slf4j
public class TailService {

    private final ObjectMapper objectMapper;
    private final OkHttpClient client;

    @Getter
    private static final TailService instance = new TailService();

    /**
     * Tail Service Constructor
     */
    private TailService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS) // connection timeout
                .writeTimeout(60, TimeUnit.SECONDS)   // write timeout
                .readTimeout(60, TimeUnit.SECONDS)    // read timeout
                .callTimeout(60, TimeUnit.SECONDS)    // total call timeout
                .build();
    }

    /**
     * Send tail alert to N1netails Api
     * @param alert tail alert
     */
    public void send(TailModel alert) {
        String message = alert.getMessage();
        String level = alert.getLevel();

        log.info("[TAIL][{}] {}", level, message);

        Optional<String> apiUrlOpt = TailConfig.getApiUrl();
        Optional<String> tokenOpt = TailConfig.getToken();

        // Check to see if tail is configured if not log out one time warning about missing configurations.
        TailConfig.isConfigured();
        // If configs are not set up then default to logging out message
        if (!apiUrlOpt.isPresent() || !tokenOpt.isPresent()) {
            log.warn("TailService is not configured. Skipping sending alert.");
            return;
        }
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

            RequestBody body = RequestBody.create(
                    objectMapper.writeValueAsString(tailRequest),
                    MediaType.get("application/json")
            );

            Request request = new Request.Builder()
                    .url(apiUrl)
                    .header("N1ne-Token", token)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    log.error("Failed sending tail alert to N1netails API", e);
                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        log.error("N1netails API responded with HTTP status: {}", response.code());
                    }
                    response.close();
                }
            });
        } catch (Exception e) {
            log.error("Exception while preparing tail request", e);
        }
    }
}
