package exceptions;

/**
 * This exception is showed in the event a user tries creating 
 * a file, in the same directory, with a pre-existing name
 */

public class FileAlreadyExists extends AlreadyExistsException {
  public FileAlreadyExists(String name) {
    super("File already exists: " + name);
  }
}
