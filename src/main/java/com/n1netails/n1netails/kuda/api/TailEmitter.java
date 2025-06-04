package com.n1netails.n1netails.kuda.api;

import com.n1netails.n1netails.kuda.model.TailModel;
import com.n1netails.n1netails.kuda.service.TailService;

import java.util.HashMap;
import java.util.Map;

/**
 * Tail Emitter
 * @author shahid foy
 */
public class TailEmitter {

    private final String level;
    private final String message;
    private String description;
    private String details;
    private String type;
    private final Map<String, String> tags = new HashMap<>();

    /**
     * Tail Emitter Constructor
     * @param level level
     * @param message message
     */
    public TailEmitter(String level, String message) {
        this.level = level;
        this.message = message;
    }

    /**
     * Tail Description
     * @param description tail description
     * @return tail emitter
     */
    public TailEmitter description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Tail Details
     * @param details tail details
     * @return tail emitter
     */
    public TailEmitter details(String details) {
        this.details = details;
        return this;
    }

    /**
     * Tail Type
     * @param type tail type
     * @return tail emitter
     */
    public TailEmitter type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Tail with Tag
     * @param key tail key
     * @param value tail value
     * @return tail emitter
     */
    public TailEmitter withTag(String key, String value) {
        this.tags.put(key, value);
        return this;
    }

    /**
     * Tail with Tags
     * @param tags tail tags
     * @return tail emitter
     */
    public TailEmitter withTags(Map<String, String> tags) {
        this.tags.putAll(tags);
        return this;
    }

    /**
     * Send Tail Request
     */
    public void send() {
        TailService.getInstance().send(new TailModel(level, message, description, tags, details, type));
    }
}
