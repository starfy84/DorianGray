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
import java.util.*;
import java.io.*;
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
      load();
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
    
    private void load(){
      DeckGenerator a1 = new DeckGenerator("Act1");
      DeckGenerator a2 = new DeckGenerator("Act2");
      DeckGenerator a3 = new DeckGenerator("Act3");

      //ACT 1
      Deck d = new Deck();
      put("Act1:2:11-Act1:2:11",gen(a1,2,11,2,11));
      put("Act1:2:13-Act1:2:13",gen(a1,2,13,2,13));
      put("Act1:2:15-Act1:2:21",gen(a1,2,15,2,21));
      put("Act1:2:23-Act1:2:50",gen(a1,2,23,2,50));
      add(d,get("Act1:2:11-Act1:2:11"),get("Act1:2:13-Act1:2:13"),get("Act1:2:15-Act1:2:21"),get("Act1:2:23-Act1:2:50"));
      put("Act1: 2:11 2:13 2:15-2:21 2:23-2:50",d);
      d = new Deck();
      put("Act1:2:12-Act1:2:14",gen(a1,2,12,2,14));
      put("Act1:2:22-Act1:2:22",gen(a1,2,22,2,22));
      add(d,get("Act1:2:12-Act1:2:14"),get("Act1:2:22-Act1:2:22"));
      put("Act1: 2:12-2:14 2:22",d);
      put("Act1:2:51-Act1:2:53",gen(a1,2,51,2,53));
      put("Act1 Init",gen(a1,1,1,2,10));
      //END ACT 1

      //ACT 2
      put("Act2:2:1-Act2:2:4",gen(a2,2,1,2,4));
      put("Act2:3:1-Act2:3:16",gen(a2,3,1,3,16));
      put("Act2:3:18-Act2:3:28",gen(a2,3,18,3,28));
      put("Act2:3:30-Act2:3:51",gen(a2,3,30,3,51));
      put("Act2:3:54-Act2:3:56",gen(a2,3,54,3,56));
      put("Act2:4:1-Act2:4:2",gen(a2,4,1,4,2));
      put("Act2:4:4-Act2:4:9",gen(a2,4,4,4,9));
      put("Act2:4:11-Act2:4:11",gen(a2,4,11,4,11));
      put("Act2:4:13-Act2:4:27",gen(a2,4,13,4,27));
      put("Act2:5:1-Act2:5:2",gen(a2,5,1,5,2));
      put("Act2:5:5-Act2:5:6",gen(a2,5,5,5,6));
      put("Act2:5:10-Act2:5:11",gen(a2,5,10,5,11));
      put("Act2:5:14-Act2:5:16",gen(a2,5,14,5,16));
      put("Act2:5:18-Act2:5:20",gen(a2,5,18,5,20));
      put("Act2:5:22-Act2:5:25",gen(a2,5,22,5,25));
      put("Act2:5:27-Act2:5:34",gen(a2,5,27,5,34));
      put("Act2:5:37-Act2:5:62",gen(a2,5,37,5,62));
      put("Act2:5:64-Act2:5:64",gen(a2,5,64,5,64));

      put("Act2:0",gen(a2,1,1,1,8));
      put("Act2:1",gen(a2,3,1,3,1));
      put("Act2:2",gen(a2,3,4,3,54));
      put("Act2:3",gen(a2,3,57,3,57));
      put("Act2:4",gen(a2,4,1,4,10));
      put("Act2:5",gen(a2,4,12,4,27));
      put("Act2:6",gen(a2,5,1,5,15));
      put("Act2:7",gen(a2,5,17,5,21));
      put("Act2:8",gen(a2,5,23,5,63));

      put("Act2:6:1-Act2:6:7",gen(a2,6,1,6,7));
      put("Act2:5:65-Act2:5:66",gen(a2,5,65,5,66));
      put("Act2:6:8-Act2:6:8",gen(a2,6,8,6,8));
      put("Act2:6:10-Act2:6:12",gen(a2,6,10,6,12));
      put("Act2:6:9-Act2:6:12",gen(a2,6,9,6,12));
      put("Act2:6:13-Act2:6:13",gen(a2,6,13,6,13));
      put("Act2:6:14-Act2:6:16",gen(a2,6,14,6,16));
      put("Act2:6:17-Act2:6:17",gen(a2,6,17,6,17));
      put("Act2:6:19-Act2:6:21",gen(a2,6,19,6,21));
      put("Act2:6:18-Act2:6:21",gen(a2,6,18,6,21));
      put("Act2:6:22-Act2:6:22",gen(a2,6,22,6,22));
      put("Act2:6:24-Act2:6:35",gen(a2,6,24,6,35));
      put("Act2:6:23-Act2:6:35",gen(a2,6,23,6,35));
      //END ACT 2

      //ACT 3
      put("Act3:5:1-Act3:5:21",gen(a3,5,1,5,21));
      put("Act3:4:1-Act3:4:19",gen(a3,4,1,4,19));
      put("Act3:6:1-Act3:6:18",gen(a3,6,1,6,18));
      put("Act3:2:1-Act3:2:66",gen(a3,2,1,2,66));

      put("Act3:BB BG Init",gen(a3,1,1,1,39));
      put("Act3:1:42-Act3:1:62",gen(a3,1,42,1,62));

      put("Act3:GG Init",gen(a3,1,1,1,4));
      put("Act3:1:6-Act3:1:38",gen(a3,1,6,1,38));
      put("Act3:1:40-Act3:1:61",gen(a3,1,40,1,61));
      put("Act3:1:63-Act3:1:63",gen(a3,1,63,1,63));
      put("Act3:1:42-Act3:1:61",gen(a3,1,42,1,61));

      put("Act3:3:32-Act3:3:32",gen(a3,3,32,3,32));
      put("Act3:33-Act3:3:68",gen(a3,3,33,3,68));
      put("Act3:3:69-Act3:3:74",gen(a3,3,69,3,74));
      put("Act3:3:1-Act3:3:30",gen(a3,3,1,3,30));
      //END ACT 3
    }

    private Deck get(String s){
      return Const.DECK_CACHE.get(s);
    }
    private void put(String s, Deck d){
      Const.DECK_CACHE.put(s,d);
    }
    private void add(Deck d,Deck...l){
      for(Deck x:l)
        d.getDeck().addAll(x.getDeck());
    }
    private Deck gen(DeckGenerator d,int bScene, int bCard, int eScene, int eCard){
      return d.genDeck(bScene,bCard,eScene,eCard);
    }


    /** Closes the window**/
    public static void close(){
      window.close();
    }

    /**Main method **/
    public static void main(String[] args) {
      launch();
    }
  }
