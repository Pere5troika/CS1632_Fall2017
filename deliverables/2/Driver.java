
class Driver {
  Location currentLoc;

  public Driver(Location beginLocation) {
    currentLoc = beginLocation;
  }

  public Location getCurrentLocation() {
    return currentLoc;
  }

  public String getCurrentLocationName() {
    return currentLoc.getCityName();
  }

  public void setCurrentLocation( Location loc ) {
    currentLoc = loc;
  }
}
