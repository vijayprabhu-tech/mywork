package com.bnk.ecomproj.constants;

public final class ApplicationConstants {

    private ApplicationConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // Transaction Type
    public static final String PROCESS_TYPE_CREDIT= "CREDIT";
    public static final String PROCESS_TYPE_DEBIT="DEBIT";
    public static final String PROCESS_TYPE_REVERSAL="REVERSAL";

    // Transaction Status
    public static final String PROCESS_STATUS_ = "RECEIVED";
    public static final String PROCESS_STATUS_PROCESSING = "PROCESSING";
    public static final String PROCESS_STATUS_PROCESSED = "PROCESSED";
    public static final String PROCESS_STATUS_DUPLICATE = "DUPLICATE";
    public static final String PROCESS_STATUS_FAILED = "FAILED";
    public static final String PROCESS_STATUS_RETRY_PENDING = "RETRY_PENDING";

}
