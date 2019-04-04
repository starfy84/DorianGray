/**
  * Author(s): Andy Pham
  * Version: 1.5
  * Date: June 5, 2018
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
    private boolean[] hasEffect;         // Represents the bars this Card effects (Health, Happiness, Self-Esteem, Achievement)
    private int[] effectAmount;          // Represents how much the bar is affected (eg. -20 or 5)
    private List<Card> nextCards;        // Stores cards that appear as a result of this choice.
    
    /** Constructor
      * 
      * @param text           Preview text for choice
      * @param hasEffect      Represents the bars this Card effects (Health, Happiness, Self-Esteem, Achievement)
      * @param effectAmount   Represents how much the bar is affected (eg. -20 or 5)
      * @param nextCards      Stores cards that appear as a result of this choice.
      **/
    public Choice(String text, boolean[] hasEffect, int[] effectAmount, List<Card> nextCards){
        this.text = text;
        this.hasEffect = hasEffect;
        this.effectAmount = effectAmount;
        this.nextCards = nextCards;
    }
    
    /** Overloaded constructor for choices that have no effect on the game (simply to confirm the results of cards).
      * 
      * @param text  Preview text for choice.
      **/
    public Choice(String text){
        this.text = text;
        this.hasEffect = new boolean[4];
        this.effectAmount = new int[4];
        this.nextCards = new ArrayList<Card>();
    }
    
    /** Accessor method
      * 
      * @return  Preview text of choice.
      **/
    public String getText(){ return text;}
    
    /** Accessor method
      * 
      * @return  Array of aspects effected for choice
      **/
    public boolean[] getHasEffect(){ return hasEffect;}
    
    /** Accessor method
      * 
      * @return  Array of effect amounts per aspect for choice
      **/
    public int[] getEffectAmount(){ return effectAmount;}
    
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

    /** Creates and returns a string representation of this object
      * 
      * @return   The string representation of the object
      **/
    @Override
    public String toString(){
        String s = "Choice:"+nextCards+":";
        for (int i = 0; i < 4; i++)
            s += hasEffect[i] + " ";
        s += ":";
        for (int i = 0; i < 4; i++)
            s += effectAmount[i] + " ";
        return s;
    }
}