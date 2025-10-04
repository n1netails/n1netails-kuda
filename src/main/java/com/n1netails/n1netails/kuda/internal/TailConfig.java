package com.n1netails.n1netails.kuda.internal;

import com.n1netails.n1netails.kuda.Kuda;
import com.n1netails.n1netails.kuda.exception.TailConfigException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

/**
 * Tail Config
 * @author shahid foy
 */
public class TailConfig {

    public static final String ALERT_PATH = "/ninetails/alert";

    private static String apiUrl;
    private static String token;

    // For one-time warning log
    private static boolean warningLogged = false;

    private TailConfig() {}

    /**
     * Enable Kuda as Default Uncaught Exception Handler
     */
    public static void enableExceptionHandler() {
        boolean configured = isConfigured();
        if (configured) Kuda.init();
        else System.out.println("[TAIL][WARN] unable to enable exception handler. Tail API URL and Tail Token must be configured first.");
    }

    /**
     * Checks to see if Kuda has been configured correctly
     * @return true if configured otherwise false
     */
    public static boolean isConfigured() {
        boolean configured = getApiUrl().isPresent() && getToken().isPresent();
        if (!configured && !warningLogged) {
            warningLogged = true;

            if (!getApiUrl().isPresent()) {
                System.out.println("[TAIL][WARN] Tail API URL is not configured. Call TailConfig.setApiUrl(...)");
            }
            if (!getToken().isPresent()) {
                System.out.println("[TAIL][WARN] Tail Token is not configured. Call TailConfig.setN1neToken(...)");
            }
        }
        return configured;
    }

    /**
     * Set N1netails Api Url
     * (Example: https[:]//your-n1netails-api[.]com)
     * @param url N1netails Api Url
     */
    public static void setApiUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new TailConfigException("API base URL cannot be null or empty.");
        }

        url = url.trim();
        // Remove trailing slash
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        try {
            URL parsed = new URL(url);
            // Must not have a path or query string
            if ((parsed.getPath() != null && !parsed.getPath().isEmpty() && !parsed.getPath().equals("/")) ||
                    parsed.getQuery() != null) {
                throw new TailConfigException("Base URL must not contain a path or query. Example: https[:]//your-n1netails-api[.]com");
            }
            TailConfig.apiUrl = url + ALERT_PATH;
        } catch (MalformedURLException e) {
            throw new TailConfigException("Invalid URL format: " + url, e);
        }
    }

    /**
     * Set N1netails Api Path
     * (Example: /ninetails/alert)
     * @param path N1netails Api Path
     */
    public static void setApiPath(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new TailConfigException("API path cannot be null or empty.");
        }

        path = path.trim();

        if (path.charAt(0) != '/') {
            throw new TailConfigException("The the start of the url path needs to include a forward slash '/'. Example: /ninetails/alert");
        }
        TailConfig.apiUrl = getBaseUrl(TailConfig.apiUrl);
        TailConfig.apiUrl = TailConfig.apiUrl + path;
    }

    /**
     * Set N1ne Token (Generated from N1netails Api)
     * @param token n1ne token
     */
    public static void setN1neToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new TailConfigException("Token cannot be null or empty.");
        }
        try {
            TailConfig.token = token.trim();
        } catch (Exception e) {
            throw new TailConfigException("Invalid UUID format for token: " + token, e);
        }
    }

    /**
     * Get Configured N1netails Api Url
     * @return api url
     */
    public static Optional<String> getApiUrl() {
        return Optional.ofNullable(apiUrl).filter(s -> !s.isEmpty());
    }

    /**
     * Get Configured N1ne Token (Generated from N1netails Api)
     * @return n1ne token
     */
    public static Optional<String> getToken() {
        return Optional.ofNullable(token);
    }

    private static String getBaseUrl(String fullUrl) {
        try {
            java.net.URL url = new java.net.URL(fullUrl);
            int port = url.getPort();
            String base = url.getProtocol() + "://" + url.getHost();
            if (port != -1 && port != url.getDefaultPort()) {
                base += ":" + port;
            }
            return base;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid URL: " + fullUrl, e);
        }
    }
}
