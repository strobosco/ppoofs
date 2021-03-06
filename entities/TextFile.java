package entities;

import exceptions.AlreadyExistsException;
import exceptions.FileAlreadyExists;
import exceptions.MustHaveParent;
import exceptions.ParentIsNotContainerException;

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

  public TextFile(String name, Entity parent, String c) throws AlreadyExistsException, FileAlreadyExists, MustHaveParent, ParentIsNotContainerException {
    super(name, "TEXT_FILE", parent);
    setContent(c);
  }

  public void setContent(String c) {
    content = c;
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
