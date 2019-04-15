/**
 * Author(s): Matthew Mach, Dereck Tu, William Xu
 * Version: 2.0
 * Date: April 07, 2019
  *
  * Modifications:
  * - Removed textImageHash
  * - Removed cardBack from assigning to cards
  * - Removed all methods relating to text images (createTextImage and createResultTextImage)
  * - Added default result image
  * 
  * Goals:
  * - createTextImage: Render text and save in image pool
  *     - Implement image pool
  *     - Maybe add alignment?
  * - createResultTextImage: Same as above, but text covers card and is centered
  *
  * Description:
  * The main purpose of this class is to organize image generation code and 
  * reduce the amount of times an image is initialize. This should help 
  * organization and run time. Used entirely for deck image generation.
  *
  * Image format:
  *     Cards: /card/(front or back)/Name_deck.png
  *     Text: /text/cleanedText.png
  **/

import javafx.scene.image.*;
import java.io.*;
import java.util.*;

public class DeckGenerator {

    private String deckName;

    /** Constructor 
      *
      * @param deckName The name of the Deck
      **/
    public DeckGenerator (String deckName){
      this.deckName = deckName;
    }
    public Deck genDeck(int bScene, int bCard, int eScene, int eCard){
      Deck d = new Deck(deckName);
      int card = bCard;
      for(int scene = bScene; scene <= eScene; scene++){
        while(card <= d.getCardsPerScene()[scene-1]){
          d.getDeck().add(Const.CARD_CACHE.get(deckName + ":"+scene+":"+card));
          if(scene >= eScene && card >= eCard)
            break;
          card++;
        }
        card = 1;
      }
      return d;
    }
  }