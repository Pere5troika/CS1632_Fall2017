import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class CitySim9005 {

  public static void main(String args[]) {

    Random r = new Random();
    Scanner in = new Scanner(System.in);
    System.out.println("Welcome to City Sim 9005\nPlease enter a number to seed the generator.");
    try {
      r = new Random(in.nextLong());
    } catch (Exception e) {
      System.out.println("ERROR: Enter one integer argument.");
      System.exit(0);
    }
    //1. Make locations and add streets
    ArrayList<Location> locations = makeLocs();
    connectLocs(locations);

    //2. Create and Assign drivers to locs
    ArrayList<Driver> drivers = addDriversToMap(locations, r);

    //3.iterate turns
    runGame(drivers, r);
  }

  //Create locations
  public static ArrayList<Location> makeLocs() {
    ArrayList<Location> locations = new ArrayList<Location>();

    locations.add(new Location("Hotel", false));
    locations.add(new Location("Diner", false));
    locations.add(new Location("Library", false));
    locations.add(new Location("Coffee", false));
    locations.add(new Location("Philadelphia", true));
    locations.add(new Location("Cleveland", true));
    return locations;
  }

  //Connect the locations with possible destinations
  public static void connectLocs(ArrayList<Location> locations) {
    String fourth = "Fourth Ave";
    String fifth = "Fifth Ave";
    String bill = "Bill St";
    String phil = "Phil St";

     //hotel
    locations.get(0).addDestination(new Destination(locations.get(1), fourth));
    locations.get(0).addDestination(new Destination(locations.get(2), bill));

    //Diner
    locations.get(1).addDestination(new Destination(locations.get(4), fourth));
    locations.get(1).addDestination(new Destination(locations.get(3), phil));

    //Library
    locations.get(2).addDestination(new Destination(locations.get(5), fifth));
    locations.get(2).addDestination(new Destination(locations.get(0), bill));

    //Coffee
    locations.get(3).addDestination(new Destination(locations.get(2), fifth));
    locations.get(3).addDestination(new Destination(locations.get(1), phil));

    //Philadelphia
    locations.get(4).addDestination(new Destination(locations.get(3), fifth));

    //Cleveland
    locations.get(5).addDestination(new Destination(locations.get(0), fourth));
  }

  //This method will create the drivers and add them to the allowed (not outside cities) starting points
  public static ArrayList<Driver> addDriversToMap(ArrayList<Location> locations, Random r) {
    ArrayList<Driver> drivers = new ArrayList<Driver>();
    for(int x = 0; x < 5; x++){
      drivers.add(new Driver(locations.get(r.nextInt(4))));
    }
    return drivers;
  }

  //This runs the game iterating through each driver and having him roam the city until he leaves
  public static void runGame(ArrayList<Driver> drivers, Random r) {
    for(int x = 0; x < drivers.size(); x++) {
      boolean outsideReached = false;
      while(!outsideReached) {
        int plus1Driver = x + 1;
        Driver curDriver = drivers.get(x);
        ArrayList<Destination> possibleDests = curDriver.getCurrentLocation().getDestinations();
        int choice = r.nextInt(possibleDests.size());

        //if we reach outside or we move
        if(possibleDests.get(choice).getLocation().getOutside()) {
         System.out.println("Driver "+plus1Driver+" has gone to "+possibleDests.get(choice).getLocation().getCityName()+"!"+
         "\n-------------------");
         outsideReached = true;
         break;
       } else {
         System.out.println("Driver "+plus1Driver+" is heading from "+curDriver.getCurrentLocationName()+
         " to "+possibleDests.get(choice).getLocation().getCityName()+" via "+possibleDests.get(choice).getStreet()+".");
         curDriver.setCurrentLocation(possibleDests.get(choice).getLocation());
       }
      }
    }
  }

}
