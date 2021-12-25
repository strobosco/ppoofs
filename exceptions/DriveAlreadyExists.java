package exceptions;

/**
 * This exception will be showed in the event a user tries
 * creating a drive with the same one as an existing one.
 */

public class DriveAlreadyExists extends AlreadyExistsException {
  public DriveAlreadyExists(String name) {
    super("Drive already exists: " + name);
  }
}
