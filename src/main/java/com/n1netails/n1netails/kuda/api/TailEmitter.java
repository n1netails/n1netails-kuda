package com.n1netails.n1netails.kuda.api;

import com.n1netails.n1netails.kuda.model.TailModel;
import com.n1netails.n1netails.kuda.service.TailService;

import java.util.HashMap;
import java.util.Map;

public class TailEmitter {

    private final String level;
    private final String message;
    private String description;
    private String details;
    private String type;
    private final Map<String, String> tags = new HashMap<>();

    public TailEmitter(String level, String message) {
        this.level = level;
        this.message = message;
    }

    public TailEmitter description(String description) {
        this.description = description;
        return this;
    }

    public TailEmitter details(String details) {
        this.details = details;
        return this;
    }

    public TailEmitter type(String type) {
        this.type = type;
        return this;
    }

    public TailEmitter withTag(String key, String value) {
        this.tags.put(key, value);
        return this;
    }

    public TailEmitter withTags(Map<String, String> tags) {
        this.tags.putAll(tags);
        return this;
    }

    public void send() {
        TailService.getInstance().send(new TailModel(level, message, description, tags, details, type));
    }
}
