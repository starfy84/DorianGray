/**
 * Author(s): Matthew Mach, Dereck Tu, William Xu
 * Version: 2.0
 * Date: April 07, 2019
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
import javafx.util.Duration;
import java.util.Iterator;
import javafx.scene.image.*;
import javafx.scene.media.*;
public class Driver extends Application {
    private static Stage window; //the window of the game

    /** Used in applications to initialize what is run on start**/
    @Override
    public void start(Stage stage) throws Exception {
      Const.menuSong = new MediaPlayer(new Media(getClass().getResource("Resources/Music/menu.mp3").toString()));
      Const.menuSong.setOnEndOfMedia(new Runnable(){
        public void run(){
          Const.menuSong.seek(Duration.ZERO);
        }
      });

      Const.act1Song = new MediaPlayer(new Media(getClass().getResource("Resources/Music/act1.mp3").toString()));
      Const.act1Song.setOnEndOfMedia(new Runnable(){
        public void run(){
          Const.act1Song.seek(Duration.ZERO);
        }
      });

      Const.act2Song = new MediaPlayer(new Media(getClass().getResource("Resources/Music/act2.mp3").toString()));
      Const.act2Song.setOnEndOfMedia(new Runnable(){
        public void run(){
          Const.act2Song.seek(Duration.ZERO);
        }
      });

      Const.act3Song = new MediaPlayer(new Media(getClass().getResource("Resources/Music/act3.mp3").toString()));
      Const.act3Song.setOnEndOfMedia(new Runnable(){
        public void run(){
          Const.act3Song.seek(Duration.ZERO);
        }
      });

      Const.tutSong = new MediaPlayer(new Media(getClass().getResource("Resources/Music/tutorial.mp3").toString()));
      Const.tutSong.setOnEndOfMedia(new Runnable(){
        public void run(){
          Const.tutSong.seek(Duration.ZERO);
        }
      });
      if(Const.MASTER_DEBUG == true){
        Const.MENU_DEBUG = true;
        Const.DECK_DEBUG = true;
        Const.GAME_DEBUG = true;
      }
      window = stage;
      stage.getIcons().add(new Image ("/Images/icons/icon64x64.png"));  //Set icon
      MainMenu m = new MainMenu(); //Initialize and run main menu
      window.setScene(m.getScene());
      window.setTitle (Const.TITLE);
      window.setHeight(759);
      window.setMaxHeight(759);
      window.setMinHeight(759);

      window.setWidth(1296);
      window.setMaxWidth(1296);
      window.setMinWidth(1296);

      window.hide();
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
