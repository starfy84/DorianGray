/**
 * Author(s): Matthew Mach, Dereck Tu, William Xu
 * Version: 2.0
 * Date: April 07, 2019
 *
 * Modifications:
 * - Added state field
 * - Removed randomization of starting values
 * - Added currentCard field
 * - Added nextCard method
 * - Removed creation of text images
 * - Added default result image
 * - Removed unessary code and javadoc comment
 *
 * Description:
 * This class represents a level of the Game. It stores Decks of cards which it creates
 * and moves through and carries out the logic of the game's progression.
 **/

import javafx.scene.image.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import javafx.scene.effect.*;
import javafx.animation.*;
import javafx.scene.*;
import javafx.scene.text.*;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
class Variables {boolean choiceLeft, choiceRight;}
public abstract class Game {
//Instance Variables
  private String deckName;
  protected Deck gameDeck, introDeck; //The two decks in the game, first contianing the cards in the game, the second containing the introduction cards
  // private List<Card> endingCards; //The ending cards, determined on if you lose and cause of loss or win
  protected DeckGenerator deckGenerator; //deckgenerator that generates the deck 
  private Card currentCard; //The currentCard in play
  
  private ImageView resultImage; //
  
  private HashMap<String,ImageView> imageHash; //Used to make sure no image is used more than once
  private HashMap<String,Card> cardHash; //Used to make sure no card is use more than once
  protected String state; //The current state of the program; states are: Intro, Main, End
  
  private Pane root; //the root that contains all the object in the scene
  
  private ImageView cardBack, cardFront; //The current back and front of the card
  private Text choice1, choice2; //The text of each choices presented by the card
  private ImageView cardDim; //The dim on the top of the card when displaying each choice
  
