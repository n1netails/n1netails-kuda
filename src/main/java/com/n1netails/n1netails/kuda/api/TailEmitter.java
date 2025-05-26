package com.n1netails.n1netails.kuda.api;

import com.n1netails.n1netails.kuda.model.TailModel;
import com.n1netails.n1netails.kuda.service.TailService;

import java.util.HashMap;
import java.util.Map;

// TODO MATCH THIS WITH N1NETAILS CORE TAIL DOMAIN
public class TailEmitter {

    private final String level;
    private final String message;
    private final Map<String, String> tags = new HashMap<>();

    public TailEmitter(String level, String message) {
        this.level = level;
        this.message = message;
    }

    public TailEmitter withTag(String key, String value) {
        tags.put(key, value);
        return this;
    }

    public void send() {
        TailService.getInstance().send(new TailModel(level, message, tags));
    }
}
