 //////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    SimpleBag
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
import java.util.Random;
import java.util.Scanner;

public class SimpleBag {

  protected String[] data; // Oversize array for containing the words in a specified file
  protected Random random; // For random number generation

  /**
   * Constructor for a SimpleBag object. Initializes the data and random fields. Takes a seed as
   * parameter for the random field.
   * @param seed Seed for the random field.
   */
  public SimpleBag(int seed) {
    data = new String[80000]; // 80 000 is the necessary size
    random = new Random(seed);
  }

  /**
   * Loads information from a file and transfers each string separated by a space into the data
   * array. Each string must be inserted into the start of the array, such that all elements are
   * shifted when a new element is added.
   * Complexity: O(N^2)
   * 
   * @param f The file to load information from
   */
  public void loadData(File f) {
    int wordCount;
    Scanner fileScan = null;

    try {
      fileScan = new Scanner(f);
      wordCount = fileScan.nextInt();

      for (int i = 0; i < wordCount; i++) {
        // A double for loop is required for iterating through the words in the file and shifting
        // the contents of data in the manner instructed.
        if (i > 0) {
          for (int j = i; j > 0; j--) {
            if (data[j - 1] != null) {
              data[j] = data[j - 1];
            }
          }
        }
        data[0] = fileScan.next();
        //System.out.println(data[0]); (For testing purposes)
      }
      fileScan.close();

    } catch (java.io.FileNotFoundException e) {
      System.out.println("error");
      return;
    }
  }

  /**
   * Removes a string randomly from the data array and returns it, shifting the array to cover the
   * empty space left by the removed string.
   * Complexity: O(N)
   * 
   * @return The string that was removed
   */
  public String removeRandom() {
    String chosenStr;
    int numWords = 0;
    int chosenIndex;

    // Since we were instructed not to include a size field, this for loop is necessary to calculate
    // the number of strings in data
    for (int i = 0; i < data.length; i++) {
      if (data[i] != null) {
        numWords++;
      } else {
        break;
      }
    }
    
    if (numWords <= 0) {
      return null;
    }
    //System.out.println(numWords); (For testing purposes)
    chosenIndex = random.nextInt(numWords);
    chosenStr = data[chosenIndex];

    // Shifts each string to the left of the array to cover the removed word
    for (int i = chosenIndex; i < numWords - 1; i++) {
      data[i] = data[i + 1];
    }
    data[numWords - 1] = null;

    /* (For testing purposes)
    for (int i = 0; i < data.length; i++) {
      if (data[i] != null) {
      System.out.print(data[i] + " ");
      } else {
        break;
      }
    }
    System.out.println();
    */
    return chosenStr;
  }

}
