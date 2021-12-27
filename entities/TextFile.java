package entities;

import exceptions.AlreadyExistsException;
import exceptions.FileAlreadyExists;
import exceptions.MustHaveParent;

/**
 * The TextFile class is the only class that possesses an extra
 * attribute.
 * @attr content -> contains the text content of the file.
 * 
 * If one wanted to improved this class the attribute would be changed
 * as the current String type concatenates all content that is added and
 * makes removing content virtually impossible once a sufficient amount is added.
 */

public class TextFile extends Entity {

  private String content;

  public TextFile(String name, Entity parent) throws AlreadyExistsException, FileAlreadyExists, MustHaveParent {
    super(name, "TEXT_FILE", parent);
  }

  public void setContent(String c) {
    this.content = c;
  }

  public String getContent() {
    return content;
  }

  public int getSize() {
    return content.length();
  }

  public String getPath() {
    return getParent().getPath() + getPath();
  }

  public boolean isContainer() {
    return false;
  }

}
