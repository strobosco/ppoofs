package entities;

import exceptions.AlreadyExistsException;
import exceptions.FolderAlreadyExistsException;
import exceptions.MustHaveParent;
import exceptions.ParentIsNotContainerException;

public class Folder extends Entity {
  public Folder(String name, Entity parent) throws AlreadyExistsException, FolderAlreadyExistsException, MustHaveParent, ParentIsNotContainerException  {
    super(name, "FOLDER", parent);
  }
  
  public String getPath() {
    return getParent().getPath() + getName() + "/";
  }

  public int getSize() {
    int size = 0;
    for(Entity e : getChildren().values()) {
      size += e.getSize();
    }
    return size;
  }
}
