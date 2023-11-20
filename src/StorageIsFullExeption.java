public class StorageIsFullExeption extends RuntimeException {
    public StorageIsFullExeption() {
    }

    public StorageIsFullExeption(String message) {
        super(message);
    }

    public StorageIsFullExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageIsFullExeption(Throwable cause) {
        super(cause);
    }

    public StorageIsFullExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
