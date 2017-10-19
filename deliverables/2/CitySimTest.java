
import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class CitySimTest {


  Location hotel;

  @Test
  //Tests that the city hits all methods
  //and constructs each variable
  public void testCityConstructor() {
    City test = new City(12, 1);
    assertEquals(1, test.numDrivers);
    assertNotNull(test.r);
    assertNotNull(test.locations);
    assertNotNull(test.drivers);
  }

  @Test
  //tests street construction
  public void testStreetGetters() {
    Location hotel = mock(Location.class);
    Street test = new Street(hotel, "Street name");
    assertEquals(test.getLocation(), hotel);
    assertEquals(test.getStreet(), "Street name");
  }

  @Test
  //tests location construction
  public void testLocationConstructionGetters() {
    hotel = new Location("Hotel", false);
    assertEquals(hotel.getCityName(), "Hotel");
    assertEquals(hotel.getOutside(), false);
  }

  @Test
  //tests adding a street to a location
  //and displays its name
  public void testLocationAddStreet() {
    hotel = new Location("Hotel", false);

    Street aStreet = mock(Street.class);
    when(aStreet.getStreet()).thenReturn("Some St");
    hotel.addStreet(aStreet);

    assertEquals(hotel.getStreets().get(0).getStreet(), "Some St");
  }

  @Test
  //tests that an added street goes to the proper dest
  public void testLocationAddStreetDest() {
    hotel = new Location("Hotel", false);

    Location testLoc = mock(Location.class);
    Street aStreet = mock(Street.class);
    when(aStreet.getLocation()).thenReturn(testLoc);
    hotel.addStreet(aStreet);

    assertEquals(hotel.getStreets().get(0).getLocation(), testLoc);
  }

  @Test
  //tests that a location has all streets added.
  public void testLocationGetStreets() {
    hotel = new Location("Hotel", false);

    ArrayList<Street> streets =  new ArrayList<Street>();
    Street street1 = mock(Street.class);
    streets.add(street1);
    Street street2 = mock(Street.class);
    streets.add(street2);
    Street street3 = mock(Street.class);
    streets.add(street3);

    hotel.addStreet(street1);
    hotel.addStreet(street2);
    hotel.addStreet(street3);

    assertEquals(hotel.getStreets(), streets);
    }

  @Test
  //Make sure correct number of drivers added to map
  public void testAddCorrectNumDrivers() {
    City city = new City(12, 1);
    ArrayList<Location> locations = new ArrayList<Location>();
    Location testLoc = mock(Location.class);
    locations.add(testLoc);
    ArrayList<Driver> drivers = city.addDriversToMap(locations, new Random(50), 3);

    assertEquals(drivers.size(), 3);
  }

  @Test
  //Make sure all drivers allocated to consistent places
  public void testAddDriversToPlace() {
    City city = new City(12, 1);
    ArrayList<Location> locations = new ArrayList<Location>();
    Location testLoc = mock(Location.class);
    locations.add(testLoc);
    when(testLoc.getCityName()).thenReturn("Hotel");
    ArrayList<Driver> drivers = city.addDriversToMap(locations, new Random(50), 3);

    for (Driver d : drivers)
      assertEquals(d.getCurrentLocationName(), "Hotel");
  }

  @Test
  //Test run game runs consistently
  public void testRunGame() {
    City testCity1 = new City(12, 5);
    String test1 = testCity1.runGame(testCity1.drivers);

    City testCity2 = new City(12, 5);
    String test2 = testCity2.runGame(testCity2.drivers);
    assertEquals(test1, test2);

  }

}
