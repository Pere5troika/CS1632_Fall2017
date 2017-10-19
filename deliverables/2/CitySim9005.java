import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class CitySim9005 {

  public static void main(String args[]) {

    long seed = 0;
    Scanner in = new Scanner(System.in);
    System.out.println("Welcome to City Sim 9005\nPlease enter a number to seed the generator.");
    try {
      seed = in.nextLong();
    } catch (Exception e) {
      System.out.println("ERROR: Enter one integer argument.");
      System.exit(0);
    }

    City ourCity = new City(seed, 5);
    ourCity.runGame();
  }


}
