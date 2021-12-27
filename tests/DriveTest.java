package tests;

import org.junit.Test;

import entities.Drive;
import exceptions.AlreadyExistsException;
import exceptions.MustHaveParent;
import exceptions.ParentIsNotContainerException;

import static org.junit.Assert.assertEquals;

public class DriveTest {

  @Test
  public void testDrive() throws AlreadyExistsException, MustHaveParent, ParentIsNotContainerException {
    Drive drive1 = new Drive("c", null);
    assertEquals("c:", drive1.getPath());
  }
  
}
