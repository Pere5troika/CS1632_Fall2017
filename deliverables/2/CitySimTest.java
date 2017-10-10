import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Random;

import org.mockito.*;

public class CitySimTest {

  @Test
    //Test makes sure that makeLoc()
    //produces 6 locations for the city
    public void testMakeLocsSize() {
    CitySim9005 city = new CitySim9005();
    ArrayList<Location> locs = city.makeLocs();
    assertEquals(locs.size(), 6);
  }

  @Test
    //Check that connectLocs added destinations to
    //each of the locations
    public void testConnectLocs() {
      CitySim9005 city = new CitySim9005();

      ArrayList<Location> locations = new ArrayList<Location>();
      locations.add(new Location("Hotel", false));
      locations.add(new Location("Diner", false));
      locations.add(new Location("Library", false));
      locations.add(new Location("Coffee", false));
      locations.add(new Location("Philadelphia", true));
      locations.add(new Location("Cleveland", true));

      city.connectLocs(locations);

      for(Location l : locations) {
        assert(l.getDestinations().size() > 0);
      }
    }

  @Test
    //Test that drivers were placed in random locs
    public void testAddDriversToMap() {
      CitySim9005 city = new CitySim9005();

      ArrayList<Location> locations = new ArrayList<Location>();
      locations.add(new Location("Hotel", false));
      locations.add(new Location("Diner", false));
      locations.add(new Location("Library", false));
      locations.add(new Location("Coffee", false));
      locations.add(new Location("Philadelphia", true));
      locations.add(new Location("Cleveland", true));

      Random r = new Random(13242);
      ArrayList<Driver> drivers = city.addDriversToMap(locations, r);
      ArrayList<Driver> drivers2 = city.addDriversToMap(locations, r);

      assertNotEquals(drivers, drivers2);

    }



}
