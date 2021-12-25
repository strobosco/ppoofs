package exceptions;

/**
 * This exception occurs when a user tries creating a file type that
 * isn't DRIVE, FOLDER, TEXT_FILE or ZIP_FILE.
 */

import java.io.IOException;

public class UnsupportedFileTypeException extends IOException {
  public UnsupportedFileTypeException(String name) {
    super("Illegal: cannot create a file of type: " + name);
  }
}