package com.runssnail.ddd.common.message;

public final class Util {

    private Util() {
    }

    public static final void report(String msg, Throwable t) {
        System.err.println(msg);
        System.err.println("Reported exception:");
        t.printStackTrace();
    }


}
