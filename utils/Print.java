package utils;

import entities.Entity;

public class Print {
  public static void printDirectory(Entity entity) {
    System.out.println(entity.getPath() + "\t size: " + entity.getSize());
    if (entity.isContainer()) {
      for (Entity c : entity.getChildren().values()) {
          printDirectory(c);
      }
    }
  }
}
