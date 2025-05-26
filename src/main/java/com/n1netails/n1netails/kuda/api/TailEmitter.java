package com.n1netails.n1netails.kuda.api;

import com.n1netails.n1netails.kuda.model.TailModel;
import com.n1netails.n1netails.kuda.service.TailService;

import java.util.HashMap;
import java.util.Map;

public class TailEmitter {

    private final String level;
    private final String message;
    private String details;
    private String type;
    private final Map<String, String> tags = new HashMap<>();

    public TailEmitter(String level, String message) {
        this.level = level;
        this.message = message;
    }

    public TailEmitter(String level, String message, String details) {
        this.level = level;
        this.message = message;
        this.details = details;
    }

    public TailEmitter(String level, String message, String details, String type) {
        this.level = level;
        this.message = message;
        this.details = details;
        this.type = type;
    }

    public TailEmitter withTag(String key, String value) {
        tags.put(key, value);
        return this;
    }

    public TailEmitter withTags(Map<String, String> tags) {
        this.tags.putAll(tags);
        return this;
    }

    public void send() {
        TailService.getInstance().send(new TailModel(level, message, tags, details, type));
    }
}
