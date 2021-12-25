package main;

import entities.Drive;
import entities.Entity;
import entities.Folder;
import entities.TextFile;
import entities.ZipFile;

import java.util.ArrayList;
import java.util.Arrays;

public class FileSystem {

  private Drive cDrive;

  public FileSystem() {
    cDrive = new Drive("c", null);
  }

  public Drive getCDrive() {
    return cDrive;
  }

  public void create(String type, String name, String path) {
    switch (type) {
      case "DRIVE":
        new Drive(name, cDrive);  
        break;
      case "FOLDER":
        ArrayList<String> folderParent = new ArrayList<String>(Arrays.asList(path.split("/|#|:")));
        new Folder(name, findParent(cDrive, folderParent));
        break;
      case "TEXT_FILE":
        ArrayList<String> textParent = new ArrayList<String>(Arrays.asList(path.split("/|#|:")));
        new TextFile(name, findParent(cDrive, textParent));
        break;
      case "ZIP_FILE":
        ArrayList<String> zipParent = new ArrayList<String>(Arrays.asList(path.split("/|#|:")));
        new ZipFile(name, findParent(cDrive, zipParent));
        break;
      default:
        break;
    }

  }

  public void delete(String name, String path) {
    ArrayList<String> split = new ArrayList<String>(Arrays.asList(path.split("/|#|:")));
    Entity child = findParent(cDrive, split);
    child.getParent().deleteChild(name);
  }

  public void move(String origin, String destination) {
    ArrayList<String> split = new ArrayList<String>(Arrays.asList(origin.split("/|#|:")));
    Entity e = findParent(cDrive, split);
    ArrayList<String> newParentPath = new ArrayList<String>(Arrays.asList(destination.split("/|#|:")));
    Entity newParent = findParent(cDrive, newParentPath);
    
    e.getParent().deleteChild(e.getName());

    e.setParent(newParent);
    
  }

  public void writeToFile(String path, String content) {
    ArrayList<String> p = new ArrayList<String>(Arrays.asList(path.split("/|#|:")));
    TextFile e = (TextFile) findParent(cDrive, p);
    e.setContent(content);
  }

  public Entity findParent(Entity parent, ArrayList<String> split) {
    if(split.get(0).equals("c")) {
      split.remove(0);
    }
    Entity child = parent.getChildren().get(split.get(0));
    split.remove(0);
    if(split.size() < 1) {
      return child;
    }    
    return findParent(child, split);
  }

  public void printDirectory(Entity entity) {
    System.out.println(entity.getPath());
    if (entity.isContainer()) {
      for (Entity c : entity.getChildren().values()) {
          printDirectory(c);
      }
    }
  }
}