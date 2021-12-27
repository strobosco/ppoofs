package main;

import entities.Drive;
import entities.Entity;
import entities.Folder;
import entities.TextFile;
import entities.ZipFile;
import exceptions.AlreadyExistsException;
import exceptions.DriveAlreadyExists;
import exceptions.EntityNotFoundException;
import exceptions.FileAlreadyExists;
import exceptions.FolderAlreadyExistsException;
import exceptions.MustHaveParent;
import exceptions.ParentIsNotContainerException;
import exceptions.UnsupportedFileTypeException;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileSystem {

  private Drive cDrive;

  public FileSystem() throws AlreadyExistsException, MustHaveParent, ParentIsNotContainerException {
    cDrive = new Drive("c", null);
  }

  public Drive getCDrive() {
    return cDrive;
  }

  public void create(String type, String name, String path) throws AlreadyExistsException, FileAlreadyExists, UnsupportedFileTypeException, MustHaveParent, EntityNotFoundException, ParentIsNotContainerException {
    switch (type) {
      case "DRIVE":
        if(cDrive.getChildren().containsKey(name)) {
          throw new DriveAlreadyExists(name);
        }
        new Drive(name, cDrive);  
        break;
      case "FOLDER":
      ArrayList<String> folderParent = new ArrayList<String>(Arrays.asList(path.split("/|#|:")));
        if(findParent(cDrive, folderParent).getChildren().containsKey(name)) {
          throw new FolderAlreadyExistsException(name);
        }
        new Folder(name, findParent(cDrive, folderParent));
        break;
      case "TEXT_FILE":
        ArrayList<String> textParent = new ArrayList<String>(Arrays.asList(path.split("/|#|:")));
        if(findParent(cDrive, textParent).getChildren().containsKey(name)) {
          throw new FileAlreadyExists(name);
        }
        new TextFile(name, findParent(cDrive, textParent), "");
        break;
      case "ZIP_FILE":
        ArrayList<String> zipParent = new ArrayList<String>(Arrays.asList(path.split("/|#|:")));
        if(findParent(cDrive, zipParent).getChildren().containsKey(name)) {
          throw new FileAlreadyExists(name);
        }
        new ZipFile(name, findParent(cDrive, zipParent));
        break;
      default:
        throw new UnsupportedFileTypeException("Entity type not supported!");
    }

  }

  public void delete(String name, String path) throws EntityNotFoundException {
    ArrayList<String> split = new ArrayList<String>(Arrays.asList(path.split("/|#|:")));
    Entity child = findParent(cDrive, split);
    child.getParent().deleteChild(name);
  }

  public void move(String origin, String destination) throws MustHaveParent, EntityNotFoundException, ParentIsNotContainerException  {
    ArrayList<String> split = new ArrayList<String>(Arrays.asList(origin.split("/|#|:")));
    Entity e = findParent(cDrive, split);
    ArrayList<String> newParentPath = new ArrayList<String>(Arrays.asList(destination.split("/|#|:")));
    Entity newParent = findParent(cDrive, newParentPath);
    
    e.getParent().deleteChild(e.getName());

    e.setParent(newParent);
    
  }

  public void writeToFile(String path, String content) throws EntityNotFoundException {
    ArrayList<String> p = new ArrayList<String>(Arrays.asList(path.split("/|#|:")));
    TextFile e = (TextFile) findParent(cDrive, p);
    e.setContent(content);
  }

  public Entity findParent(Entity parent, ArrayList<String> split) throws EntityNotFoundException {
    if(split.get(0).equals("c")) {
      split.remove(0);
    }
    Entity child = parent.getChildren().get(split.get(0));
    if(child == null) {
      throw new EntityNotFoundException(split.get(split.size()));
    }
    split.remove(0);
    if(split.size() < 1) {
      return child;
    }    
    return findParent(child, split);
  }
}