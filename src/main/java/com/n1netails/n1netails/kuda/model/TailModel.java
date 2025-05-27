package com.n1netails.n1netails.kuda.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class TailModel {
    private final String level;
    private final String message;
    private final String description;
    private final String details;
    private final String type;
    private final Map<String, String> tags;

    public TailModel(String level, String message, String description, Map<String, String> tags, String details, String type) {
        this.level = level;
        this.message = message;
        this.description = description;
        this.tags = tags;
        this.details = details;
        this.type = type;
    }
}
