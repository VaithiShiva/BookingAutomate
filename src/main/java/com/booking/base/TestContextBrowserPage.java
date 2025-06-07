package com.booking.base;

public class TestContextBrowserPage {

    private static ThreadLocal<String> parentWindow = new ThreadLocal<>();

    public static void setBrowser(String value) {
        parentWindow.set(value);
    }

    public static String getBrowser() {
        return parentWindow.get();
    }


}


