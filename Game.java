/**
 * Author(s): Matthew Mach, Andy Pham
 * Version: 1.5
 * Date: June 8, 2018
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
public class Game {
//Instance Variables
  private String deckName;
  private Deck gameDeck, introDeck; //The two decks in the game, first contianing the cards in the game, the second containing the introduction cards
  private List<Card> endingCards; //The ending cards, determined on if you lose and cause of loss or win
  private DeckGenerator deckGenerator; //deckgenerator that generates the deck 
  private Card currentCard; //The currentCard in play
  
  private ImageView resultImage; //
  
  private HashMap<String,ImageView> imageHash; //Used to make sure no image is used more than once
  private HashMap<String,Card> cardHash; //Used to make sure no card is use more than once
  
  private int score; //The current score of the player
  private int[] aspects; //The current levels of each aspect. There are four aspects health, happiness, selfesteem, achievement
  private String state; //The current state of the program; states are: Intro, Main, End
  
  private Pane root; //the root that contains all the object in the scene
  
  private ImageView cardBack, cardFront; //The current back and front of the card
  private Text choice1, choice2; //The text of each choices presented by the card
  private ImageView cardDim; //The dim on the top of the card when displaying each choice
  
  private ImageView background; //The background of the scene
  private Text levelName, scoreTxt, personName, question; //Text for the level name, score, person's name, and question 
  private ImageView boardScore; //The board that displays the score bars under it
  private Rectangle healthBar, happinessBar, selfesteemBar, achievementBar; //Each bar represents a the four values in aspects
  private Rectangle boardCard, boardName; // boardCard is the backboard used to hold the card and the other boards; boardName is the board for the name
  private ImageView cardBackStation; //a stationary carBack behind the current card front and back to simulate going through a deck
  
  
  /** Constructor for Game
    * 
    * @param  deckName  The name of the level/deck. Displayed in game and used to generate Deck.
    */
  public Game(String deckName){
    this.deckName = deckName;
    //Creating the hashmaps
    imageHash = new HashMap<String,ImageView>();
    
    List<List<Card>> seq = new ArrayList<List<Card>>();
    
    // Set level name and create decks
    deckGenerator = new DeckGenerator(deckName);
    
    gameDeck = new Deck(deckGenerator.getDeck());
    
    endingCards = gameDeck.getDeck().remove(0);     // First sequence will always be the result cards
    endingCards.add(0,gameDeck.getCurrentCard());   // Compensate for how nextCard() skips over first card
    
    // Health Lose, Happiness Lose, Self-Esteem Lose, Achievement Lose, Success
    seq.add(gameDeck.getDeck().remove(0));
    introDeck = new Deck(seq); // Second sequence will always be the intro deck
    
    gameDeck.nextCard();  // Prepare first card for gameplay
    
    imageHash = deckGenerator.getImageHash();
    cardHash = deckGenerator.getCardHash();
    
    resultImage = imageHash.get("RESULT");
    
    // Reset score
    score = 0;
    
    // Set starting values of aspects
    aspects = new int[4];
    for (int i = 0; i < 4; i++)
      aspects[i] = 50;
    
    state = "Intro";   // Intro, Main, End
    
    //first card is null so nextCard must be called after intialization
    nextCard(true);
    
    //Creation of the scene
    root = new Pane();
    
    //background intialization
    background = new ImageView(new Image("/Images/game/backgrounds/"+deckName+".png"));
    boardScore = new ImageView(new Image("/Images/game/ui/game_bar.png"));
    boardScore.relocate(420, 0);
    
    //initialization of the boards
    Color peach = Color.rgb(212, 185, 113);
    Color brown = Color.rgb(108, 58, 5);
    boardCard = new Rectangle (440, 720, peach);
    boardName = new Rectangle (440, 70, brown);  
    cardBackStation = new ImageView(new Image("/Images/card/back/"+deckName+".png"));     
    
    //relocation of the boards
    boardCard.relocate(420, 0);
    boardName.relocate(420, 650);
    cardBackStation.relocate (460, 260);
    //intialization of the four aspect bars
    healthBar = new Rectangle (50, aspects[0]/2, Color.rgb(153, 225, 100));
    happinessBar = new Rectangle (50, aspects[1]/2, Color.rgb (254, 165, 46));
    selfesteemBar = new Rectangle (50, aspects[2]/2, Color.rgb (171, 40, 72));
    achievementBar = new Rectangle (50, aspects[3]/2, Color.rgb (54, 200, 197));
    
    //relocation of the four aspect bars
    healthBar.relocate(448, 100-aspects[0]/2);
    happinessBar.relocate(557, 100-aspects[1]/2);
    selfesteemBar.relocate(671, 100-aspects[2]/2);
    achievementBar.relocate(778, 100-aspects[3]/2);
    
    //initialization of the text that displays the score of the user
    scoreTxt = new Text (1200, 60, String.valueOf(score));
    scoreTxt.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 36));
    scoreTxt.setFill (Color.rgb(216, 91, 161));
    
    //Makes sure the score is aligned to the right
    Bounds temp = scoreTxt.getBoundsInParent();
    System.out.println (temp.getMaxX());
    scoreTxt.relocate (2434 -temp.getMaxX(), 20);
    
    //Initialization of the level name, question, and choice texts        
    levelName = new Text (35, 685, deckName.substring(0, 1).toUpperCase() + deckName.substring(1)); 
    levelName.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 36));
    //Sets the color of the text
    levelName.setFill (Color.rgb(255, 255, 255));
    
    personName = new Text (420, 700, currentCard.getName()); 
    personName.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 36));
    personName.setFill (Color.rgb(255, 255, 255));
    //Sets the text alignment
    personName.setTextAlignment(TextAlignment.CENTER);
    //Sets the max size in pixels of how many characters are on one line
    personName.setWrappingWidth(440);
    
    question = new Text (435, 160, currentCard.getText()); 
    question.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 24));
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
    cardBack = new ImageView(new Image("/Images/card/back/"+deckName+".png")); 
    //Set invisible until flipped
    cardBack.setScaleX(0);
    cardFront = currentCard.getCardFront(); 
    cardDim = new ImageView (new Image ("/Images/game/ui/transparent_back.png"));
    
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
            root.getChildren().remove(17, 18); //removes the right choice if it the mouse somehow never goes in the middle
            root.getChildren().add(choice2);
          }
          else if (!v.choiceLeft)
            root.getChildren().addAll(cardDim, choice1); //Adds the dim and choice if it isn't there
          cardFront.setRotate(Math.max(350, 360+(int)((mouseEvent.getSceneX()-555)/19.5))); //rotates the card front, dim, choices together
          cardDim.setRotate(Math.max(350, 360+(int)((mouseEvent.getSceneX()-555)/19.5)));
          choice1.setRotate(Math.max(350, 360+(int)((mouseEvent.getSceneX()-555)/19.5)));
          v.choiceLeft = true; //Shows the left choice should be shown
          v.choiceRight = false;
        }
        else if (mouseEvent.getSceneX() > 810){ //if mouse is on the right
          if (v.choiceLeft){
            root.getChildren().remove(17, 18); //removes the left choice if it the mouse somehow never goes in the middle
            root.getChildren().add(choice1);
          }
          else if (!v.choiceRight)
            root.getChildren().addAll(cardDim, choice2);
          cardFront.setRotate(Math.min(10, 360+(int)((mouseEvent.getSceneX()-555)/19.5)));
          cardDim.setRotate(Math.min(10, 360+(int)((mouseEvent.getSceneX()-555)/19.5)));
          choice2.setRotate(Math.min(10, 360+(int)((mouseEvent.getSceneX()-555)/19.5)));
          v.choiceLeft = false; //Shows the right choice should be shown
          v.choiceRight = true;
        }
        else{ //if mouse is in the middle
          if (v.choiceRight || v.choiceLeft)
            root.getChildren().remove(16, 18);
          cardFront.setRotate(360+(int)((mouseEvent.getSceneX()-555)/19.5));
          v.choiceLeft = false; //Shows no choice should be shown
          v.choiceRight = false;
        }
        cardFront.relocate(450+(mouseEvent.getSceneX()-460)/20, 260+(mouseEvent.getSceneY()-640)/30); //Moves the cards in respect to the mouse but within bounds
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
          root.getChildren().remove(16, 18); //dim and choice removes
          updateGame(true); //left choice was chosen
        }
        else if (mouseEvent.getSceneX() > 810){
          root.getChildren().remove(16, 18);
          updateGame(false); //right choice was chosen
        }
        if (currentCard == null){ //if the game is over
          if (deckName != "tutorial")
            enterHighscore(); //enter highscores
          else
            MainMenu.backToLevelSelect(); //back to level select
        }
      }
    });
    
    //Adds all the nodes to the scene
    root.getChildren().addAll(background, boardCard, boardName, cardBackStation, healthBar, happinessBar, selfesteemBar, achievementBar, boardScore, scoreTxt, levelName, question, personName, cardFront, cardBack);
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
    if (state.equals("Intro")){
      // Ensure we don't skip over result cards
      if (currentCard != null){
        if (introDeck.nextCard((swipeLeft ? currentCard.getLeftChoice() : currentCard.getRightChoice()))){
          currentCard = introDeck.getCurrentCard();
          System.out.println("  went to next intro card: "+currentCard);
        }
        // When there's no more cards in the intro deck, move to main deck
        else{
          state = "Main";
          currentCard = gameDeck.getCurrentCard();
          System.out.println("  went to game deck: "+currentCard);
        }
      }
      // No current card, so we call nextCard without passing choice
      else if (introDeck.nextCard()){
        currentCard = introDeck.getCurrentCard();
        System.out.println("  went to next intro card: "+currentCard);
      }
      // No intro cards left, move to game
      else{
        state = "Main";
        currentCard = gameDeck.getCurrentCard();
        System.out.println("  went to game deck: "+currentCard);
      }
    }
    else if (state.equals("Main")){
      // Apply effects of choice
      int sum = 0;
      
      Choice chosen = (swipeLeft ? currentCard.getLeftChoice() : currentCard.getRightChoice());
      
      System.out.println("  current: "+currentCard);
      System.out.println("  chosen: "+chosen);
      System.out.print("  aspects: ");
      
      for (int i = 0; i < 4; i++){
        if (chosen.getHasEffect()[i]){
          aspects[i] = Math.min(Math.max(aspects[i] + chosen.getEffectAmount()[i],0),100);
          
          // Check if game over due to aspect depletion
          if (aspects[i] == 0){
            currentCard = endingCards.get(i);
            return;
          } 
          
          sum += aspects[i];
        }
        System.out.print(" " + aspects[i]);
      }
      
      // Increase score by average of aspects divided by 4, multiply by 10 for aesthetic
      score += (int)(10+(sum/16.0)*10);
      System.out.println("\n  score:"+score);
      
      // If the game deck has another card
      if (gameDeck.nextCard(chosen)){
        currentCard = gameDeck.getCurrentCard();
      }
      // When there's no more cards in the game deck, move to success card
      else{
        state = "End";
        currentCard = endingCards.get(4);
      }
    }
    else {
      currentCard = null;
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
  
  /** Updates the aspect bars with the current values after the choice and updates the score**/
  private void updateGameBars(){
    healthBar.setHeight(aspects[0]/2);
    happinessBar.setHeight(aspects[1]/2);
    selfesteemBar.setHeight(aspects[2]/2);
    achievementBar.setHeight(aspects[3]/2);
    
    healthBar.relocate(448, 100-aspects[0]/2);
    happinessBar.relocate(557, 100-aspects[1]/2);
    selfesteemBar.relocate(671, 100-aspects[2]/2);
    achievementBar.relocate(778, 100-aspects[3]/2);
    
    scoreTxt.setText(String.valueOf(score));
    
    scoreTxt.relocate(1200, 60);
    Bounds temp = scoreTxt.getBoundsInParent();
    scoreTxt.relocate (2434 -temp.getMaxX(), 20);
  }
  
  /** **/
  private void updateGame(boolean swipeLeft){
    nextCard(swipeLeft);
    if (currentCard == null)
      return;
    cardFront.setImage(currentCard.getCardFront().getImage());  
    cardBack.setScaleX(1);
    updateGameBars();
    scoreTxt.setText(String.valueOf(score));
    personName.setText(currentCard.getName());
    question.setText(currentCard.getText());
    choice1.setText(currentCard.getLeftChoice().getText());
    choice2.setText(currentCard.getRightChoice().getText());
    cardFlip();
  }
  
  private void enterHighscore(){
    root.getChildren().remove(0, 16);
    HighscoreMaster highscores = new HighscoreMaster("C:/ToLive");
    HighscoreHandler highscoresHandler = highscores.getHandler(deckName);
    highscoresHandler.readScores();
    
    Text[] nameObj = new Text[10];
    Text[] scoreObj = new Text[10];
    
    List<String> names = highscoresHandler.getNames();
    List<Integer> scores = highscoresHandler.getScores();
    
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
      root.getChildren().addAll(nameObj[x], scoreObj[x], nameObj[x+5], scoreObj[x+5]);      
    }
    Text title = new Text (370, 80, deckName.substring(0,1).toUpperCase() + deckName.substring(1) + " Highscores");
    title.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 44));
    
    Label label1 = new Label("Name:");
    TextField textField = new TextField ();
    textField.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
    label1.setFont(Font.loadFont(getClass().getResourceAsStream("/Images/montserrat_light.ttf"), 16));
    label1.relocate(100, 100);
    textField.relocate(100, 150);
    root.getChildren().addAll(title, label1, textField);
    root.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
      if(key.getCode()==KeyCode.ENTER) {
          highscoresHandler.insertScore(score, textField.getText());
          highscoresHandler.writeScores();
          MainMenu.backToLevelSelect();
      }
    });
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
