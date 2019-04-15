/**
 * Author(s): Matthew Mach, Dereck Tu, William Xu
 * Version: 2.0
 * Date: April 07, 2019
  *
  * Modifications:
  * - Removed attribute field
  * - Added fileName field
  * - Added accessors and mutators for leftChoice and rightChoice
  * - Added accessors for fileName, cardFront, and cardBack
  * - Removed cardBack instance variable and everything associated 
  * - Removed flip code; not needed in this code
  * - Removed unintentional parameters from accessor methods
  * - Modified toString for ease of debugging
  *
  * Description:
  * This class represents a Card in-game.
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

public class Card {
  private String text; //Dialogue
  private String name; //Name of Person
  private String background;
  private String type;
  private ImageView cardFront;   // Self explanatory; images used for the card front and back  
  private boolean hasChoice;
  private Choice left;
  private Choice right;
    /** Constructor
      * 
      * @param fileName  The filename of the card. This will access a text file with the matching name and create the
      *     card accordingly.
      * @param cardFront The image of the front face. Might be shared between cards, so created in DeckImageGenerator.
      * @param cardBack  The image of the back face. Might be shared between cards, so created in DeckImageGenerator.
      **/

    public Card (String type, String name, String text, String background, ImageView cardFront, Choice left, Choice right){
      this.type = type;
      this.name = name;
      this.text = text;
      this.background = background;
      this.cardFront = cardFront;
      this.left = left;
      this.right = right;
      hasChoice = !(left.getText().equals("") && right.getText().equals(""));
    }

    public Choice getLeftChoice(){
      return left;
    }
    public Choice getRightChoice(){
      return right;
    }
    /** Accessor for text.
      *
      * @return  Value of text
      **/
    public String getText() { return this.text;}

    public boolean getHasChoice(){ return this.hasChoice;}
    /** Accessor for name.
      *
      * @return  Value of name
      **/
    public String getName() { return this.name;}

    /** Accessor for cardFront.
      *
      * @return  Value of cardFront.
      **/
    public ImageView getCardFront() { return cardFront;}

    public String toString(){
      return text;
    }
    
  }