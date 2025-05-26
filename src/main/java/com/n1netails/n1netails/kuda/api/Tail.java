package com.n1netails.n1netails.kuda.api;

// TODO SET UP CONSTANTS OR ENUMS FOR THE DIFFERENT TAIL LEVELS
public class Tail {

    public static TailEmitter info(String message) {
        return new TailEmitter("INFO", message);
    }
    public static TailEmitter success(String message) {
        return new TailEmitter("SUCCESS", message);
    }
    public static TailEmitter warn(String message) {
        return new TailEmitter("WARN", message);
    }
    public static TailEmitter error(String message) {
        return new TailEmitter("ERROR", message);
    }
    public static TailEmitter critical(String message) {
        return new TailEmitter("CRITICAL", message);
    }
}
