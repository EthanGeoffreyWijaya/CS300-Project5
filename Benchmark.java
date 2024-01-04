 //////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Benchmark
// Course:   CS 300 Spring 2021
//
// Author:   Ethan Geoffrey Wijaya
// Email:    egwijaya@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         none
// Online Sources:  none
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.FileWriter;

public class Benchmark {

  /**
   * Compares the time taken to run loadData between SimpleBag and CleverBag classes.
   * 
   * @param f The file for loadData to load
   * @param s The SimpleBag object to be used
   * @param c The CleverBag object to be used
   * @return A string demonstrating the time results for both classes (Load: SimpleBag CleverBag)
   */
  public static String compareLoadData(File f, SimpleBag s, CleverBag c) {
    long sBagLoadTime;
    long cBagLoadTime;
    long tempStart;
    long tempEnd;

    // Current time is marked before and after running loadData to later calculate runtime
    tempStart = System.currentTimeMillis();
    s.loadData(f);
    tempEnd = System.currentTimeMillis();

    sBagLoadTime = tempEnd - tempStart;// Subtracts the time before running from the time after

    tempStart = System.currentTimeMillis();
    c.loadData(f);
    tempEnd = System.currentTimeMillis();

    cBagLoadTime = tempEnd - tempStart;

    return "load:\t" + String.format("%-8s", sBagLoadTime) + cBagLoadTime + "\n";
  }

  /**
   * Compares the time taken to run removeRandom() between both SimpleBag and CleverBag classes. 
   * Does this by running compareRemove() of both methods a certain number of times and calculating 
   * the time taken to do so. 
   * 
   * @param n The number of times to run removeRandom
   * @param s The SimpleBag object used
   * @param c The CleverBag object used
   * @return A string demonstrating the time results for both classes (n  SimpleBag  CleverBag)
   */
  public static String compareRemove(int n, SimpleBag s, CleverBag c) {
    long sBagRemoveTime = 0;
    long cBagRemoveTime = 0;
    long tempStart;
    long tempEnd;

    // For loop used to run removeRandom n times
    for (int i = 0; i < n; i++) {
      tempStart = System.currentTimeMillis();
      s.removeRandom();
      tempEnd = System.currentTimeMillis();

      sBagRemoveTime += tempEnd - tempStart;

      tempStart = System.currentTimeMillis();
      c.removeRandom();
      tempEnd = System.currentTimeMillis();

      cBagRemoveTime += tempEnd - tempStart;
    }


    return String.format("%-8s", n) + String.format("%-8s", sBagRemoveTime) + cBagRemoveTime + "\n";
  }

  /**
   * Outputs into a file the time results returned from compareLoadData and compareRemove.
   * 
   * @param in The file to read from
   * @param out The file to output to
   * @param nValues An array containing values of n for the running of compareRemove
   */
  public static void createResultsFile(File in, File out, int[] nValues) {
    SimpleBag s = new SimpleBag(0);
    CleverBag c = new CleverBag(0);
    String loadDataComparison = compareLoadData(in, s, c);
    String removeRandomComparison = "";
    FileWriter output = null;

    for (int i = 0; i < nValues.length; i++) {// Gets compareRemove for all n values in nValues
      // System.out.println(1);
      removeRandomComparison += compareRemove(nValues[i], s, c);
    }

    try {
      output = new FileWriter(out);
      output.write(loadDataComparison);
      output.write(removeRandomComparison);
      output.close();
    } catch (java.io.IOException e) {
      return;
    }

  }

  /**
   * The main method which is used to run tests and the createResultsFile() method.
   * @param args
   */
  public static void main(String[] args) {
    
    /*
    SimpleBag b = new SimpleBag(8);
    b.loadData(new File("poopoo.txt"));
    System.out.println(b.removeRandom());
    CleverBag c = new CleverBag(5);
    c.loadData(new File("shortfile.txt"));
    System.out.println(c.removeRandom());

    System.out.println(compareLoadData(new File("frank.txt"), b, c));
    System.out.println(compareRemove(100, b, c));
    */
    java.util.Random b = new java.util.Random();
    createResultsFile(new File("shortfile.txt"), new File("emptyfile.txt"),
        new int[] {1});
    System.out.println(b.nextInt(1));
  }

}
