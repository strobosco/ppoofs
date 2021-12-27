package tests;

import org.junit.Test;

import entities.Folder;
import exceptions.AlreadyExistsException;
import exceptions.MustHaveParent;
import entities.Drive;
import main.FileSystem;
import utils.Print;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FileSystemTest {

  @Test
  public void testFileSystem() throws AlreadyExistsException, MustHaveParent {

    // Create filesystem
    FileSystem fs = new FileSystem();

    //Create d: drive
    Drive drive1 = new Drive("d", fs.getCDrive());
    try {
      System.out.print("Testing drive path: ");
      assertEquals("c#d:", drive1.getPath());
    } catch(AssertionError e) {
      System.out.println(e);
      System.exit(1);
    }
    System.out.print("successful\n\n");
    // Create e: drive
    Drive drive2 = new Drive("e", fs.getCDrive());
    try {
      System.out.print("Testing drive path: ");
      assertEquals("c#e:", drive2.getPath());
    } catch(AssertionError e) {
      System.out.println(e);
      System.exit(1);
    }
    System.out.print("successful\n\n");
    // Create f: drive
    Drive drive3 = new Drive("f", fs.getCDrive());
    try {
      System.out.print("Testing drive path: ");
      assertEquals("c#f:", drive3.getPath());
    } catch(AssertionError e) {
      System.out.println(e);
      System.exit(1);
    }
    System.out.print("successful\n\n");

    // Create folder in d: drive
    Folder folder1 = new Folder("test", drive1);
    try {
      System.out.print("Testing folder path: ");
      assertEquals("c#d:test/", folder1.getPath());
    } catch(AssertionError e) {
      System.out.println(e);
      System.exit(1);
    }
    System.out.print("successful\n\n");

    // Create folder in e: drive
    Folder folder2 = new Folder("test", drive2);
    try {
      System.out.print("Testing folder path: ");
      assertEquals("c#e:test/", folder2.getPath());
    } catch(AssertionError e) {
      System.out.println(e);
      System.exit(1);
    }
    System.out.print("successful\n\n");

    // Testing find parent function
    try {
      System.out.print("Testing find parent on folder: ");
      ArrayList<String> path = new ArrayList<String>(Arrays.asList("c#d:test/".split("/|#|:")));
      assertEquals("test", fs.findParent(fs.getCDrive(), path).getName());
    } catch(AssertionError e) { 
      System.out.println("E: " + e);
      System.exit(1);
    }
    System.out.print("successful\n\n");

    // Printing current state of fs
    System.out.print("Printing tree: \n\n");
    Print.printDirectory(fs.getCDrive());
    System.out.print("\n\n");

    // Delete test folder in e: drive
    System.out.print("Deleting test in e: drive: \n\n");
    fs.delete("test", "c#e:test/");

    // Reprint tree
    System.out.print("Printing tree: \n\n");
    Print.printDirectory(fs.getCDrive());
    System.out.print("\n\n");

    // Move test in d: to e: drive
    System.out.print("Moving test in d: to e: drive: \n\n");
    fs.move("c#d:test/", "c#e:");

    // Reprint tree
    System.out.print("Printing tree: \n\n");
    Print.printDirectory(fs.getCDrive());
    System.out.print("\n\n");

  }
  
}
