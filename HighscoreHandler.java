/**
 * Author(s): Andy Pham and Matthew Mach
 * Version: 1.1
 * Date: June 4, 2018
 *
 * Modifications:
 * - Removed readScores from constructor in case we're creating the file for the first time
 * - Added code to give scores and names right values in constructor
 *
 * Description:
 * This class reads and writes highscore information to a text file and
 * provides methdods for the main game to carry out its highscore functions.
 **/

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
public class HighscoreHandler {
    private String fileName; //The name of the file this is writing and reading to
    private List<Integer> scores; // Sorted greatest to least
    private List<String> names;   // Parallel list storing corresponding names
    
    /** Constructor
      * 
      * @param fileName The filename of the highscore file
      **/
    public HighscoreHandler(String fileName){
	this.fileName = fileName;
	scores = new ArrayList<Integer>();
	names = new ArrayList<String>();
	
	//Reads the scores and corresponding names to the arrays
	for (int i = 0; i < 10; i++){
	    scores.add(i, 0);
	    names.add(i, "-");
	}
    }
    
    /** Inserts a score and returns if the player has set a high score
      *
      * @param  score  The score of the player.
      * @param  name   The name of the player.
      * @return if the score is high enough to be added to the highscore file
      **/
    public boolean insertScore(int score, String name){
	for (int i = 0; i < 10; i++)
	    if (scores.get(i) < score){
		scores.add(i,score);
		names.add(i,name);
		scores.remove(10);
		names.remove(10);
		writeScores();
		return true;
	    }
	return false;
    }
    
    /** Reads data into scores **/
    public void readScores(){
	ArrayList<Integer> tempScores = new ArrayList<Integer>();
	ArrayList<String> tempNames = new ArrayList<String>();
	try{
	    BufferedReader reader = new BufferedReader(new FileReader(fileName));
	    for (int i = 0; i < 10; i++){
		tempScores.add(Integer.parseInt(reader.readLine()));
		tempNames.add(reader.readLine());
	    }
	    reader.close();
	    scores = tempScores;
	    names = tempNames;
	}catch (IOException e){System.out.println("IOException in readScores.");}
    }

    /** Writes data from scores into file **/
    public void writeScores(){
	try{
	    PrintWriter writer = new PrintWriter (new BufferedWriter (new FileWriter (fileName)));
	    for (int i = 0; i < 10; i++){
		writer.println(scores.get(i));
		writer.println(names.get(i));
	    }
	    writer.close();
	}catch (IOException e){System.out.println("IOException in writeScores.");}
    }
    
    /** Clears scores **/
    public void clearScores(){
	for (int i = 0; i < 10; i++){
	    scores.set(i, 0);
	    names.set(i, "-");
	}
	writeScores();
    }
    
    /** Accessor for high scores
      *
      * @return The list of high scores
      **/
    public List<Integer> getScores(){return scores;}
    
    /** Accessor for names
      *
      * @return The list of names
      **/
    public List<String> getNames(){return names;}
}
