package exceptions;

/**
 * This exception is the top-level exception of user-provoked
 * exceptions that occure when:
 * - creating an entity
 * - moving an entity
 */

import java.io.IOException;

public class IllegalOperationException extends IOException {
    public IllegalOperationException(String name) {
        super("Illegal operation:  " + name);
    }
}