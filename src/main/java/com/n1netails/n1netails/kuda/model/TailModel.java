package com.n1netails.n1netails.kuda.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// TODO MATCH THIS WITH N1NETAILS CORE TAIL DOMAIN
@Getter
public class TailModel {
    private final String level;
    private final String message;
    private final Map<String, String> tags;

    public TailModel(String level, String message, Map<String, String> tags) {
        this.level = level;
        this.message = message;
        this.tags = tags;
    }

}
