package exceptions;

/**
 * This exception will be shown in the event a user tries to 
 * create an entity that is already present. Specifically:
 * 
 * <b>
 *    Two entities with the same parent cannot have the same name. 
 *    Similarly, two drives cannot have the same name.
 * </b>
 */

public class AlreadyExistsException extends IllegalOperationException {
  public AlreadyExistsException(String name) {
    super("Entity already exists: " + name);
  }
}
