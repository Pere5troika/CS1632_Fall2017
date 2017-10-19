import java.util.ArrayList;

class Location {
  ArrayList<Street> streets = new ArrayList<Street>();
  boolean outside;
  String cityName;

  public Location(String cityName, boolean outside) {
    this.cityName = cityName;
    this.outside = outside;
  }

  public void addStreet(Street dest) {
    streets.add(dest);
  }

  public ArrayList<Street> getStreets() {
    return streets;
  }

  public String getCityName() {
    return cityName;
  }

  public boolean getOutside() {
    return outside;
  }
}
