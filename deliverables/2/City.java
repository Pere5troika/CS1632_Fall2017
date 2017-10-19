import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class City {
  public Random r;
  public int numDrivers;
  public ArrayList<Location> locations;
  public ArrayList<Driver> drivers;

  public City(long seed, int numDrivers) {
    init(seed, numDrivers);
  }

  private void init(long seed, int numDrivers) {
    this.r = new Random(seed);
    this.numDrivers = numDrivers;
    //1. Make locations and add streets
    this.locations = makeLocs();

    //2. Create and Assign drivers to locs
    this.drivers = addDriversToMap(locations, r, numDrivers);
  }

  public ArrayList<Location> makeLocs() {
    ArrayList<Location> locations = new ArrayList<Location>();

    locations.add(new Location("Hotel", false));
    locations.add(new Location("Diner", false));
    locations.add(new Location("Library", false));
    locations.add(new Location("Coffee", false));
    locations.add(new Location("Philadelphia", true));
    locations.add(new Location("Cleveland", true));

    String fourth = "Fourth Ave";
    String fifth = "Fifth Ave";
    String bill = "Bill St";
    String phil = "Phil St";

     //hotel
    locations.get(0).addStreet(new Street(locations.get(1), fourth));
    locations.get(0).addStreet(new Street(locations.get(2), bill));

    //Diner
    locations.get(1).addStreet(new Street(locations.get(4), fourth));
    locations.get(1).addStreet(new Street(locations.get(3), phil));

    //Library
    locations.get(2).addStreet(new Street(locations.get(5), fifth));
    locations.get(2).addStreet(new Street(locations.get(0), bill));

    //Coffee
    locations.get(3).addStreet(new Street(locations.get(2), fifth));
    locations.get(3).addStreet(new Street(locations.get(1), phil));

    //Philadelphia
    locations.get(4).addStreet(new Street(locations.get(3), fifth));

    //Cleveland
    locations.get(5).addStreet(new Street(locations.get(0), fourth));

    return locations;
  }

  //This method will create the drivers and add them to the allowed (not outside cities) starting points
  public ArrayList<Driver> addDriversToMap(ArrayList<Location> locations, Random r, int numDrivers) {
    ArrayList<Driver> drivers = new ArrayList<Driver>();
    for(int x = 0; x < numDrivers; x++){
      drivers.add(new Driver(locations.get(r.nextInt(locations.size()))));
    }
    return drivers;
  }

  //for outside use
  public void runGame() {
    System.out.println(runGame(drivers));
  }

  //This runs the game iterating through each driver and having him roam the city until he leaves
  public String runGame(ArrayList<Driver> drivers) {
    StringBuilder returnString = new StringBuilder();
    for(int x = 0; x < drivers.size(); x++) {
      boolean outsideReached = false;
      while(!outsideReached) {
        int driver1Indexed = x + 1;
        Driver curDriver = drivers.get(x);
        ArrayList<Street> possibleDests = curDriver.getCurrentLocation().getStreets();
        int choice = r.nextInt(possibleDests.size());

        //if we reach outside or we move
        if(possibleDests.get(choice).getLocation().getOutside()) {
         returnString.append("Driver "+driver1Indexed+" has gone to "+possibleDests.get(choice).getLocation().getCityName()+"!"+
         "\n-------------------\n");
         outsideReached = true;
         break;
       } else {
         returnString.append("Driver "+driver1Indexed+" is heading from "+curDriver.getCurrentLocationName()+
         " to "+possibleDests.get(choice).getLocation().getCityName()+" via "+possibleDests.get(choice).getStreet()+".\n");
         curDriver.setCurrentLocation(possibleDests.get(choice).getLocation());
       }
      }
    }
    return returnString.toString();
  }
}
