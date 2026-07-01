package main.java.com.pf.constants;

public final class ApplicationConstants {

    private ApplicationConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // Transaction Type
    public static final String PORTFOLIO_STATUS_INVALID_INPUT= "INVALID_INPUT";
    public static final String PORTFOLIO_STATUS_REVIEW_REQUIRED="REVIEW_REQUIRED";
    public static final String PORTFOLIO_STATUS_VALID="VALID";

}
