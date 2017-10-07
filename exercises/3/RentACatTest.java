import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.*;

public class RentACatTest {


  // Testing the returnCat methodin RentACat
  // Relies on cat so we mock it
  @Test
  public void testReturnCat() {
    RentACat rc = new RentACat();
    Cat unrented = Mockito.mock(Cat.class);
    Mockito.when(unrented.getRented()).thenReturn(false);
    assertFalse(rc.returnCat(unrented));
  }

  @Test
  public void testReturnCatRented() {
    RentACat rc = new RentACat();
    Cat rented = Mockito.mock(Cat.class);
    Mockito.when(rented.getRented()).thenReturn(true);
    assertTrue(rc.returnCat(rented));
  }

  @Test
  public void testRentCat() {
    RentACat rc = new RentACat();
    Cat unrented = Mockito.mock(Cat.class);
    Mockito.when(unrented.getRented()).thenReturn(false);
    assertTrue(rc.rentCat(unrented));
  }

  @Test
  public void testRentCatRented() {
    RentACat rc = new RentACat();
    Cat rented = Mockito.mock(Cat.class);
    Mockito.when(rented.getRented()).thenReturn(true);
    assertFalse(rc.rentCat(rented));
  }

  @Test
  public void testListCats() {
    RentACat rc = new RentACat();
    makeCatList(rc);
    assertEquals(rc.listCats(rc._cats), "ID 1. Jennyanydots\nID 2. Old Deuteronomy\n");
  }

  @Test
  public void testCatExistsFull() {
    RentACat rc = new RentACat();
    makeCatList(rc);
    assertTrue(rc.catExists(1, rc._cats));
  }

  @Test
  public void testCatDoesntExistsFullFalse() {
    RentACat rc = new RentACat();
    makeCatList(rc);
    assertFalse(rc.catExists(4, rc._cats));
  }

  @Test
  public void testCatExistsEmpty() {
    RentACat rc = new RentACat();
    assertFalse(rc.catExists(1, rc._cats));
  }

  private static void makeCatList(RentACat rc) {
    //Ask about this. Seems like a bunch of extra work to make these cats all mocks then
    //stub their methods
    Cat jenny = Mockito.mock(Cat.class);
    Mockito.when(jenny.getRented()).thenReturn(false);
    Mockito.when(jenny.getName()).thenReturn("Jennyanydots");
    Mockito.when(jenny.getId()).thenReturn(1);
    rc._cats.add(jenny);

    Cat old = Mockito.mock(Cat.class);
    Mockito.when(old.getRented()).thenReturn(false);
    Mockito.when(old.getName()).thenReturn("Old Deuteronomy");
    Mockito.when(old.getId()).thenReturn(2);
    rc._cats.add(old);

    //Need to atleast stub the one that is already rented
    Cat rented = Mockito.mock(Cat.class);
    Mockito.when(rented.getRented()).thenReturn(true);
    Mockito.when(rented.getName()).thenReturn("Mistoffelees");
    Mockito.when(rented.getId()).thenReturn(3);
  	rc._cats.add(rented);
  }
}
