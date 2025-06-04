package com.n1netails.n1netails.kuda.api;

import com.n1netails.n1netails.kuda.TailLevel;
import com.n1netails.n1netails.kuda.service.ExceptionReporter;

/**
 * Tail
 * @author shahid foy
 */
public class Tail {

    private static ExceptionReporter reporter;

    /**
     * Tail Constructor
     */
    public Tail() {}

    /**
     * Tail Info
     * @param message info message
     * @return tail emitter
     */
    public static TailEmitter info(String message) {
        return new TailEmitter(TailLevel.INFO.name(), message);
    }

    /**
     * Tail Success
     * @param message success message
     * @return tail emitter
     */
    public static TailEmitter success(String message) {
        return new TailEmitter(TailLevel.SUCCESS.name(), message);
    }

    /**
     * Tail Warning
     * @param message warning message
     * @return tail emitter
     */
    public static TailEmitter warn(String message) {
        return new TailEmitter(TailLevel.WARN.name(), message);
    }

    /**
     * Tail Error
     * @param message error message
     * @return tail emitter
     */
    public static TailEmitter error(String message) {
        return new TailEmitter(TailLevel.ERROR.name(), message);
    }

    /**
     * Tail Critical
     * @param message critical message
     * @return tail emitter
     */
    public static TailEmitter critical(String message) {
        return new TailEmitter(TailLevel.CRITICAL.name(), message);
    }

    /**
     * Tail Kuda (Custom Tail Level)
     * @param level custom level
     * @param message kuda message
     * @return tail emitter
     */
    public static TailEmitter kuda(String level, String message) {
        return new TailEmitter(level, message);
    }

    /**
     * Tail Report Exception
     * @param throwable throwable exception
     */
    public static void report(Throwable throwable) {
        reporter = new ExceptionReporter();
        reporter.reportException(throwable, Thread.currentThread().getName());
    }

    /**
     * Tail Report Exception with Custom Level
     * @param level level
     * @param throwable throwable exception
     */
    public static void report(String level, Throwable throwable) {
        reporter = new ExceptionReporter();
        reporter.reportException(level, throwable, Thread.currentThread().getName());
    }
}
