package tests;

import org.junit.Test;

import entities.Folder;
import exceptions.AlreadyExistsException;
import exceptions.MustHaveParent;
import entities.Drive;

import static org.junit.Assert.assertEquals;

public class FolderTest {

  @Test
  public void testFolder() throws AlreadyExistsException, MustHaveParent {
    Drive drive1 = new Drive("c", null);
    try {
      assertEquals("c#", drive1.getPath());
    } catch(AssertionError e) {
      System.out.println(e);
      System.exit(1);
    }
    Folder folder1 = new Folder("test", drive1);
    try {
      System.out.println(folder1.getPath());
      assertEquals("c#test/", folder1.getPath());
    } catch(AssertionError e) {
      System.out.println(e);
      System.exit(1);
    }

  }
  
}
