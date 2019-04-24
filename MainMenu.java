/**
 * Author(s): Matthew Mach, Dereck Tu, William Xu
 * Version: 2.0
 * Date: April 07, 2019
 *
 * Modifications:
 *
 * Description:
 * 
 **/

import java.util.*;
import java.io.*;
import javafx.animation.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.event.*;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.geometry.*;
import javafx.scene.effect.*;

public class MainMenu{
  //Instance Variables
  private static Scene scene; //the scene used throughout the entire program
  private Pane root; //The main menu root, contains all the main menu elements
  private Game tutorial; //The game object of the tutorial
  private Game act1; //the game object of act 1
  private Game act2; //The game object of the middleschool level
  private Game act3; //The game object of the highschool level
  //Graphics
  private Text title; //the images for the door used in the main menu and the title
  private Text text1, text3, text4; //The images used for each option of the menu as well as the bottom prompt
  
  // private Rectangle fade1, fade2, fade3, fade4, fadeTitle, fadeBot; //The rectangles used in the intro fade animation 
  // private Color fade; //The color used for the fade
  // private double alpha; //The alpha used in fade
  
  private Pane sideMenu; //the pane that holds all nodes used in both side menus
  private ImageView credits; //The image of the credits screen
  private ImageView help; //The image of the help screen
  private ImageView menuButton; //The menu button that returns the user back to the main menu
  
  private static Pane levelSelect; //the pane that holds all the nodes used in the level select menu
  private Text choice1, choice2, choice3, choice4; //Each ooption in level select
  private ImageView menuButton2; //The menu button to go back to the main menu 
  
  // private HighscoreMaster highscores; //The highscore master
  // private Pane highscoreMenu; //The pane that holds all the nodes used in the highscore menu
  private ImageView crown, menuButton3; //The crown and title shown in the highscore menu, and the menu button for this pane 
  private ImageView score1, score2, score3; //Each option 

  private Pane tutorialPane; //The pane for the tutorial level
  private Pane act1Pane; //The pane for Act 1
  private Pane act2Pane; //The pane for Act 2
  private Pane act3Pane; //The pane for Act 3
  private ImageView menuButton4, menuButton5, menuButton6, menuButton7; //the menu buttons for each level 
  private ImageView closePrompt, leave, stay; //The components of the close prompt when you attempt to go back to the main menu in the middle of a game
  private ImageView portrait;
  private Rectangle backFade;
  private ImageView bgg;
  private Pane creditsPane;
  private Text prog,writ,art;
  private String pImg;
  private int counterr;
  private ImageView bbg;

