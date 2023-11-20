public class ElementNotFoundExeption extends RuntimeException {
    public ElementNotFoundExeption() {
    }

    public ElementNotFoundExeption(String message) {
        super(message);
    }

    public ElementNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotFoundExeption(Throwable cause) {
        super(cause);
    }

    public ElementNotFoundExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
