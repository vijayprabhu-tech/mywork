package com.bnk.ecomproj.constants;

public final class ApplicationConstants {

    private ApplicationConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // Transaction Type
    public static final String PROCESS_TYPE_CREDIT= "CREDIT";
    public static final String PROCESS_TYPE_DEBIT="DEBIT";
    public static final String PROCESS_TYPE_REVERSAL="REVERSAL";

}
