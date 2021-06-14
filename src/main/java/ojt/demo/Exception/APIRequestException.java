package ojt.demo.Exception;

/**
 * APIRequestException
 *
 * Version 1.0
 *
 * Date: 06-10-2021
 *
 * Copyright
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 06-10-2021	         LyNTT9           Create
 */
public class APIRequestException extends RuntimeException {
    public APIRequestException(String message) {
        super(message);
    }

    public APIRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
