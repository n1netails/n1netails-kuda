package com.n1netails.n1netails.kuda.service;

import com.n1netails.n1netails.kuda.internal.TailConfig;
import com.n1netails.n1netails.kuda.model.TailModel;
import lombok.Getter;

import java.util.Optional;

public class TailService {

    @Getter
    private static final TailService instance = new TailService();

    private TailService() {}

    public void send(TailModel alert) {

        String message = alert.getMessage();
        String level = alert.getLevel();

        Optional<String> apiUrlOpt = TailConfig.getApiUrl();
        Optional<String> tokenOpt = TailConfig.getToken();

        // Check to see if tail is configured if not log out one time warning about missing configurations.
        boolean configured = TailConfig.isConfigured();

        if (!apiUrlOpt.isPresent() || !tokenOpt.isPresent()) {
            System.out.println("[TAIL][" + level + "] " + message);
            return;
        }

        System.out.println("[TAIL][" + level + "] " + message);
        // Proceed with real sending logic
        String apiUrl = apiUrlOpt.get();
        String token = tokenOpt.get();
        // send HTTP POST ...
        System.out.println("Sending Tail to api " + apiUrl + " Message: " + alert.getMessage());

    }
}
