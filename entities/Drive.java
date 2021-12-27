package entities;

import exceptions.AlreadyExistsException;
import exceptions.FileAlreadyExists;
import exceptions.MustHaveParent;

/**
 * The Drive class contains the code necessary to make an Entity
 * a drive. It's {@code mustBeContained} method is overriden to
 * false as a drive is not contained in any other entity.
 * This may seem controversial but for convenience it was decided
 * that a the {@code c#} drive would dominate the entity hierarchy. Thus,
 * it is assigned an identifier of "#" and not ":". ALL OTHER ENTITIES DERIVE
 * FROM THE C# DRIVE.
 */

public class Drive extends Entity {
  public Drive(String name, Entity parent) throws AlreadyExistsException, FileAlreadyExists, MustHaveParent {
    super(name, "DRIVE", parent);
  }

  public boolean mustBeContained() { 
    return false;
  }

  public String getPath() {
    if (getParent() == null) {
      return getName() + (getName().equals("c") ? "#" : ":");
    } else {
      return getParent().getPath() + getName() + (getName().equals("c") ? "#" : ":");
    }
  }

  public int getSize() {
    int size = 0;
    for(Entity e : getChildren().values()) {
      size += e.getSize();
    }
    return size;
  }
}
