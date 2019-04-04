/**
 * Author(s): Matthew Mach
 * Version: 1.4
 * Date: June 8, 2018
 *
 * Modifications:
 * - Main program tests our file structure
 *
 * Description:
 * The main purpose of this class is to run the game.
 **/
import javafx.application.*;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import javafx.scene.image.*;

public class Driver extends Application {
    private static Stage window; //the window of the game
  
    /** Used in applications to initialize what is run on start**/
    @Override
    public void start(Stage stage) throws Exception {
	window = stage;
	stage.getIcons().add(new Image ("/Images/icons/icon64x64.png"));  //Set icon
	MainMenu m = new MainMenu(); //Initialize and run main menu
	window.setScene(m.getScene());
	window.setTitle ("To Live");
	window.show();
	m.intro();
    }    
    /* Gameplay pseudocode:
     * 
     * display loading screen
     * start playing music
     * generate deck
     * reset score
     * display gameplay screen
     * 
     * 0 - Display intro card seqeuence
     * 
     * 1 - Draw nextCard (using deck.nextCard())
     * 2 - Check if game is over (nextCard failed and returned false)
     *      If over, go to 6
     * 3 - Wait for player to make choice
     * 4 - Apply effects of choice
     * 5 - Check if game is over
     *      If over, go to 6
     *      Else, go to 1
     * 6 - Display relevant outro card
     * 7 - Wait for player to make choice
     * 8 - End game and return to Deck
     */
    
     /** Closes the window**/
    public static void close(){
      window.close();
    }
    
    /**Main method **/
    public static void main(String[] args) {
	launch();
    }
}
