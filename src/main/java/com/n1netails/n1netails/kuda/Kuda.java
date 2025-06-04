package com.n1netails.n1netails.kuda;

import com.n1netails.n1netails.kuda.service.ExceptionReporter;

/**
 * Kuda Default Uncaught Exception Handler
 * @author shahid foy
 */
public class Kuda {

    private static ExceptionReporter reporter;

    /**
     * Kuda Constructor
     */
    private Kuda() {}

    /**
     * Initialize Exception Handler
     */
    public static void init() {

        reporter = new ExceptionReporter();
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            if (reporter != null) {
                reporter.reportException(throwable, Thread.currentThread().getName());
            }
            throwable.printStackTrace();
        });
    }
}
