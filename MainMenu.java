/**
 * Author(s): Matthew Mach
 * Version: 1.4
 * Date: May 27, 2018
 *
 * Modifications:
 * Created class
 *
 * Description:
 * 
 **/

import java.util.*;
import javafx.animation.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.event.*;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Bounds;


public class MainMenu{
  //Instance Variables
  private final String IMGPATH = "/Images/main menu/"; //path of the images used in main menu
  
  private static Scene scene; //the scene used throughout the entire program
  private Pane root; //The main menu root, contains all the main menu elements
  private Game tutorial; //The game object of the tutorial
  private Game childhood; //the game object of the childhood level
  private Game middleschool; //The game object of the middleschool level
  private Game highschool; //The game object of the highschool level
  
  //Graphics
  private ImageView door, title; //the images for the door used in the main menu and the title
  private ImageView text1, text2, text3, text4, textBot; //The images used for each option of the menu as well as the bottom prompt
  
  private Rectangle fade1, fade2, fade3, fade4, fadeTitle, fadeBot; //The rectangles used in the intro fade animation 
  private Color fade; //The color used for the fade
  private double alpha; //The alpha used in fade
  
  private Pane sideMenu; //the pane that holds all nodes used in both side menus
  private ImageView credits; //The image of the credits screen
  private ImageView help; //The image of the help screen
  private ImageView menuButton; //The menu button that returns the user back to the main menu
  
  private static Pane levelSelect; //the pane that holds all the nodes used in the level select menu
  private ImageView clock; //The clock in the level select
  private ImageView choice1, choice2, choice3, choice4; //Each ooption in level select
  private ImageView menuButton2; //The menu button to go back to the main menu 
  
  private HighscoreMaster highscores; //The highscore master
  private Pane highscoreMenu; //The pane that holds all the nodes used in the highscore menu
  private ImageView crown, menuButton3; //The crown and title shown in the highscore menu, and the menu button for this pane 
  private ImageView score1, score2, score3; //Each option 
  private Pane childhoodScores, middleschoolScores, highschoolScores; //each pane that holds the highscores of each level
  
  private Pane tutorialPane; //The pane for the tutorial level
  private Pane childhoodPane; //The pane for the childhood level
  private Pane middleschoolPane; //The pane for the middleschool level
  private Pane highschoolPane; //The pane for the highschool level
  private ImageView menuButton4, menuButton5, menuButton6, menuButton7; //the menu buttons for each level 
  private ImageView closePrompt, leave, stay; //The components of the close prompt when you attempt to go back to the main menu in the middle of a game
  private Rectangle backFade;
  
