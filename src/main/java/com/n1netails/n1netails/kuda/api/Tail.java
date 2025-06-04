package com.n1netails.n1netails.kuda.api;

import com.n1netails.n1netails.kuda.TailLevel;
import com.n1netails.n1netails.kuda.service.ExceptionReporter;

public class Tail {

    private static ExceptionReporter reporter;

    /////////////////
    // INFO
    ////////////////
    public static TailEmitter info(String message) {
        return new TailEmitter(TailLevel.INFO.name(), message);
    }

    /////////////////
    // SUCCESS
    /////////////////
    public static TailEmitter success(String message) {
        return new TailEmitter(TailLevel.SUCCESS.name(), message);
    }

    /////////////////
    // WARN
    /////////////////
    public static TailEmitter warn(String message) {
        return new TailEmitter(TailLevel.WARN.name(), message);
    }

    /////////////////
    // ERROR
    /////////////////
    public static TailEmitter error(String message) {
        return new TailEmitter(TailLevel.ERROR.name(), message);
    }

    /////////////////
    // CRITICAL
    /////////////////
    public static TailEmitter critical(String message) {
        return new TailEmitter(TailLevel.CRITICAL.name(), message);
    }

    /////////////////
    // KUDA
    // Send message with custom level
    /////////////////
    public static TailEmitter kuda(String level, String message) {
        return new TailEmitter(level, message);
    }

    /////////////////
    // Exception reporter
    /////////////////
    public static void report(Throwable throwable) {
        reporter = new ExceptionReporter();
        reporter.reportException(throwable, Thread.currentThread().getName());
    }

    /////////////////
    // Exception reporter
    /////////////////
    public static void report(String level, Throwable throwable) {
        reporter = new ExceptionReporter();
        reporter.reportException(level, throwable, Thread.currentThread().getName());
    }
}
