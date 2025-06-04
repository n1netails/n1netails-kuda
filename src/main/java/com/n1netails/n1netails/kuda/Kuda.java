package com.n1netails.n1netails.kuda;

import com.n1netails.n1netails.kuda.service.ExceptionReporter;

public class Kuda {

    private static ExceptionReporter reporter;

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
