package exceptions;

/**
 * This exception occurs when an entity is not found
 */

import java.io.IOException;

public class EntityNotFoundException extends IOException {
  public EntityNotFoundException(String name) {
    super("Entity not found: " + name);
  }
}