//This class holds a street to a location pair
class Street {
  Location loc;
  String street;

  public Street(Location loc, String street) {
    this.loc = loc;
    this.street = street;
  }

  public Location getLocation() {
    return loc;
  }

  public String getStreet() {
    return street;
  }

}
