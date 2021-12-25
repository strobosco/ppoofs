package exceptions;

/**
 * This drive will be shown in the event a user tries to
 * place a drive inside an entity. As previously mentioned,
 * all drives created are children of the C: drive.
 */

public class DriveInEntityException extends IllegalOperationException {
  public DriveInEntityException(String name) {
    super("Cannot place drive in entity: " + name);
  }
}