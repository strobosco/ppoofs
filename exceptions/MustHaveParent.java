package exceptions;

/**
 * This exception is shown when a user tries placing a folder or file
 * outside of its respective container. Every folder must be inside a drive
 * or a parent folder and a file must be inside a parent drive or folder.
 */

public class MustHaveParent extends IllegalOperationException {
  public MustHaveParent(String name) {
    super("Entity must have parent: " + name);
  }
}
