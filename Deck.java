/**
  * Author(s): Matthew Mach, Andy Pham
  * Version: 1.6
  * Date: June 7, 2018
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
import java.util.List;
import java.util.ArrayList;

public class Deck {
    private List<List<Card>> deck;
    private Card currentCard;
    
    private Parent deckImgs;
    
    /** Constructor
      *  
      * @param deckName  The string relating to the Deck file.
      **/
    public Deck (List<List<Card>> deck) {
	this.deck = deck;
    }
    
    /** Adds nextCards from choice into Deck, removes empty entries in deck, and sets the new value of currentCard.
      * 
      * @param chosen  The choice the player chose for the previous card
      * 
      * @return  If the deck still has cards.
      **/
    public boolean nextCard(Choice chosen){
	// Add result card from chosen choice to front of Deck
	if (chosen.getNextCards().size() > 0)
	    deck.get(0).add(0,chosen.getNextCard());
	
	// Add next cards from the chosen choice (result card has already been removed)
	deck.add((int)(Math.random()*(deck.size()-1))+1, chosen.getNextCards());
	
	return nextCard();
    }
    
    /** Called to perform nextCard functions that don't need a Choice (in the constructor and in nextCard original)
      * 
      * @return  If the deck still has cards.
      **/    
    public boolean nextCard(){
	// Remove empty sequences at front
	while (deck.size() > 0 && deck.get(0).size() == 0)
	    deck.remove(0);
	
	// Signals a game over
	if (deck.size() == 0)
	    return false;
	
	// Add next card from front of deck
	currentCard = deck.get(0).remove(0);
	return true;
    }
    
    /** Shuffles deck.**/
    public void shuffle(){
	List<List<Card>> temp = new ArrayList<List<Card>>();
	while (deck.size() > 0)
	    temp.add(deck.remove((int)(Math.random()*deck.size())));
	deck = temp;
    }
    
    /** The card currently in play.
      * 
      * @return  Value of currentCard.
      **/
    public Card getCurrentCard(){ return currentCard;}
    
    /** Accessor for the deck of cards
      * 
      * @return     The deck of Cards
      **/
    public List<List<Card>> getDeck(){ return deck;}
    
    /** toString overload
      *
      * @return A string representation of the object
      **/
    @Override
    public String toString() { return "" + deck;}
}
