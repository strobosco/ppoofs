package entities;

import exceptions.AlreadyExistsException;
import exceptions.FileAlreadyExists;
import exceptions.MustHaveParent;
import exceptions.ParentIsNotContainerException;

/**
 * The main difference between the ZipFile class and its
 * counterparts is the this classes size corresponds to a 
 * "compressed" version of its final size, 1/2 of it.
 */

public class ZipFile extends Entity {
  public ZipFile(String name, Entity parent) throws AlreadyExistsException, FileAlreadyExists, MustHaveParent, ParentIsNotContainerException {
    super(name, "ZIP_FILE", parent);
  }

  public String getPath() {
    return getParent().getPath() + getPath();
  }

  public int getSize() {
    int size = 0;
    for(Entity e : getChildren().values()) {
      size += e.getSize();
    }
    return size / 2;
  }
}
