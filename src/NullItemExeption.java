public class NullItemExeption extends RuntimeException {
    public NullItemExeption() {
    }

    public NullItemExeption(String message) {
        super(message);
    }

    public NullItemExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public NullItemExeption(Throwable cause) {
        super(cause);
    }

    public NullItemExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
