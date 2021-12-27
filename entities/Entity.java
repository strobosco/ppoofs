package entities;

import java.util.HashMap;

import exceptions.AlreadyExistsException;
import exceptions.MustHaveParent;
import exceptions.ParentIsNotContainerException;

/**
   * The entity class is the top-level class in this OOP implementation of a
   * filesystem. It possesses 6 attributes
   * @attr name -> the name assigned to the entity, in the case of drives ":"
   *               is automatically appended, "/" to folders and zip files
   * @attr path -> the absolute path at which the entity is located
   * @attr size -> the size of the entity
   * @attr type -> the type of entity, may be DRIVE, FOLDER, TEXT_FILE or ZIP_FILE
   * @attr chidlren -> a hashmap containing the entities children and their absolute path
   */

public abstract class Entity {
  public static final String SEPARATOR = "/";

  private String name;
  private String path;
  private int size;
  private String type;

  private Entity parent;
  private HashMap<String, Entity> children = new HashMap<String, Entity>();

  public Entity(String name, String type, Entity parent) throws AlreadyExistsException, MustHaveParent, ParentIsNotContainerException {
    setName(name);
    setType(type);
    if(parent != null) {
      setParent(parent);
    }
    setPath();
  }

  /**
   * Every attribute presents a setter and a getter.
   */

  public String getType() {
    return type;
  } 

  private void setType(String t) {
    this.type = t;
  }

  public String getName() {
    return name;
  }

  public void setName(String n) {
    if(n != null) {
      this.name = n;
    }
  }

  public Entity getParent() {
    return parent;
  }

  public void setParent(Entity p) throws MustHaveParent, ParentIsNotContainerException {
    if(p == null && mustBeContained()) {
      throw new MustHaveParent(getName());
    } 
    if(!p.isContainer()) {
      throw new ParentIsNotContainerException(p.getName());
    }
    p.getChildren().put(getName(), this);
    this.parent = p;
  }

  public HashMap<String, Entity> getChildren() {
    return children;
  }

  // Adding children is done through the {@code .put()} HashMap method

  public void deleteChild(String child) {
    children.remove(child);
  }

  /**
   * {@code mustBeContained} and {@code isContainer} are methods that help
   * in controlling that the right entity type has access to the correct
   * permissions, in this case,
   * 
   * - is it a container?
   * - can it be contained?
   */

  public boolean mustBeContained() {
    return true;
  }

  public boolean isContainer() {
    return true;
  }

  public abstract int getSize();

  public String getPath() {
    if (parent == null) {
      return getName();
    } else {
      return parent.getPath() + getName();
    }
  }

  public void setPath() {
    this.path = getPath();
  }
}
