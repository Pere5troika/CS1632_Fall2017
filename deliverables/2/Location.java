import java.util.ArrayList;

class Location {
  ArrayList<Destination> destinations = new ArrayList<Destination>();
  boolean outside;
  String cityName;

  public Location(String cityName, boolean outside) {
    this.cityName = cityName;
    this.outside = outside;
  }

  public void addDestination(Destination dest) {
    destinations.add(dest);
  }

  public ArrayList getDestinations() {
    return destinations;
  }

  public String getCityName() {
    return cityName;
  }

  public boolean getOutside() {
    return outside;
  }
}
