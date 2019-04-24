/**
 * Author(s): Matthew Mach, Dereck Tu, William Xu
 * Version: 2.0
 * Date: April 07, 2019
  *
  * Modifications:
  * - Added nextCard method that sets the next card and inserts cards as needed depending on the current card
  * - Added currentCard field representing card at front of the deck
  * - Added getCurrentCard accessor method 
  * - Removed getParent(), not needed
  * - Removed nextCard() from constructor
  *
  * Description:
  * This class stores a Deck of Cards that will be manipulated by the Game class.
  * It will utilize the DeckImageGenerator class to construct Card objects and Images
  * from a text file.
  **/

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.*;
import javafx.event.EventHandler;
import java.util.*;
import java.io.*;

public class Deck {
  private LinkedList<Card> deck;
  private Card currentCard;
  private int scenes;
  private int totalCards;
  private int[] cardsPerScene;
  private int currScene;
  private int currCard;
  private Parent deckImgs;
    /** Constructor
      *  
      * @param deckName  The string relating to the Deck file.
      **/
    public Deck (String act) {
      deck = new LinkedList<Card>();
      currScene = currCard = 1;
      try{
        BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Resources/"+act+"/scenes.txt")));
        scenes = Integer.parseInt(in.readLine());
        cardsPerScene = new int[scenes];
        for(int x = 0;x < scenes;x++){
          cardsPerScene[x] = Integer.parseInt(in.readLine());
          totalCards+=cardsPerScene[x];
        }
        for(int scene = 1; scene <= scenes; scene++){
          for(int card = 1; card <= cardsPerScene[scene-1]; card++){
            Const.CARD_CACHE.put(act+":"+scene+":"+card,readCard(act,String.valueOf(scene),String.valueOf(card)));
          }
        }
      }catch(Exception e){e.printStackTrace();}
    }
    public Deck () {
      deck = new LinkedList<Card>();
    }


    private Card readCard(String act, String scene, String card){
      Card c = null;
      String type="",character="",text="";
      try{
        BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Resources/"+act+"/Scene"+scene+"/"+card+".txt")));
        type = in.readLine();
        character = in.readLine();
        text = in.readLine();
        if(type.equals("End of Dialogue")){
          Choice choice1 = new Choice(in.readLine());
          Choice choice2 = new Choice(in.readLine());
          String background = in.readLine();
          c = new Card(type, character, text, background, getCharacterImage(character),choice1,choice2);
        }
        else {
          Choice choice1 = new Choice("");
          Choice choice2 = new Choice("");
          String background = in.readLine();
          c = new Card(type, character, text, background, getCharacterImage(character),choice1,choice2);
        }
        c.act = act;
        c.scene = scene;
        c.cardn = card;
      }catch(Exception e){System.out.println(character);}
      return c;
    }
    
    private ImageView getCharacterImage(String character){
        return new ImageView(new Image(Const.CARD_FRONT_PATH+character+".png"));
    }

    /** Adds nextCards from choice into Deck, removes empty entries in deck, and sets the new value of currentCard.
      * 
      * @param chosen  The choice the player chose for the previous card
      * 
      * @return  If the deck still has cards.
      **/
    public Card nextCard(Choice chosen){
      currentCard = deck.pollFirst();
      return currentCard;
    }

    public Card nextCard(){
      currentCard = deck.pollFirst();
      return currentCard;
    }

    public int[] getCardsPerScene(){return cardsPerScene;}

    public int getScenes(){return scenes;}
    /** The card currently in play.
      * 
      * @return  Value of currentCard.
      **/
    public Card getCurrentCard(){ return currentCard;}
    
    /** Accessor for the deck of cards
      * 
      * @return     The deck of Cards
      **/
    public LinkedList<Card> getDeck(){ return deck;}
    
    /** toString overload
      *
      * @return A string representation of the object
      **/
    @Override
    public String toString() { return "" + deck;}
  }