  /**
   * Constructor for MainMenu
   **/
  public MainMenu (){
    
    //Initializes intro images and relocates them
    door = new ImageView (new Image(IMGPATH + "door.png"));
    text1 = new ImageView (new Image(IMGPATH + "intro_text1.png"));
    text2 = new ImageView (new Image(IMGPATH + "intro_text2.png"));
    text3 = new ImageView (new Image(IMGPATH + "intro_text3.png"));
    text4 = new ImageView (new Image(IMGPATH + "intro_text4.png"));
    title = new ImageView (new Image(IMGPATH + "title.png"));
    textBot = new ImageView (new Image(IMGPATH + "intro_textStudioLabel.png"));
    
    door.relocate (210, 140);
    text1.relocate (500, 145);
    text2.relocate (500, 245);
    text3.relocate (500, 345);
    text4.relocate (500, 445);
    title.relocate (0, 20);
    textBot.relocate (190, 590);
    
    
    //Initializes fade rectangles put on top for each text to simulate a fade
    alpha = 0.0;
    fade = Color.rgb(255, 255, 255, alpha);
    fade1 = new Rectangle (669, 47, fade);
    fade2 = new Rectangle (540, 47, fade);
    fade3 = new Rectangle (540, 47, fade);
    fade4 = new Rectangle (617, 47, fade);
    fadeTitle = new Rectangle (1280, 51, Color.rgb(255, 255, 255, 1));
    fadeBot = new Rectangle (855, 52, fade);
    
    fade1.relocate (500, 145);  
    fade2.relocate (500, 245);
    fade3.relocate (500, 345);
    fade4.relocate (500, 445);
    fadeTitle.relocate (0, 20);
    fadeBot.relocate (190, 590);
    
    
    //Creates menu button and picutres for both help and credits
    sideMenu = new Pane();
    credits = new ImageView (new Image (IMGPATH + "credits.png"));
    help = new ImageView (new Image (IMGPATH + "help.png"));
    menuButton = createMenuButton();;
    sideMenu.getChildren().addAll(help, menuButton);
    
    
    //Creates level select
    levelSelect = new Pane();
    clock = new ImageView(new Image(IMGPATH + "level_select_clock.png"));
    choice1 = new ImageView (new Image(IMGPATH + "level_select_text1.png"));
    choice2 = new ImageView (new Image(IMGPATH + "level_select_text2.png"));
    choice3 = new ImageView (new Image(IMGPATH + "level_select_text3.png"));
    choice4 = new ImageView (new Image(IMGPATH + "level_select_text4.png"));
    
    choice1.relocate (450, 195);
    choice2.relocate (450, 295);
    choice3.relocate (450, 395);
    choice4.relocate (450, 495);
    menuButton2 = createMenuButton();
    enableLevelSelectFunction();
    
    //Adds all graphics to level select pane
    levelSelect.getChildren().addAll(clock, choice1, choice2, choice3, choice4, menuButton2);
    
    
    //Creates the root for the main menu pane
    root = new Pane();
    root.getChildren().addAll(door, text1, text2, text3, text4, textBot, fade1, fade2, fade3, fade4, fadeBot, title, fadeTitle); //Adds all the intro nodes to the current pane
    
    //Adds function to the buttons that adds the "are you sure want to go back to the main menu" prompt
    menuButton4 = createGameMenuButton(tutorialPane);
    menuButton4.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        tutorialPane.getChildren().addAll(backFade, closePrompt, leave, stay);
      }
    });
    menuButton5 = createGameMenuButton(childhoodPane);
    menuButton5.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        childhoodPane.getChildren().addAll(backFade, closePrompt, leave, stay);
      }
    });
    menuButton6 = createGameMenuButton(middleschoolPane);
    menuButton6.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        middleschoolPane.getChildren().addAll(backFade, closePrompt, leave, stay);
      }
    });
    menuButton7 = createGameMenuButton(highschoolPane);
    menuButton7.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        highschoolPane.getChildren().addAll(backFade, closePrompt, leave, stay);
      }
    });
    
    
    //Creates highscore counter
    highscoreMenu = new Pane();
    highscores = new HighscoreMaster("C:/ToLive");
    crown = new ImageView (new Image(IMGPATH + "highscores_screen.png"));
    score1 = new ImageView (new Image(IMGPATH + "level_select_text2.png"));
    score2 = new ImageView (new Image(IMGPATH + "level_select_text3.png"));
    score3 = new ImageView (new Image(IMGPATH + "level_select_text4.png"));
    
    score1.relocate (450, 295);
    score2.relocate (450, 395);
    score3.relocate (450, 495);
    
    menuButton3 = createMenuButton();
    enableHighscoreFunction();
    
    highscoreMenu.getChildren().addAll (crown, menuButton3, score1, score2, score3);
    
    
    //Creates the inital scene
    scene = new Scene(root, 1280, 720, true, SceneAntialiasing.BALANCED);
    scene.setCamera(new PerspectiveCamera());
  }
  
  /** Created as multiple menu buttons are created
    *  
    * @return An Imageview of the button with working user input
    **/
  private ImageView createMenuButton(){
    ImageView menuButtonTemp = new ImageView (new Image (IMGPATH + "menu_button.png"));
    menuButtonTemp.relocate (35, 35);
    menuButtonTemp.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        scene.setRoot(root);
      }
    });
    menuButtonTemp.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        menuButtonTemp.setImage(new Image(IMGPATH + "menu_button_over.png"));
      }
    });
    menuButtonTemp.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        menuButtonTemp.setImage(new Image(IMGPATH + "menu_button.png"));
        
      }
    });
    return menuButtonTemp;
  }
  
  /** Created as multiple menu buttons are created
    *  
    * @return An Imageview of the button with working user input
    **/
  private ImageView createHighscoresMenuButton(){
    ImageView menuButtonTemp = new ImageView (new Image (IMGPATH + "menu_button.png")); //Gets the image
    menuButtonTemp.relocate (35, 35); //relocates it
    //Adds if mouse pressed, and hover over user input
    menuButtonTemp.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        scene.setRoot(highscoreMenu);
      }
    });
    menuButtonTemp.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        menuButtonTemp.setImage(new Image(IMGPATH + "menu_button_over.png"));
      }
    });
    menuButtonTemp.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        menuButtonTemp.setImage(new Image(IMGPATH + "menu_button.png"));
        
      }
    });
    return menuButtonTemp;
  }
  
  /** Created as multiple game menu buttons are created
    *  
    * @return An Imageview of the button with working user input
    **/
  private ImageView createGameMenuButton(Pane gamePane){
    ImageView menuButtonTemp = new ImageView (new Image (IMGPATH + "menu_button.png"));
    menuButtonTemp.relocate (35, 35);
    menuButtonTemp.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        menuButtonTemp.setImage(new Image(IMGPATH + "menu_button_over.png"));
      }
    });
    menuButtonTemp.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        menuButtonTemp.setImage(new Image(IMGPATH + "menu_button.png"));
        
      }
    });
    return menuButtonTemp;
  }
  
  /** Adds the function to the close prompt by game pane
    * @param the pane in which the close prompt is in
    **/
  private void changeGameMenuFunction(Pane gamePane){
    
    closePrompt = new ImageView (new Image (IMGPATH + "leave_main.png"));
    backFade = new Rectangle (1280, 720, Color.rgb(0,0,0,0.5));
    closePrompt.relocate(206, 128);
    backFade.relocate(0,0);
    
    leave = new ImageView (new Image (IMGPATH + "leave.png"));
    leave.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        scene.setRoot(levelSelect);
      }
    });
    leave.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        leave.setImage(new Image(IMGPATH + "leave_over.png"));
      }
    });
    leave.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        leave.setImage(new Image(IMGPATH + "leave.png"));
        
      }
    });
    leave.relocate(370, 500);
    
    stay = new ImageView (new Image (IMGPATH + "stay.png"));
    stay.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        gamePane.getChildren().remove(16, 20); //Place numbers in here
      }
    });
    stay.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        stay.setImage(new Image(IMGPATH + "stay_over.png"));
      }
    });
    stay.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        stay.setImage(new Image(IMGPATH + "stay.png"));
        
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
    scene.setRoot(levelSelect);
  }  
  
  /** Changes the scene to help menu **/
  private void helpMenu() {
    sideMenu.getChildren().remove(0, 2);
    sideMenu.getChildren().addAll(help, menuButton);
    scene.setRoot(sideMenu);
  }
  
  /** Changes the scene to credits **/
  private void credits() {
    sideMenu.getChildren().remove(0, 2);
    sideMenu.getChildren().addAll(credits, menuButton);
    scene.setRoot(sideMenu);
  }
  
  /** updates the childhood highscores from the file**/
  private void updateChildhoodHighscores(){
    childhoodScores = new Pane();
    HighscoreHandler childhoodHandler = highscores.getHandler("childhood");
    childhoodHandler.readScores();
    
    Text[] nameObj = new Text[10];
    Text[] scoreObj = new Text[10];
    
    List<String> names = childhoodHandler.getNames();
    List<Integer> scores = childhoodHandler.getScores();
    
    for (int x=0; x < 5; x++){
      System.out.println (names.get(x) + " " + String.valueOf(scores.get(x)) + names.get(x+5) + " " + String.valueOf(scores.get(x+5)));
      nameObj[x] = new Text (440, 100*(x+1)+50, (x+1)+ ". " + names.get(x));
      scoreObj[x] = new Text (600, 100*(x+1)+50, String.valueOf(scores.get(x)));
      nameObj[x+5] = new Text (660, 100*(x+1)+50, (x+6)+ ". " + names.get(x+5));
      scoreObj[x+5] = new Text (800, 100*(x+1)+50, String.valueOf(scores.get(x+5)));
      
      nameObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      scoreObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      nameObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      scoreObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      Bounds temp1 = scoreObj[x].getBoundsInParent();
      scoreObj[x].relocate (1220 -temp1.getMaxX(), 100*(x+1)-16+50);
      Bounds temp2 = scoreObj[x+5].getBoundsInParent();
      scoreObj[x+5].relocate (1640 -temp2.getMaxX(), 100*(x+1)-16+50);
      childhoodScores.getChildren().addAll(nameObj[x], scoreObj[x], nameObj[x+5], scoreObj[x+5]);
    }
    
    Text title = new Text (400, 80, "Childhood Highscores");
    title.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 44));
    ImageView menuButtonTmp = createHighscoresMenuButton();
    Text clear = new Text (520, 650, "Click to clear");
    clear.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 34));
    clear.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        childhoodHandler.clearScores();
        updateChildhoodHighscores();
        scene.setRoot(childhoodScores);
      }
    });
    
    childhoodScores.getChildren().addAll(title, menuButtonTmp, clear);
  }
  
  /** Updates the middleschool highscores from the file **/
  private void updateMiddleschoolHighscores(){
    middleschoolScores = new Pane();
    HighscoreHandler middleschoolHandler = highscores.getHandler("middleschool");
    middleschoolHandler.readScores();
    
    Text[] nameObj = new Text[10];
    Text[] scoreObj = new Text[10];
    
    List<String> names = middleschoolHandler.getNames();
    List<Integer> scores = middleschoolHandler.getScores();
    
    for (int x=0; x < 5; x++){
      nameObj[x] = new Text (440, 100*(x+1)+50, (x+1)+ ". " + names.get(x));
      scoreObj[x] = new Text (600, 100*(x+1)+50, String.valueOf(scores.get(x)));
      nameObj[x+5] = new Text (660, 100*(x+1)+50, (x+6)+ ". " + names.get(x+5));
      scoreObj[x+5] = new Text (800, 100*(x+1)+50, String.valueOf(scores.get(x+5)));
      
      nameObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      scoreObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      nameObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      scoreObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      Bounds temp1 = scoreObj[x].getBoundsInParent();
      scoreObj[x].relocate (1220 -temp1.getMaxX(), 100*(x+1)-16+50);
      Bounds temp2 = scoreObj[x+5].getBoundsInParent();
      scoreObj[x+5].relocate (1640 -temp2.getMaxX(), 100*(x+1)-16+50);
      middleschoolScores.getChildren().addAll(nameObj[x], scoreObj[x], nameObj[x+5], scoreObj[x+5]);
    }
    Text title = new Text (370, 80, "Middleschool Highscores");
    title.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 44));
    ImageView menuButtonTmp = createHighscoresMenuButton();
    Text clear = new Text (520, 650, "Click to clear");
    clear.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 34));
    clear.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        middleschoolHandler.clearScores();
        updateMiddleschoolHighscores();
        scene.setRoot(middleschoolScores);
      }
    });
    
    middleschoolScores.getChildren().addAll(title, menuButtonTmp, clear);
  }
  
  /** Updates the highschool highscores from the file **/
  private void updateHighschoolHighscores(){
    highschoolScores = new Pane();
    HighscoreHandler highschoolHandler = highscores.getHandler("highschool");
    highschoolHandler.readScores();
    
    Text[] nameObj = new Text[10];
    Text[] scoreObj = new Text[10];
    
    List<String> names = highschoolHandler.getNames();
    List<Integer> scores = highschoolHandler.getScores();
    
    for (int x=0; x < 5; x++){
      nameObj[x] = new Text (440, 100*(x+1)+50, (x+1)+ ". " + names.get(x));
      scoreObj[x] = new Text (600, 100*(x+1)+50, String.valueOf(scores.get(x)));
      nameObj[x+5] = new Text (660, 100*(x+1)+50, (x+6)+ ". " + names.get(x+5));
      scoreObj[x+5] = new Text (800, 100*(x+1)+50, String.valueOf(scores.get(x+5)));
      
      nameObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      scoreObj[x].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      nameObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      scoreObj[x+5].setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
      Bounds temp1 = scoreObj[x].getBoundsInParent();
      scoreObj[x].relocate (1220 -temp1.getMaxX(), 100*(x+1)-16+50);
      Bounds temp2 = scoreObj[x+5].getBoundsInParent();
      scoreObj[x+5].relocate (1640 -temp2.getMaxX(), 100*(x+1)-16+50);
      highschoolScores.getChildren().addAll(nameObj[x], scoreObj[x], nameObj[x+5], scoreObj[x+5]);
    }
    
    Text title = new Text (390, 80, "Highschool Highscores");
    title.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 44));
    ImageView menuButtonTmp = createHighscoresMenuButton();
    Text clear = new Text (520, 650, "Click to clear");
    clear.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 34));
    clear.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        highschoolHandler.clearScores();
        updateHighschoolHighscores();
        scene.setRoot(highschoolScores);
      }
    });
    
    highschoolScores.getChildren().addAll(title, menuButtonTmp, clear);
  }
  
  /** Creates and runs the intro animation**/
  public void intro(){
    //The fade out transition, repeatedly increases the alpha value of a white rgb value to simulate a fade out 
    Timeline fadeOut = new Timeline (
                                     new KeyFrame(
                                                  Duration.millis(100),
                                                  (evt) -> {
      alpha = (((double)(int)(alpha*100.0))+4)/100;
      fade = Color.rgb(255, 255, 255, alpha);
      fade1.setFill(fade);
      fade2.setFill(fade);
      fade3.setFill(fade);
      fade4.setFill(fade);
      fadeBot.setFill(fade);
    }));
    fadeOut.setCycleCount (25);               //Cycles 10 times
    fadeOut.setDelay(Duration.seconds(3));   //Starts after 100 millis
    
    //The fade in transition; repeatedly decreases the alpha value of a white rgb value to simulate a fade in
    Timeline fadeIn = new Timeline (
                                    new KeyFrame(
                                                 Duration.millis(100),
                                                 (evt) -> {
      alpha = (((double)(int)(alpha*100.0))-4)/100;
      fade = Color.rgb(255, 255, 255, alpha);
      fade1.setFill(fade);
      fade2.setFill(fade);
      fade3.setFill(fade);
      fade4.setFill(fade);
      fadeTitle.setFill(fade);
      fadeBot.setFill(fade);
    }));
    fadeIn.setCycleCount (25);                //Cycles 10 times
    
    
    //Sets an action when timeline is done; changes the images behind the white rectangles and autoplays the fade in transition
    fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) {
        text1.setImage(new Image(IMGPATH + "menu_text1.png"));
        text2.setImage(new Image(IMGPATH + "menu_text2.png"));
        text3.setImage(new Image(IMGPATH + "menu_text3.png"));
        text4.setImage(new Image(IMGPATH + "menu_text4.png"));
        textBot.setImage(new Image(IMGPATH + "menu_textPrompt.png"));
        fadeIn.play();
      }
    });
    
    
    //Sets an action when timeline is done; removes the rectangles from the root pane and enables menu function
    fadeIn.setOnFinished(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) {
        root.getChildren().remove(6, 11);
        enableMenuFuction();
      }
    });
    
    
    //Plays the first animation
    fadeOut.play();
  }
  
  /** Adds user input to the main menu; run after the intro animation is done **/
  private void enableMenuFuction(){
    door.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        Driver.close();
      }
    });
    door.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        door.setImage(new Image(IMGPATH + "door_over.png"));
        textBot.setImage(new Image (IMGPATH + "close.png"));
      }
    });
    door.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        door.setImage(new Image(IMGPATH + "door.png"));
        textBot.setImage (new Image (IMGPATH + "menu_textPrompt.png"));
      }
    });
    
    
    text1.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        levelSelect();
      }
    });
    text1.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        text1.setImage(new Image(IMGPATH + "menu_text1_over.png"));
      }
    });
    text1.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        text1.setImage(new Image(IMGPATH + "menu_text1.png"));
        
      }
    });
    
    
    text2.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        helpMenu();
      }
    });
    text2.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        text2.setImage(new Image(IMGPATH + "menu_text2_over.png"));
      }
    });
    text2.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        text2.setImage(new Image(IMGPATH + "menu_text2.png"));
      }
    });
    
    
    text3.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        credits();
      }
    });
    text3.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        text3.setImage(new Image(IMGPATH + "menu_text3_over.png"));
      }
    });
    text3.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        text3.setImage(new Image(IMGPATH + "menu_text3.png"));
      }
    });
    
    
    text4.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        scene.setRoot (highscoreMenu);
      }
    });
    text4.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        text4.setImage(new Image(IMGPATH + "menu_text4_over.png"));
      }
    });
    text4.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        text4.setImage(new Image(IMGPATH + "menu_text4.png"));
      }
    });
  }
  
  /** Enables level select user input; mostly used fro grouping code**/
  private void enableLevelSelectFunction(){
    choice1.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        tutorial = new Game("tutorial");
        tutorialPane = tutorial.getRoot();
        changeGameMenuFunction(tutorialPane);
        tutorialPane.getChildren().add(15, menuButton4);
        scene.setRoot(tutorialPane);
      }
    });
    choice1.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        choice1.setImage(new Image(IMGPATH + "level_select_text1_over.png"));
      }
    });
    choice1.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        choice1.setImage(new Image(IMGPATH + "level_select_text1.png"));
      }
    });
    
    
    choice2.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        childhood = new Game("childhood");
        childhoodPane = childhood.getRoot();
        changeGameMenuFunction(childhoodPane);
        childhoodPane.getChildren().add(15, menuButton5);
        scene.setRoot(childhoodPane);
      }
    });
    choice2.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        choice2.setImage(new Image(IMGPATH + "level_select_text2_over.png"));
      }
    });
    choice2.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        choice2.setImage(new Image(IMGPATH + "level_select_text2.png"));
      }
    });
    
    
    choice3.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        middleschool = new Game("middleschool");
        middleschoolPane = middleschool.getRoot();
        changeGameMenuFunction(middleschoolPane);
        middleschoolPane.getChildren().add(15, menuButton6);
        scene.setRoot(middleschoolPane);
      }
    });
    choice3.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        choice3.setImage(new Image(IMGPATH + "level_select_text3_over.png"));
      }
    });
    choice3.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        choice3.setImage(new Image(IMGPATH + "level_select_text3.png"));
      }
    });
    
    
    choice4.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        highschool = new Game("highschool");
        highschoolPane = highschool.getRoot();
        changeGameMenuFunction(highschoolPane);
        highschoolPane.getChildren().add(15, menuButton7);
        scene.setRoot(highschoolPane);
      }
    });
    choice4.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        choice4.setImage(new Image(IMGPATH + "level_select_text4_over.png"));
      }
    });
    choice4.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        choice4.setImage(new Image(IMGPATH + "level_select_text4.png"));
      }
    });
  }
  
  /** Enables user input in the highscore menu pane; mostly used to organize code**/
  private void enableHighscoreFunction(){
    score1.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        updateChildhoodHighscores();
        scene.setRoot(childhoodScores);
      }
    });
    score1.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        score1.setImage(new Image(IMGPATH + "level_select_text2_over.png"));
      }
    });
    score1.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        score1.setImage(new Image(IMGPATH + "level_select_text2.png"));
      }
    });
    
    
    score2.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        updateMiddleschoolHighscores();
        scene.setRoot(middleschoolScores);
      }
    });
    score2.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        score2.setImage(new Image(IMGPATH + "level_select_text3_over.png"));
      }
    });
    score2.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        score2.setImage(new Image(IMGPATH + "level_select_text3.png"));
      }
    });
    
    
    score3.setOnMousePressed(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        updateHighschoolHighscores();
        scene.setRoot(highschoolScores);
      }
    });
    score3.setOnMouseEntered(new EventHandler<MouseEvent>(){
      @Override public void handle(MouseEvent mouseEvent) {
        score3.setImage(new Image(IMGPATH + "level_select_text4_over.png"));
      }
    });
    score3.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        score3.setImage(new Image(IMGPATH + "level_select_text4.png"));
      }
    });
  }
  
  /** Brings the game back to level select; static because it is used in Game without passing a MainMenu object**/
  public static void backToLevelSelect(){
    scene.setRoot(levelSelect);
  }
}
