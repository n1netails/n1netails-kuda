package com.n1netails.n1netails.kuda.service;

import com.n1netails.n1netails.kuda.TailLevel;
import com.n1netails.n1netails.kuda.internal.TailConfig;
import com.n1netails.n1netails.kuda.model.TailModel;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.Instant;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Exception Reporter
 * @author shahid foy
 */
public class ExceptionReporter {

    /**
     * Exception Reporter Constructor
     */
    public ExceptionReporter() {}

    /**
     * Report Exception
     * @param throwable throwable exception
     * @param threadName thread name
     */
    public void reportException(Throwable throwable, String threadName) {
        this.reportException(TailLevel.ERROR.name(), throwable, threadName);
    }

    /**
     * Report Exception
     * @param level tail level
     * @param throwable throwable exception
     * @param threadName thread name
     */
    public void reportException(String level, Throwable throwable, String threadName) {
        try {
            StringWriter sw = new StringWriter();
            throwable.printStackTrace(new PrintWriter(sw));
            String stackTrace = sw.toString();

            Map<String, String> kudatags = new HashMap<>();
            if (throwable.toString() != null)
                kudatags.put("throwable", throwable.toString());
            if (throwable.getLocalizedMessage() != null)
                kudatags.put("localized-message", throwable.getLocalizedMessage());
            kudatags.put("exception-type", throwable.getClass().getName());
            kudatags.put("timestamp", Instant.now().toString());
            kudatags.put("thread-name", threadName);
            kudatags.put("thread-id", String.valueOf(Thread.currentThread().getId()));

            if (TailConfig.isVerbose()) {
                kudatags.put("thread-state", Thread.currentThread().getState().toString());
                if (throwable.getCause() != null)
                    kudatags.put("cause", throwable.getCause().toString());
                kudatags.put("java-version", System.getProperty("java.version"));
                kudatags.put("host", getHostName());
                kudatags.put("ip-local", getLocalIp());
                kudatags.put("os", System.getProperty("os.name"));
                kudatags.put("os-version", System.getProperty("os.version"));
                kudatags.put("arch", System.getProperty("os.arch"));
            }

            TailModel alert = new TailModel(
                    level,
                    getHostName() + " | " + throwable,
                    throwable.getMessage(),
                    kudatags,
                    stackTrace,
                    "SYSTEM_ALERT");
            TailService.getInstance().send(alert);
        } catch (Exception e) {
            System.out.println("There was an issue with reporting the exception: " + e.getMessage());
        }
    }

    private String getHostName() {
        try {
            return java.net.InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "unknown-host";
        }
    }

    private String getLocalIp() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (!iface.isUp() || iface.isLoopback()) continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet4Address && addr.isSiteLocalAddress()) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            return "unknown-local-ip";
        }
        return "unknown-local-ip";
    }
}
