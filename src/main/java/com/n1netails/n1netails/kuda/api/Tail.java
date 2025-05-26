package com.n1netails.n1netails.kuda.api;

import com.n1netails.n1netails.kuda.TailLevel;

public class Tail {

    /////////////////
    // INFO
    ////////////////
    public static TailEmitter info(String message) {
        return new TailEmitter(TailLevel.INFO.name(), message);
    }

    public static TailEmitter info(String message, String details) {
        return new TailEmitter(TailLevel.INFO.name(), message, details);
    }

    public static TailEmitter info(String message, String details, String type) {
        return new TailEmitter(TailLevel.INFO.name(), message, details, type);
    }

    /////////////////
    // SUCCESS
    /////////////////
    public static TailEmitter success(String message) {
        return new TailEmitter(TailLevel.SUCCESS.name(), message);
    }

    public static TailEmitter success(String message, String details) {
        return new TailEmitter(TailLevel.SUCCESS.name(), message, details);
    }

    public static TailEmitter success(String message, String details, String type) {
        return new TailEmitter(TailLevel.SUCCESS.name(), message, details, type);
    }

    /////////////////
    // WARN
    /////////////////
    public static TailEmitter warn(String message) {
        return new TailEmitter(TailLevel.WARN.name(), message);
    }

    public static TailEmitter warn(String message, String details) {
        return new TailEmitter(TailLevel.WARN.name(), message, details);
    }

    public static TailEmitter warn(String message, String details, String type) {
        return new TailEmitter(TailLevel.WARN.name(), message, details, type);
    }

    /////////////////
    // ERROR
    /////////////////
    public static TailEmitter error(String message) {
        return new TailEmitter(TailLevel.ERROR.name(), message);
    }

    public static TailEmitter error(String message, String details) {
        return new TailEmitter(TailLevel.ERROR.name(), message, details);
    }

    public static TailEmitter error(String message, String details, String type) {
        return new TailEmitter(TailLevel.ERROR.name(), message, details, type);
    }

    /////////////////
    // CRITICAL
    /////////////////
    public static TailEmitter critical(String message) {
        return new TailEmitter(TailLevel.CRITICAL.name(), message);
    }

    public static TailEmitter critical(String message, String details) {
        return new TailEmitter(TailLevel.CRITICAL.name(), message, details);
    }

    public static TailEmitter critical(String message, String details, String type) {
        return new TailEmitter(TailLevel.CRITICAL.name(), message, details, type);
    }

    /////////////////
    // KUDA
    // Send message with custom level
    /////////////////
    public static TailEmitter kuda(String level, String message) {
        return new TailEmitter(level, message);
    }

    public static TailEmitter kuda(String level, String message, String details) {
        return new TailEmitter(level, message, details);
    }

    public static TailEmitter kuda(String level, String message, String details, String type) {
        return new TailEmitter(level, message, details, type);
    }
}
