/**
  * Author(s): Matthew Mach, Andy Pham
  * Version: 1.6
  * Date: June 5, 2018
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
    private String text;
    private String name;
    private String fileName;
    /* Text is the description of problem, the thing Derek must make a choice on
       Name is the name of the person presenting the choice
       Filename is the filename storing the card's info. Also used to reference card as a key in DeckImageGenerator's cardHash
    */
    private ImageView cardFront;   // Self explanatory; images used for the card front and back  
    private Choice leftChoice, rightChoice;  // Choices that Derek can make by choosing the left or right 
    
    /** Constructor
      * 
      * @param fileName  The filename of the card. This will access a text file with the matching name and create the
      *     card accordingly.
      * @param cardFront The image of the front face. Might be shared between cards, so created in DeckImageGenerator.
      * @param cardBack  The image of the back face. Might be shared between cards, so created in DeckImageGenerator.
      **/
    public Card (String fileName, String text, String name, ImageView cardFront){
        this.fileName = fileName;
        this.cardFront = cardFront;
        this.text = text;
        this.name = name;
    }

    /** Mutator for leftChoice.
      * 
      * @param c  New value of leftChoice
      **/
    public void setLeftChoice(Choice c){ this.leftChoice = c;}
    
    /** Mutator for rightChoice.
      * 
      * @param c  New value of rightChoice
      **/
    public void setRightChoice(Choice c){ this.rightChoice = c;}
    
    /** Accessor for leftChoice.
      * 
      * @return  Value of leftChoice
      **/
    public Choice getLeftChoice(){ return this.leftChoice;}
    
    /** Accessor for rightChoice.
      * 
      * @return  Value of rightChoice
      **/
    public Choice getRightChoice(){ return this.rightChoice;}

    /** Accessor for text.
      *
      * @return  Value of text
      **/
    public String getText() { return this.text;}

    /** Accessor for name.
      *
      * @return  Value of name
      **/
    public String getName() { return this.name;}

    /** Accessor for fileName.
      *
      * @return  Value of fileName.
      **/
    public String getFileName() { return fileName;}

    /** Accessor for cardFront.
      *
      * @return  Value of cardFront.
      **/
    public ImageView getCardFront() { return cardFront;}

    /** Creates and returns a string representation of this object
      * 
      * @return   The string representation of the object
      **/
    @Override
    public String toString(){
        return "(Card:"+fileName+")";
    }
    
}