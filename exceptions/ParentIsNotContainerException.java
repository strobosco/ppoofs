package exceptions;

public class ParentIsNotContainerException extends IllegalOperationException {
  public ParentIsNotContainerException(String name) {
    super("Parent is not container: " + name);
  }
}
