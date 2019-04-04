/**
 * Author(s): Andy Pham and Matthew Mach
 * Version: 1.0
 * Date: June 7, 2018
 *
 * Modifications:
 * - Created class
 *
 * Description:
 * This class creates files for HighscoreHandler to write to and uses HighscoreHandler to modify those highscores.
 **/

import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class HighscoreMaster {
    private String fileName; //The name of the directory of the highscore files
    private HighscoreHandler childhoodHandler, middleschoolHandler, highschoolHandler; //The HighscoreHandlers for each stage
    
    /** Constructor
      * 
      * @param fileName The filename of the directory for the highscore files.
      **/
    public HighscoreMaster(String fileName){
	this.fileName = fileName;
	
	// Checks if the files exist
	try {
	    BufferedReader reader = new BufferedReader(new FileReader(fileName + "/childhood.txt"));
	    reader.close();
	    reader = new BufferedReader(new FileReader(fileName + "/middleschool.txt"));
	    reader.close();
	    reader = new BufferedReader(new FileReader(fileName + "/highschool.txt"));
	    reader.close();
	} catch (Exception e){
	    // If they don't, create the directory
	    System.out.println("Making directory: " + new File(fileName).mkdirs());
	    
	    try{
		PrintWriter writer = new PrintWriter(fileName + "/childhood.txt", "UTF-8");
		writer.close();
		writer = new PrintWriter(fileName + "/middleschool.txt", "UTF-8");
		writer.close();
		writer = new PrintWriter(fileName + "/highschool.txt", "UTF-8");
		writer.close();
	    } catch (IOException e2) {System.out.println(e2);}
	}
	
	// Create handlers
	childhoodHandler = new HighscoreHandler(fileName + "/childhood.txt");
	middleschoolHandler = new HighscoreHandler(fileName + "/middleschool.txt");
	highschoolHandler = new HighscoreHandler(fileName + "/highschool.txt");
	
	// Initiate file values
	childhoodHandler.clearScores();
	middleschoolHandler.clearScores();
	highschoolHandler.clearScores();
    }
    
    /** Accessor for handlers
      *
      * @param name The name of the handler wanted
      * @return The handler of choice
      **/
    public HighscoreHandler getHandler(String name){
      if (name.equals("childhood"))
	return childhoodHandler;
      else if (name.equals("middleschool"))
	return middleschoolHandler;
      else if (name.equals("highschool"))
	return highschoolHandler;
      return null;
    }
}
