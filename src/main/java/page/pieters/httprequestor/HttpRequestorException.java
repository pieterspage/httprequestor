package page.pieters.httprequestor;

public class HttpRequestorException extends Exception {

    public HttpRequestorException(String message) {
        super(message);
    }

    public HttpRequestorException(String message, Throwable cause) {
        super(message, cause);
    }
}
