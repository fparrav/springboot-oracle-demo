package cl.voxcom.demo.error;

/**
 * Created by Felipe Parra V. on July, 2019
 * Email : fparrav@icloud.com
 * Github: fparrav
 */
public class ApiNotFoundException extends RuntimeException{

    public ApiNotFoundException(String message) {
        super(message);
    }

    public ApiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiNotFoundException(Throwable cause) {
        super(cause);
    }
}