  protected ImageView background; //The background of the scene
  private Text personName, question; //Text for the level name, score, person's name, and question 
  //private ImageView boardScore; //The board that displays the score bars under it
  private Rectangle boardCard, boardName,boardTop; // boardCard is the backboard used to hold the card and the other boards; boardName is the board for the name
  private ImageView cardBackStation; //a stationary carBack behind the current card front and back to simulate going through a deck
  protected String choices;
  private double dark,blur;
  private Timeline blurE,darkE,noneBE,noneDE;
  private String currentBack;
  abstract public void addToDeck();
  abstract public void conditional();
  /** Constructor for Game
    * 
    * @param  deckName  The name of the level/deck. Displayed in game and used to generate Deck.
    */
  public Game(String deckName,String bg){
      blurE= new Timeline(
        new KeyFrame(
          Duration.millis(100),
          (evt) ->{
            blur= Math.min(blur+1,20);
            background.setEffect(new GaussianBlur(blur));
          }));
      blurE.setCycleCount(20);

            darkE= new Timeline(
        new KeyFrame(
          Duration.millis(100),
          (evt) ->{
            ColorAdjust ad = new ColorAdjust();
            dark= Math.max(dark-.05,-0.9);
            ad.setBrightness(dark);
            background.setEffect(ad);
          }));
      darkE.setCycleCount(20);

      noneDE= new Timeline(
        new KeyFrame(
          Duration.millis(100),
          (evt) ->{
            ColorAdjust ad = new ColorAdjust();
            dark= Math.min(dark+.05,0);
            ad.setBrightness(dark);
            background.setEffect(ad);
          }));
      noneDE.setCycleCount(20);

        noneBE= new Timeline(
        new KeyFrame(
          Duration.millis(100),
          (evt) ->{
            blur= Math.max(blur-1,0);
            background.setEffect(new GaussianBlur(blur));
          }));
      noneBE.setCycleCount(20);
    this.deckName = deckName;
    deckGenerator = new DeckGenerator(deckName);
    dark = 0;
    blur = 0;
    state = "Init";
    addToDeck();
    currentBack = "";
    choices = "";
    try{
      PrintWriter clear = new PrintWriter(new FileWriter("saves/"+deckName));
      clear.close();
      // BufferedReader in = new BufferedReader(new FileReader("saves/"+deckName));
      // choices = in.readLine();
    }catch(Exception e){}
    currentCard = gameDeck.nextCard();
    state = "Main";   // Main, End
    
    
    //Creation of the scene
    root = new Pane();
    
    //background intialization
    background = new ImageView(new Image(Const.GAME_PATH+"backgrounds/"+bg+".png"));
    checkBG();
    /*
    boardScore = new ImageView(new Image(Const.GAME_PATH+"ui/game_bar.png"));
    boardScore.relocate(420, 0);
    */
    
    //initialization of the boards
    Color peach = Color.rgb(212, 185, 113);
    Color brown = Color.rgb(108, 58, 5);
    boardCard = new Rectangle (440, 720, peach);
    boardName = new Rectangle (440, 70, brown);  
    boardTop = new Rectangle(440,70,brown);
    cardBackStation = new ImageView(new Image(Const.CARD_BACK_PATH+deckName+".png"));     
    
    //relocation of the boards
    boardCard.relocate(420, 0);
    boardName.relocate(420, 650);
    boardTop.relocate(420,0);
    cardBackStation.relocate (460, 260);

    

    
    // //initialization of the text that displays the score of the user
    // scoreTxt = new Text (1200, 60, String.valueOf(score));
    // scoreTxt.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 36));
    // scoreTxt.setFill (Color.rgb(216, 91, 161));
    
    // //Makes sure the score is aligned to the right
    // Bounds temp = scoreTxt.getBoundsInParent();
    // System.out.println (temp.getMaxX());
    // scoreTxt.relocate (2434 -temp.getMaxX(), 20);
    
    //Initialization of the level name, question, and choice texts  
    
    personName = new Text (420, 700, currentCard.getName()); 
    personName.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 36));
    personName.setFill (Color.rgb(255, 255, 255));
    //Sets the text alignment
    personName.setTextAlignment(TextAlignment.CENTER);
    //Sets the max size in pixels of how many characters are on one line
    personName.setWrappingWidth(440);
    
    question = new Text (435, 100, currentCard.getText()); 
    question.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 22));
    question.setFill (Color.rgb(0, 0, 0));
    question.setTextAlignment(TextAlignment.CENTER);
    question.setWrappingWidth(410);
    
    choice1 = new Text (480, 280, currentCard.getLeftChoice().getText());
    choice1.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
    choice1.setFill (Color.rgb(255, 255, 255));
    choice1.setTextAlignment(TextAlignment.LEFT);
    choice1.setWrappingWidth(320);
    
    choice2 = new Text (480, 280, currentCard.getRightChoice().getText());
    choice2.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
    choice2.setFill (Color.rgb(255, 255, 255));
    choice2.setTextAlignment(TextAlignment.LEFT);
    choice2.setWrappingWidth(320);
    
    
    //Intialization of the back and front of the cards
    cardBack = new ImageView(new Image(Const.CARD_BACK_PATH+deckName+".png")); 
    //Set invisible until flipped
    cardBack.setScaleX(0);
    cardFront = currentCard.getCardFront(); 
    cardDim = new ImageView (new Image (Const.GAME_PATH+"ui/transparent_back.png"));
    
    //Centers the card front and back
    cardBack.relocate(460, 260);
    cardFront.relocate(460, 260);
    
    
    //Creation of user input
    
    //Variable must be final to be accessed in the set methods for user input
    final Variables v = new Variables();
    
    //Sets on mouse dragged
    cardFront.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getSceneX() < 450){ //if mouse is on the left
          if (v.choiceRight){
            root.getChildren().remove(root.getChildren().size()-2, root.getChildren().size()); //removes the right choice if it the mouse somehow never goes in the middle
            root.getChildren().add(choice2);
          }
          else if (!v.choiceLeft){
            if(currentCard.getHasChoice())
              root.getChildren().addAll(cardDim); //Adds the dim and choice if it isn't there
            root.getChildren().addAll(choice1);
          }
          cardFront.setRotate(Math.max(350, 360+(int)((mouseEvent.getSceneX()-555)/19.5))); //rotates the card front, dim, choices together
          if(currentCard.getHasChoice())
            cardDim.setRotate(Math.max(350, 360+(int)((mouseEvent.getSceneX()-555)/19.5)));
          choice1.setRotate(Math.max(350, 360+(int)((mouseEvent.getSceneX()-555)/19.5)));
          v.choiceLeft = true; //Shows the left choice should be shown
          v.choiceRight = false;
        }
        else if (mouseEvent.getSceneX() > 810){ //if mouse is on the right
          if (v.choiceLeft){
            root.getChildren().remove(root.getChildren().size()-2, root.getChildren().size()); //removes the left choice if it the mouse somehow never goes in the middle
            root.getChildren().add(choice1);
          }
          else if (!v.choiceRight){
            if(currentCard.getHasChoice())
              root.getChildren().addAll(cardDim);
            root.getChildren().addAll(choice2);
          }
          cardFront.setRotate(Math.min(10, 360+(int)((mouseEvent.getSceneX()-555)/19.5)));
          if(currentCard.getHasChoice())
            cardDim.setRotate(Math.min(10, 360+(int)((mouseEvent.getSceneX()-555)/19.5)));
          choice2.setRotate(Math.min(10, 360+(int)((mouseEvent.getSceneX()-555)/19.5)));
          v.choiceLeft = false; //Shows the right choice should be shown
          v.choiceRight = true;
        }
        else{ //if mouse is in the middle
          if (v.choiceRight || v.choiceLeft){
            if(currentCard.getHasChoice())
              root.getChildren().remove(root.getChildren().size()-2, root.getChildren().size());
            else
              root.getChildren().remove(root.getChildren().size()-1,root.getChildren().size());
          }
          cardFront.setRotate(360+(int)((mouseEvent.getSceneX()-555)/19.5));
          v.choiceLeft = false; //Shows no choice should be shown
          v.choiceRight = false;
        }
        cardFront.relocate(450+(mouseEvent.getSceneX()-460)/20, 260+(mouseEvent.getSceneY()-640)/30); //Moves the cards in respect to the mouse but within bounds
        if(currentCard.getHasChoice())
          cardDim.relocate(450+(mouseEvent.getSceneX()-460)/20, 260+(mouseEvent.getSceneY()-640)/30);
        choice1.relocate(450+(mouseEvent.getSceneX()-460)/20, 280+(mouseEvent.getSceneY()-640)/30);
        choice2.relocate(500+(mouseEvent.getSceneX()-460)/20, 280+(mouseEvent.getSceneY()-640)/30);
      }
    });
    //Sets on mouse released (after drag)
    cardFront.setOnMouseReleased(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        v.choiceLeft = false; //Sets the variables to false for good measure; resets it 
        v.choiceRight = false;
        cardFront.relocate(460, 260); //Relocated card front and hides it
        cardFront.setRotate(0);
        if (mouseEvent.getSceneX() < 450){
          if(currentCard.getHasChoice())
            root.getChildren().remove(root.getChildren().size()-2, root.getChildren().size());
          else
            root.getChildren().remove(root.getChildren().size()-1,root.getChildren().size());
          updateGame(true); //left choice was chosen
        }
        else if (mouseEvent.getSceneX() > 810){
          if(currentCard.getHasChoice())
            root.getChildren().remove(root.getChildren().size()-2, root.getChildren().size());
          else
            root.getChildren().remove(root.getChildren().size()-1,root.getChildren().size());
          updateGame(false); //right choice was chosen
        }
        if (currentCard == null){ //if the game is over
          conditional();
          MainMenu.backToLevelSelect(); //back to level select
          Const.tutSong.stop();
          Const.act1Song.stop();
          Const.act2Song.stop();
          Const.act3Song.stop();
          Const.menuSong.play();
        }
      }
    });
    
    //Adds all the nodes to the scene
    root.getChildren().addAll(background, boardCard, boardName,boardTop, cardBackStation, /*boardScore,*//* scoreTxt,*/ question, personName, cardFront, cardBack);
  }
  
  
  /** Applies the effect of the choice made and moves to the next card in the deck.
    * During the intro state it simply moves to the next intro card, or the game dcek if the
    * intro deck is emptied. During the main state it applies the effect of the choice and judges
    * whether the game is going to continue. Will return the next card in play, or null if there are
    * none left.
    * 
    * @param  swipeLeft  If the user swiped left. Helps determine choice chosen.
    * @return  nextCard  The next card to be in play, or null if there are none left (after the final result card).
    */
  public void nextCard(boolean swipeLeft){
    if (state.equals("Main")){
      // Apply effects of choice
      
      Choice chosen = (swipeLeft ? currentCard.getLeftChoice() : currentCard.getRightChoice());
      try{
        PrintWriter out = new PrintWriter(new FileWriter("saves/"+deckName,true));
        if(chosen.getText().equals("")){
          out.print("0");
          choices+="0";
        }
        else if(swipeLeft){
          out.print("L");
          choices+="L";
        }
        else{
          out.print("R");
          choices+="R";
        }
        out.close();
      }catch(Exception e){}
      if(Const.GAME_DEBUG){
        System.out.println("  current: "+currentCard);
        System.out.println("  chosen: "+chosen.getText());
      }
      addToDeck();
      // If the game deck has another card
      if (gameDeck.getDeck().peekFirst() != null){
        currentCard = gameDeck.nextCard();
      }
      // When there's no more cards in the game deck, move to success card
      else{
        state = "End";
        currentCard = null;
      }
    }
  }
  
  /** Accessor for root
    *
    * @return The root variable
    **/
  public Pane getRoot(){
    return root;
  }
  
  
  /** Creates and plays a ScaleTransition mocking a card flip**/
  private void cardFlip() {
    ScaleTransition rotation1 = new ScaleTransition(Duration.millis(300), cardBack);
    rotation1.setFromX(1);
    rotation1.setToX(0);
    
    cardFront.setScaleX(0);
    
    
    rotation1.setOnFinished(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) {
        ScaleTransition rotation2 = new ScaleTransition(Duration.millis(300), cardFront);
        rotation2.setFromX(0);
        rotation2.setToX(1);
        rotation2.play();
      }
    });
    rotation1.play();
  }
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
  private void checkBG(){
    if(!currentCard.background.equals(currentBack)){
      if (currentCard.background.equals("Blurry")){
        noneBE.stop();
        blurE.play();
      }
      else if(currentCard.background.equals("Dark")){
        noneDE.stop();
        darkE.play();
      }
      else if(currentBack.equals("Blurry")){
        blurE.stop();
        noneBE.play();
      }
      else if(currentBack.equals("Dark")){
        darkE.stop();
        noneDE.play();
      }
    }
    currentBack = currentCard.background;
  }

  /** **/
  private void updateGame(boolean swipeLeft){
    nextCard(swipeLeft);
    if (currentCard == null)
      return;
    checkBG();
    cardFront.setImage(currentCard.getCardFront().getImage());  
    cardBack.setScaleX(1);
    // scoreTxt.setText(String.valueOf(score));
    personName.setText(currentCard.getName());
    question.setText(currentCard.getText());
    choice1.setText(currentCard.getLeftChoice().getText());
    choice2.setText(currentCard.getRightChoice().getText());
    cardFlip();
  }
  
  //Subject to deletion; used for bugtesting
  /** Accessor for state
    * @return The current value of state 
    **/
  public String getState() {return state;}
  /** Accessor for introDeck
    * @return The current value of introdeck 
    **/
  public Deck getIntroDeck(){return introDeck;}
  /** Accessor for gameDeck
    * @return The current value of gameDeck 
    **/
  public Deck getGameDeck(){return gameDeck;}
  /** Accessor for cardHash
    * @return The current value of cardHash
    **/
  public HashMap<String,Card> getCardHash() {return cardHash;}
}
