package kz.edu.astanait.mydulib.core.exception;

public class MyDuApiException extends RuntimeException {
    public MyDuApiException (String message) {
        super(message);
    }

    public MyDuApiException(String message, Throwable e) {
        super(message, e);
    }
}
