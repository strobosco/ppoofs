package exceptions;

/**
 * This exception is showed in the event a user tries creating a
 * folder, in the same directory, with a pre-exsisting name.
 */

public class FolderAlreadyExistsException extends AlreadyExistsException {
  public FolderAlreadyExistsException(String name) {
    super("Folder already exists: " + name);
  }
}