    private void check(){
      counterr=0;
      try{
        BufferedReader in = new BufferedReader(new FileReader("saves/Act1"));
        if(in.readLine().contains("B"))
          counterr++;
        in = new BufferedReader(new FileReader("saves/Act2"));
        if(in.readLine().contains("B"))
          counterr++;
        in = new BufferedReader(new FileReader("saves/Act3"));
        if(in.readLine().contains("B"))
          counterr++;
      }catch(Exception e){}
      if(counterr<1)
          pImg = "Images/game/backgrounds/normal.png";
      else if(counterr < 3)
          pImg = "Images/game/backgrounds/bad.png";
      else if(counterr == 3)
          pImg = "Images/game/backgrounds/badbad.png";

  }
  private void updateP(){
    portrait.setImage(new Image(pImg));
    bbg.setImage(new Image(pImg));
    bgg.setImage(new Image(pImg));
  }
  /**
   * Constructor for MainMenu
   **/
  public MainMenu (){
    //   Text title = new Text (400, 80, "Childhood Highscores");
  //   title.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 44));

    //Initializes intro images and relocates them
    counterr =0;
    check();
    portrait = new ImageView(new Image(pImg));
    portrait.relocate(0,0);
    text1 = new Text(20,225,"Level Select");
    text1.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 30));
    text3 = new Text(20,325,"Credits");
    text3.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 30));
    text4 = new Text(20,425,"Exit");
    text4.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 30));
    title = new Text(20,20,"The Choices of Dorian Gray");
    title.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 65));
    title.setTextOrigin(VPos.TOP);
    Const.menuSong.play();
    //Initializes fade rectangles put on top for each text to simulate a fade
    // alpha = 0.0;
    // fade = Color.rgb(255, 255, 255, alpha);
    // fade1 = new Rectangle (669, 47, fade);
    // fade2 = new Rectangle (540, 47, fade);
    // fade3 = new Rectangle (540, 47, fade);
    // fade4 = new Rectangle (617, 47, fade);
    // fadeTitle = new Rectangle (1280, 51, Color.rgb(255, 255, 255, 1));
    // fadeBot = new Rectangle (855, 52, fade);
    
    // fade1.relocate (500, 145);  
    // fade2.relocate (500, 245);
    // fade3.relocate (500, 345);
    // fade4.relocate (500, 445);
    // fadeTitle.relocate (0, 20);
    // fadeBot.relocate (190, 590);
    
    
    //Creates menu button and picutres for both help and credits
    sideMenu = new Pane();
    credits = new ImageView (new Image (Const.MAIN_MENU_PATH + "stockimg.png"));
    help = new ImageView (new Image (Const.MAIN_MENU_PATH + "stockimg.png"));
    menuButton = createMenuButton();
    sideMenu.getChildren().addAll(help, menuButton);
    
    
    //Creates level select
    levelSelect = new Pane();
    choice1 = new Text(20,225,"Prelude");
    choice1.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 30));
    
    choice2 = new Text(20,325,"Act I: Basil Hallward");
    choice2.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 30));
    
    choice3 = new Text(20,425,"Act II: Lord Henry Wotton");
    choice3.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 30));
    
    choice4 = new Text(20,525,"Act III: Dorian Gray");
    choice4.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 30));
    menuButton2 = createMenuButton();
    enableLevelSelectFunction();
    bgg = new ImageView(new Image(pImg));
    bgg.relocate(0,0);
    //Adds all graphics to level select pane
    levelSelect.getChildren().addAll(bgg,choice1, choice2, choice3, choice4, menuButton2,portrait);
    

    creditsPane = new Pane();
    prog = new Text(20,225,"Programmer: Dereck Tu");
    prog.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 30));
    
    writ = new Text(20,325,"Writer: Matthew Mach");
    writ.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 30));
    
    art = new Text(20,425,"Artist: William Xu");
    art.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 30));
    menuButton3 = createMenuButton();
    bbg = new ImageView(new Image(pImg));
    bbg.relocate(0,0);
    //Adds all graphics to level select pane
    creditsPane.getChildren().addAll(bbg,prog, writ, art, menuButton3,portrait);

    //Creates the root for the main menu pane
    root = new Pane();
    root.getChildren().addAll(portrait,text1,text3,text4,/*fade1, fade2, fade3, fade4, fadeBot,*/ title/*, fadeTitle*/); //Adds all the intro nodes to the current pane
    //Adds function to the buttons that adds the "are you sure want to go back to the main menu" prompt
    menuButton4 = createGameMenuButton(tutorialPane);
    menuButton4.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        tutorialPane.getChildren().addAll(backFade, closePrompt, leave, stay);
      }
    });
    menuButton5 = createGameMenuButton(act1Pane);
    menuButton5.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        act1Pane.getChildren().addAll(backFade, closePrompt, leave, stay);
      }
    });
    menuButton6 = createGameMenuButton(act2Pane);
    menuButton6.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        act2Pane.getChildren().addAll(backFade, closePrompt, leave, stay);
      }
    });
    menuButton7 = createGameMenuButton(act3Pane);
    menuButton7.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        act3Pane.getChildren().addAll(backFade, closePrompt, leave, stay);
      }
    });
    
    
    // //Creates highscore counter
    // highscoreMenu = new Pane();
    // highscores = new HighscoreMaster(Const.HIGHSCORE_PATH);
    // crown = new ImageView (new Image(Const.MAIN_MENU_PATH + "highscores_screen.png"));
    // score1 = new ImageView (new Image(Const.MAIN_MENU_PATH + "level_select_text2.png"));
    // score2 = new ImageView (new Image(Const.MAIN_MENU_PATH + "level_select_text3.png"));
    // score3 = new ImageView (new Image(Const.MAIN_MENU_PATH + "level_select_text4.png"));
    
    // score1.relocate (450, 295);
    // score2.relocate (450, 395);
    // score3.relocate (450, 495);
    
    // menuButton3 = createMenuButton();
    // enableHighscoreFunction();
    
    // highscoreMenu.getChildren().addAll (crown, menuButton3, score1, score2, score3);
    
    
    //Creates the inital scene
    scene = new Scene(root, Const.LENGTH, Const.WIDTH, true, SceneAntialiasing.BALANCED);
    scene.setCamera(new PerspectiveCamera());
    scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
  }
  
  /** Created as multiple menu buttons are created
    *  
    * @return An Imageview of the button with working user input
    **/
  private ImageView createMenuButton(){
    ImageView menuButtonTemp = new ImageView (new Image (Const.MAIN_MENU_PATH + "menu_button.png"));
    menuButtonTemp.relocate (35, 35);
    menuButtonTemp.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        scene.setRoot(root);
        check();
        updateP();
        Const.tutSong.stop();
        Const.act1Song.stop();
        Const.act2Song.stop();
        Const.act3Song.stop();
        Const.menuSong.play();
      }
    });
    menuButtonTemp.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        menuButtonTemp.setImage(new Image(Const.MAIN_MENU_PATH + "menu_button_over.png"));
      }
    });
    menuButtonTemp.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        menuButtonTemp.setImage(new Image(Const.MAIN_MENU_PATH + "menu_button.png"));
        
      }
    });
    return menuButtonTemp;
  }
  
  /** Created as multiple menu buttons are created
    *  
    * @return An Imageview of the button with working user input
    **/
  // private ImageView createHighscoresMenuButton(){
  //   ImageView menuButtonTemp = new ImageView (new Image (Const.MAIN_MENU_PATH + "menu_button.png")); //Gets the image
  //   menuButtonTemp.relocate (35, 35); //relocates it
  //   //Adds if mouse pressed, and hover over user input
  //   menuButtonTemp.setOnMousePressed(new EventHandler<MouseEvent>(){
  //     @Override public void handle(MouseEvent mouseEvent) {
  //       scene.setRoot(highscoreMenu);
  //     }
  //   });
  //   menuButtonTemp.setOnMouseEntered(new EventHandler<MouseEvent>(){
  //     @Override public void handle(MouseEvent mouseEvent) {
  //       menuButtonTemp.setImage(new Image(Const.MAIN_MENU_PATH + "menu_button_over.png"));
  //     }
  //   });
  //   menuButtonTemp.setOnMouseExited(new EventHandler<MouseEvent>() {
  //     @Override public void handle(MouseEvent mouseEvent) {
  //       menuButtonTemp.setImage(new Image(Const.MAIN_MENU_PATH + "menu_button.png"));
        
  //     }
  //   });
  //   return menuButtonTemp;
  // }
  
  /** Created as multiple game menu buttons are created
    *  
    * @return An Imageview of the button with working user input
    **/
  private ImageView createGameMenuButton(Pane gamePane){
    ImageView menuButtonTemp = new ImageView (new Image (Const.MAIN_MENU_PATH + "menu_button.png"));
    menuButtonTemp.relocate (35, 35);
    menuButtonTemp.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        menuButtonTemp.setImage(new Image(Const.MAIN_MENU_PATH + "menu_button_over.png"));
      }
    });
    menuButtonTemp.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        menuButtonTemp.setImage(new Image(Const.MAIN_MENU_PATH + "menu_button.png"));
        
      }
    });
    return menuButtonTemp;
  }
  
  /** Adds the function to the close prompt by game pane
    * @param the pane in which the close prompt is in
    **/
  private void changeGameMenuFunction(Pane gamePane){
    
    closePrompt = new ImageView (new Image (Const.MAIN_MENU_PATH + "leave_main.png"));
    backFade = new Rectangle (1280, 720, Color.rgb(0,0,0,0.5));
    closePrompt.relocate(206, 128);
    backFade.relocate(0,0);
    
    leave = new ImageView (new Image (Const.MAIN_MENU_PATH + "leave.png"));
    leave.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        check();
        updateP();
        scene.setRoot(levelSelect);
        Const.tutSong.stop();
        Const.act1Song.stop();
        Const.act2Song.stop();
        Const.act3Song.stop();
        Const.menuSong.play();
      }
    });
    leave.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        leave.setImage(new Image(Const.MAIN_MENU_PATH + "leave_over.png"));
      }
    });
    leave.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        leave.setImage(new Image(Const.MAIN_MENU_PATH + "leave.png"));
        
      }
    });
    leave.relocate(370, 500);
    
    stay = new ImageView (new Image (Const.MAIN_MENU_PATH + "stay.png"));
    stay.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        gamePane.getChildren().remove(gamePane.getChildren().size()-4, gamePane.getChildren().size()); //Place numbers in here
      }
    });
    stay.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        stay.setImage(new Image(Const.MAIN_MENU_PATH + "stay_over.png"));
      }
    });
    stay.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        stay.setImage(new Image(Const.MAIN_MENU_PATH + "stay.png"));
        
      }
    });
    stay.relocate(770, 500); 
  }
  
  /**
   * Getter for the intial main menu scene; passes by reference
   * @return A scene with all the main menu nodes in it
   **/
  public Scene getScene() {    
    return scene;
  }
  
  /** Changes the scene to level select **/
  private void levelSelect(){
    check();
    updateP();
    scene.setRoot(levelSelect);

  }  
  

  
  /** Changes the scene to credits **/
  private void credits() {
    check();
    updateP();
    scene.setRoot(creditsPane);
  }
  
  // /** updates the childhood highscores from the file**/
  // private void updateChildhoodHighscores(){
  //   childhoodScores = new Pane();
  //   HighscoreHandler childhoodHandler = highscores.getHandler("childhood");
  //   childhoodHandler.readScores();
    
  //   Text[] nameObj = new Text[10];
  //   Text[] scoreObj = new Text[10];
    
  //   List<String> names = childhoodHandler.getNames();
  //   List<Integer> scores = childhoodHandler.getScores();
    
  //   for (int x=0; x < 5; x++){
  //     System.out.println (names.get(x) + " " + String.valueOf(scores.get(x)) + names.get(x+5) + " " + String.valueOf(scores.get(x+5)));
  //     nameObj[x] = new Text (440, 100*(x+1)+50, (x+1)+ ". " + names.get(x));
  //     scoreObj[x] = new Text (600, 100*(x+1)+50, String.valueOf(scores.get(x)));
  //     nameObj[x+5] = new Text (660, 100*(x+1)+50, (x+6)+ ". " + names.get(x+5));
  //     scoreObj[x+5] = new Text (800, 100*(x+1)+50, String.valueOf(scores.get(x+5)));
      
  //     nameObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     scoreObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     nameObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     scoreObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     Bounds temp1 = scoreObj[x].getBoundsInParent();
  //     scoreObj[x].relocate (1220 -temp1.getMaxX(), 100*(x+1)-16+50);
  //     Bounds temp2 = scoreObj[x+5].getBoundsInParent();
  //     scoreObj[x+5].relocate (1640 -temp2.getMaxX(), 100*(x+1)-16+50);
  //     childhoodScores.getChildren().addAll(nameObj[x], scoreObj[x], nameObj[x+5], scoreObj[x+5]);
  //   }
    
  //   Text title = new Text (400, 80, "Childhood Highscores");
  //   title.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 44));
  //   ImageView menuButtonTmp = createHighscoresMenuButton();
  //   Text clear = new Text (520, 650, "Click to clear");
  //   clear.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 34));
  //   clear.setOnMousePressed(new EventHandler<MouseEvent>(){
  //     @Override public void handle(MouseEvent mouseEvent) {
  //       childhoodHandler.clearScores();
  //       updateChildhoodHighscores();
  //       scene.setRoot(childhoodScores);
  //     }
  //   });
    
  //   childhoodScores.getChildren().addAll(title, menuButtonTmp, clear);
  // }
  
  /** Updates the middleschool highscores from the file **/
  // private void updateMiddleschoolHighscores(){
  //   middleschoolScores = new Pane();
  //   HighscoreHandler middleschoolHandler = highscores.getHandler("middleschool");
  //   middleschoolHandler.readScores();
    
  //   Text[] nameObj = new Text[10];
  //   Text[] scoreObj = new Text[10];
    
  //   List<String> names = middleschoolHandler.getNames();
  //   List<Integer> scores = middleschoolHandler.getScores();
    
  //   for (int x=0; x < 5; x++){
  //     nameObj[x] = new Text (440, 100*(x+1)+50, (x+1)+ ". " + names.get(x));
  //     scoreObj[x] = new Text (600, 100*(x+1)+50, String.valueOf(scores.get(x)));
  //     nameObj[x+5] = new Text (660, 100*(x+1)+50, (x+6)+ ". " + names.get(x+5));
  //     scoreObj[x+5] = new Text (800, 100*(x+1)+50, String.valueOf(scores.get(x+5)));
      
  //     nameObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     scoreObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     nameObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     scoreObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     Bounds temp1 = scoreObj[x].getBoundsInParent();
  //     scoreObj[x].relocate (1220 -temp1.getMaxX(), 100*(x+1)-16+50);
  //     Bounds temp2 = scoreObj[x+5].getBoundsInParent();
  //     scoreObj[x+5].relocate (1640 -temp2.getMaxX(), 100*(x+1)-16+50);
  //     middleschoolScores.getChildren().addAll(nameObj[x], scoreObj[x], nameObj[x+5], scoreObj[x+5]);
  //   }
  //   Text title = new Text (370, 80, "Middleschool Highscores");
  //   title.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 44));
  //   ImageView menuButtonTmp = createHighscoresMenuButton();
  //   Text clear = new Text (520, 650, "Click to clear");
  //   clear.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 34));
  //   clear.setOnMousePressed(new EventHandler<MouseEvent>(){
  //     @Override public void handle(MouseEvent mouseEvent) {
  //       middleschoolHandler.clearScores();
  //       updateMiddleschoolHighscores();
  //       scene.setRoot(middleschoolScores);
  //     }
  //   });
    
  //   middleschoolScores.getChildren().addAll(title, menuButtonTmp, clear);
  // }
  
  // /** Updates the highschool highscores from the file **/
  // private void updateHighschoolHighscores(){
  //   highschoolScores = new Pane();
  //   HighscoreHandler highschoolHandler = highscores.getHandler("highschool");
  //   highschoolHandler.readScores();
    
  //   Text[] nameObj = new Text[10];
  //   Text[] scoreObj = new Text[10];
    
  //   List<String> names = highschoolHandler.getNames();
  //   List<Integer> scores = highschoolHandler.getScores();
    
  //   for (int x=0; x < 5; x++){
  //     nameObj[x] = new Text (440, 100*(x+1)+50, (x+1)+ ". " + names.get(x));
  //     scoreObj[x] = new Text (600, 100*(x+1)+50, String.valueOf(scores.get(x)));
  //     nameObj[x+5] = new Text (660, 100*(x+1)+50, (x+6)+ ". " + names.get(x+5));
  //     scoreObj[x+5] = new Text (800, 100*(x+1)+50, String.valueOf(scores.get(x+5)));
      
  //     nameObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     scoreObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     nameObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     scoreObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
  //     Bounds temp1 = scoreObj[x].getBoundsInParent();
  //     scoreObj[x].relocate (1220 -temp1.getMaxX(), 100*(x+1)-16+50);
  //     Bounds temp2 = scoreObj[x+5].getBoundsInParent();
  //     scoreObj[x+5].relocate (1640 -temp2.getMaxX(), 100*(x+1)-16+50);
  //     highschoolScores.getChildren().addAll(nameObj[x], scoreObj[x], nameObj[x+5], scoreObj[x+5]);
  //   }
    
  //   Text title = new Text (390, 80, "Highschool Highscores");
  //   title.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 44));
  //   ImageView menuButtonTmp = createHighscoresMenuButton();
  //   Text clear = new Text (520, 650, "Click to clear");
  //   clear.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 34));
  //   clear.setOnMousePressed(new EventHandler<MouseEvent>(){
  //     @Override public void handle(MouseEvent mouseEvent) {
  //       highschoolHandler.clearScores();
  //       updateHighschoolHighscores();
  //       scene.setRoot(highschoolScores);
  //     }
  //   });
    
  //   highschoolScores.getChildren().addAll(title, menuButtonTmp, clear);
  // }
  
  /** Creates and runs the intro animation**/
  public void intro(){
    //The fade out transition, repeatedly increases the alpha value of a white rgb value to simulate a fade out 
    // Timeline fadeOut = new Timeline (
    //  new KeyFrame(
    //   Duration.millis(100),
    //   (evt) -> {
    //     alpha = (((double)(int)(alpha*100.0))+4)/100;
    //     fade = Color.rgb(255, 255, 255, alpha);
    //     fade1.setFill(fade);
    //     fade2.setFill(fade);
    //     fade3.setFill(fade);
    //     fade4.setFill(fade);
    //     fadeBot.setFill(fade);
    //   }));
    // fadeOut.setCycleCount (25);               //Cycles 10 times
    // fadeOut.setDelay(Duration.seconds(3));   //Starts after 100 millis
    
    // //The fade in transition; repeatedly decreases the alpha value of a white rgb value to simulate a fade in
    // Timeline fadeIn = new Timeline (
    //   new KeyFrame(
    //    Duration.millis(100),
    //    (evt) -> {
    //     alpha = (((double)(int)(alpha*100.0))-4)/100;
    //     fade = Color.rgb(255, 255, 255, alpha);
    //     fade1.setFill(fade);
    //     fade2.setFill(fade);
    //     fade3.setFill(fade);
    //     fade4.setFill(fade);
    //     fadeTitle.setFill(fade);
    //     fadeBot.setFill(fade);
    //   }));
    // fadeIn.setCycleCount (25);                //Cycles 10 times
    
    
    // //Sets an action when timeline is done; changes the images behind the white rectangles and autoplays the fade in transition
    // fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
    //   @Override
    //   public void handle(ActionEvent t) {
    //     fadeIn.play();
    //   }
    // });
    
    
    // //Sets an action when timeline is done; removes the rectangles from the root pane and enables menu function
    // fadeIn.setOnFinished(new EventHandler<ActionEvent>() {
    //   @Override
    //   public void handle(ActionEvent t) {
    //     root.getChildren().remove(6, 11);
        enableMenuFuction();
    //   }
    // });
    
    
    // //Plays the first animation
    // fadeOut.play();

  }
  
  /** Adds user input to the main menu; run after the intro animation is done **/
  private void enableMenuFuction(){
    
    text1.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        levelSelect();
      }
    });
    text1.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        //  InnerShadow bloom = new InnerShadow();
        //  bloom.setOffsetX(4);
        //  bloom.setOffsetY(4);
        //  bloom.setColor(Color.web("0xD44E4E"));
        // text1.setEffect(bloom);
        text1.setFill(Color.web("0xD44E4E"));
        if(Const.MENU_DEBUG)
          System.out.println("Level Select Entered");
      }
    });
    text1.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        text1.setFill(Color.BLACK);
        if(Const.MENU_DEBUG)
          System.out.println("Level Select Exited");
        
      }
    });
    
    
    
    
    text3.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        credits();
      }
    });
    text3.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        // InnerShadow bloom = new InnerShadow();
        // bloom.setOffsetX(4);
        // bloom.setOffsetY(4);
        // bloom.setColor(Color.web("0x6AA84F"));
        // text3.setEffect(bloom);
        text3.setFill(Color.web("0x6AA84F"));
        if(Const.MENU_DEBUG)
          System.out.println("Credits Entered");
      }
    });
    text3.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        text3.setFill(Color.BLACK);
        if(Const.MENU_DEBUG)
          System.out.println("Credits Exited");
      }
    });
    text4.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        Driver.close();
      }
    });
    text4.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        // InnerShadow bloom = new InnerShadow();
        // bloom.setOffsetX(4);
        // bloom.setOffsetY(4);
        // bloom.setColor(Color.web("0x674EA7"));
        // text4.setEffect(bloom);
        text4.setFill(Color.web("0x674EA7"));
        if(Const.MENU_DEBUG)
          System.out.println("Exit Entered");
      }
    });
    text4.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        text4.setFill(Color.BLACK);
        if(Const.MENU_DEBUG)
          System.out.println("Exit Exited");
      } 
    });
    
    // text4.setOnMousePressed(new EventHandler<MouseEvent>(){
    //   @Override public void handle(MouseEvent mouseEvent) {
    //     scene.setRoot (highscoreMenu);
    //   }
    // });
    // text4.setOnMouseEntered(new EventHandler<MouseEvent>(){
    //   @Override public void handle(MouseEvent mouseEvent) {
    //     text4.setImage(new Image(Const.MAIN_MENU_PATH + "menu_text4_over.png"));
    //   }
    // });
    // text4.setOnMouseExited(new EventHandler<MouseEvent>() {
    //   @Override
    //   public void handle(MouseEvent mouseEvent) {
    //     text4.setImage(new Image(Const.MAIN_MENU_PATH + "menu_text4.png"));
    //   }
    // });
  }
  
  /** Enables level select user input; mostly used fro grouping code**/
  private void enableLevelSelectFunction(){
    choice1.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        tutorial = new Act0();
        tutorialPane = tutorial.getRoot();
        changeGameMenuFunction(tutorialPane);
        tutorialPane.getChildren().add(menuButton4);
        scene.setRoot(tutorialPane);
        Const.menuSong.stop();
        Const.tutSong.play();
      }
    });
    choice1.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        choice1.setFill(Color.web("0x80de2f"));
      }
    });
    choice1.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        choice1.setFill(Color.BLACK);
      }
    });
    
    
    choice2.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        act1 = new Act1();
        act1Pane = act1.getRoot();
        changeGameMenuFunction(act1Pane);
        act1Pane.getChildren().add(menuButton5);
        scene.setRoot(act1Pane);
        Const.menuSong.stop();
        Const.act1Song.play();
      }
    });
    choice2.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        choice2.setFill(Color.web("0x80d0d7"));
      }
    });
    choice2.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        choice2.setFill(Color.BLACK);
      }
    });
    
    
    choice3.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        act2 = new Act2();
        act2Pane = act2.getRoot();
        changeGameMenuFunction(act2Pane);
        act2Pane.getChildren().add(menuButton6);
        scene.setRoot(act2Pane);
        Const.menuSong.stop();
        Const.act2Song.play();
      }
    });
    choice3.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        choice3.setFill(Color.web("0xb480d7"));
      }
    });
    choice3.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        choice3.setFill(Color.BLACK);
      }
    });
    
    
    choice4.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        act3 = new Act3();
        act3Pane = act3.getRoot();
        changeGameMenuFunction(act3Pane);
        act3Pane.getChildren().add(menuButton7);
        scene.setRoot(act3Pane);
        Const.menuSong.stop();
        Const.act3Song.play();
      }
    });
    choice4.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        choice4.setFill(Color.web("0xe78b41"));
      }
    });
    choice4.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        choice4.setFill(Color.BLACK);
      }
    });
  }
  
  // /** Enables user input in the highscore menu pane; mostly used to organize code**/
  // private void enableHighscoreFunction(){
  //   score1.setOnMousePressed(new EventHandler<MouseEvent>(){
  //     @Override public void handle(MouseEvent mouseEvent) {
  //       updateChildhoodHighscores();
  //       scene.setRoot(childhoodScores);
  //     }
  //   });
  //   score1.setOnMouseEntered(new EventHandler<MouseEvent>() {
  //     @Override
  //     public void handle(MouseEvent mouseEvent) {
  //       score1.setImage(new Image(Const.MAIN_MENU_PATH + "level_select_text2_over.png"));
  //     }
  //   });
  //   score1.setOnMouseExited(new EventHandler<MouseEvent>() {
  //     @Override
  //     public void handle(MouseEvent mouseEvent) {
  //       score1.setImage(new Image(Const.MAIN_MENU_PATH + "level_select_text2.png"));
  //     }
  //   });
    
    
  //   score2.setOnMousePressed(new EventHandler<MouseEvent>(){
  //     @Override public void handle(MouseEvent mouseEvent) {
  //       updateMiddleschoolHighscores();
  //       scene.setRoot(middleschoolScores);
  //     }
  //   });
  //   score2.setOnMouseEntered(new EventHandler<MouseEvent>(){
  //     @Override public void handle(MouseEvent mouseEvent) {
  //       score2.setImage(new Image(Const.MAIN_MENU_PATH + "level_select_text3_over.png"));
  //     }
  //   });
  //   score2.setOnMouseExited(new EventHandler<MouseEvent>() {
  //     @Override
  //     public void handle(MouseEvent mouseEvent) {
  //       score2.setImage(new Image(Const.MAIN_MENU_PATH + "level_select_text3.png"));
  //     }
  //   });
    
    
  //   score3.setOnMousePressed(new EventHandler<MouseEvent>(){
  //     @Override public void handle(MouseEvent mouseEvent) {
  //       updateHighschoolHighscores();
  //       scene.setRoot(highschoolScores);
  //     }
  //   });
  //   score3.setOnMouseEntered(new EventHandler<MouseEvent>(){
  //     @Override public void handle(MouseEvent mouseEvent) {
  //       score3.setImage(new Image(Const.MAIN_MENU_PATH + "level_select_text4_over.png"));
  //     }
  //   });
  //   score3.setOnMouseExited(new EventHandler<MouseEvent>() {
  //     @Override
  //     public void handle(MouseEvent mouseEvent) {
  //       score3.setImage(new Image(Const.MAIN_MENU_PATH + "level_select_text4.png"));
  //     }
  //   });
  // }
  
  /** Brings the game back to level select; static because it is used in Game without passing a MainMenu object**/
  public static void backToLevelSelect(){
    scene.setRoot(levelSelect);
  }
}
