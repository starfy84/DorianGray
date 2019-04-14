/**
 * Author(s): Matthew Mach, Dereck Tu, William Xu
 * Version: 2.0
 * Date: April 07, 2019
  *
  * Modifications:
  * - Changed nextCards to List</Card> for flexibility.
  * - Added imports for List and ArrayList
  * - Changed textImage to String named text
  *
  * Description:
  * This class represents a Choice the player can make. It contains the effect of the choice and the Cards related to the
  * choice that will appear afterwards.
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

public class Choice {
    private String text;                 // Preview text describing choice
    private List<Card> nextCards;        // Stores cards that appear as a result of this choice.
    
    /** Constructor
      * 
      * @param text           Preview text for choice
      * @param nextCards      Stores cards that appear as a result of this choice.
      **/
    public Choice(String text, List<Card> nextCards){
      this.text = text;
      this.nextCards = nextCards;
    }
    
    /** Overloaded constructor for choices that have no effect on the game (simply to confirm the results of cards).
      * 
      * @param text  Preview text for choice.
      **/
    public Choice(String text){
      this.text = text;
      this.nextCards = new ArrayList<Card>();
    }
    
    /** Accessor method
      * 
      * @return  Preview text of choice.
      **/
    public String getText(){ return text;}
    
    
    /** Accessor method
      * 
      * @return  Cards to be placed in Deck after choice for choice
      **/
    public List<Card> getNextCards(){ return nextCards;}
    
    /** Mutator method that removes first card from nextCards.
      * 
      * @return First card from nextCards.
      **/
    public Card getNextCard(){ return nextCards.remove(0);}
  }