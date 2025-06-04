package com.n1netails.n1netails.kuda.model;

import lombok.Getter;

import java.util.Map;

/**
 * Tail Model
 * @author shahid foy
 */
@Getter
public class TailModel {
    private final String level;
    private final String message;
    private final String description;
    private final String details;
    private final String type;
    private final Map<String, String> tags;

    /**
     * Tail Model Constructor
     * @param level tail level
     * @param message tail message
     * @param description tail description
     * @param tags tail tags
     * @param details tail details
     * @param type tail type
     */
    public TailModel(String level, String message, String description, Map<String, String> tags, String details, String type) {
        this.level = level;
        this.message = message;
        this.description = description;
        this.tags = tags;
        this.details = details;
        this.type = type;
    }
}
