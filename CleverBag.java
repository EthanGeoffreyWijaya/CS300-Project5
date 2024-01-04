 //////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    CleverBag
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
import java.util.Scanner;

public class CleverBag extends SimpleBag{
  
  private int size; // The size of the oversize data array
  
  /**
   * Constructor for a CleverBag object. Initializes the size field and call on its parent
   * constructor.
   * 
   * @param seed The seed for the random field
   */
  public CleverBag(int seed) {
    //This is a constructor
    super(seed);
    size = 0;
  }
  
  /**
   * Overrides the loadData method from SimpleBag with a different algorithm. Loads strings from a
   * file into the data array. No element shifting is required as string are added at the end of
   * the array.
   * Complexity: O(N)
   * 
   * @param f The file from which strings are loaded
   */
  @Override
  public void loadData(File f) {
    Scanner fileScan = null;
    int wordCount;
    
    try {
      fileScan = new Scanner(f);
      wordCount = fileScan.nextInt();
      //Loads all words specified by the file's word count into data
      for (int i = 0; i < wordCount; i++) {
        data[i] = fileScan.next();
        //System.out.println(data[i]); (For testing purposes)
        size++;
      }
      fileScan.close();
    } catch (java.io.FileNotFoundException e) {
      System.out.println("error");
      return;
    }
  }
  
  /**
   * Overrides removeRandom from SimpleBag with a different algorithm. Removes a random string
   * from the data array and returns it. Does not require shifting of elements. Removed array is 
   * simply replaced with the last element in the array.
   * Complexity: O(1)
   * 
   * @return The string that was removed
   */
  @Override
  public String removeRandom() {
    if (size <= 0) {
      return null;
    }
    int chosenIndex = random.nextInt(size);
    String chosenStr = data[chosenIndex];
    // No for loop is required to calculate the elements in array since size exists. Since there
    // will be no element shifting, the second for loop is removed as well.
    data[chosenIndex] = data[size - 1];
    data[size - 1] = null;
    size--;
    
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
