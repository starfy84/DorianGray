/**
  * Author(s): Andy Pham and Matthew Mach
  * Version: 1.6
  * Date: June 4, 2018
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
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class DeckGenerator {
    //Instance Variables
    private static final String[] FILLERS = {"Ok.","Okay.","Sure.","Alright.","That's okay.","Whatever."};
    private static final boolean DEBUG = true;

    private String deckName;   // Will be tutorial, childhood, middleschool, or highschool (just the level name, no file location info)
    
    private HashMap<String,Card> cardHash, tempCardHash;
    private HashMap<String,Choice> choiceHash;
    private HashMap<String,ImageView> imageHash;
    
    private ImageView resultImage;

    /** Constructor 
      *
      * @param deckName The name of the Deck
      **/
    public DeckGenerator (String deckName){
        this.deckName = deckName;
        this.cardHash = new HashMap<String,Card>();
        this.tempCardHash = new HashMap<String,Card>();
        this.choiceHash = new HashMap<String,Choice>();
        this.imageHash = new HashMap<String,ImageView>();
        
        this.resultImage = createImage("/Images/card/front/konscench_"+deckName+".png");
        this.imageHash.put("RESULT", resultImage);
    }

    /** Generates the Deck from deckName file and sets up needed images.
      *
      * @return A List of List of Card representing all the card sequences generated.
      **/
    public List<List<Card>> getDeck(){
        List<List<Card>> d = new ArrayList<List<Card>>();
        List<Card> seq;
        System.out.println (DEBUG);
      System.out.println("debug:"+DEBUG);
        if (DEBUG)
            System.out.println("getDeck");
        // Create cards from Deck file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Resources/"+deckName+"/deck.txt"));
            
            // Generate ending cards
            seq = new ArrayList<Card>();
            // Read result descriptions
            seq.add(createEndingCard(reader.readLine(),"Game Over"));
            seq.add(createEndingCard(reader.readLine(),"Game Over"));
            seq.add(createEndingCard(reader.readLine(),"Game Over"));
            seq.add(createEndingCard(reader.readLine(),"Game Over"));
            seq.add(createEndingCard(reader.readLine(),"Success"));
            
            d.add(seq);

            int lines, cards;
            String r = "";
            try {
                r = reader.readLine();
                
                // Create all cards
                lines = Integer.parseInt(r);
                
                for (int i = 0; i < lines; i++){
                    String line = reader.readLine();
                            
                    if (DEBUG){
                        System.out.println("Line("+i+"):"+line);
                        System.out.println("    Resources/" + deckName + "/card/" + line + ".txt");
                    }
                    createCard("Resources/" + deckName + "/card/" + line + ".txt");
                }
                
                if (DEBUG)
                    System.out.println("\nALL CARDS MADE.\n");
                
                // Create all card sequences
                r = reader.readLine();
                lines = Integer.parseInt(r);
                
                for (int i = 0; i < lines; i++){
                    seq = new ArrayList<Card>();

                    try{
                        cards = Integer.parseInt(reader.readLine());
                        for (int j = 0; j < cards; j++) {
                            String line = reader.readLine();
                            
                            if (DEBUG){
                                System.out.println("Line("+i+","+j+") :"+line);
                                System.out.println("    Resources/" + deckName + "/card/" + line + ".txt");
                            }
                            seq.add(cardHash.get("Resources/" + deckName + "/card/" + line + ".txt"));
                        }
                    } catch (NumberFormatException e){ System.out.println("seq parse failed");}

                    d.add(seq);
                }
                
                if (DEBUG)
                    System.out.println("    Deck:" + d);
            } catch (NumberFormatException e){System.out.println("lines parse failed:"+r);}

            reader.close();
        } catch (IOException e) { System.out.println("getDeck: file io failed" + DEBUG);}

        // Get a set of the entries in cardHash and create all choices
        if (DEBUG)
            System.out.println("\nHASH PORTION");
        try{
            Set set = cardHash.keySet();
            if (DEBUG)
                System.out.println("    Key Set:"+set);
            
            // Get an iterator
            Iterator i = set.iterator();
            
            // Display elements
            String key;
            while(i.hasNext()) {
                key = (String)i.next();
                if (DEBUG)
                    System.out.println("        Create choices:"+key+":"+cardHash.get(key));
                createChoices(cardHash.get(key));
            }
        } catch (Exception e) {System.out.println(e);}
        
        if (DEBUG)
            System.out.println("\nGeneration complete.\n");
        
        return d;
    }
    
    /** Converts text into a filename (without an extension) representing the text by rmeoving special characters.
      * 
      * @param text  Text to be converted
      * @return  The text with all special (non-alphanumeric) characters converted to '_'
      **/
    private static String cleanText(String text){
        if (DEBUG)
            System.out.print("                                  cleanText:"+text);
        String clean = "";
        char ch;
        for (int i = 0; i < text.length(); i++) {
            ch = text.charAt(i);
            if ((48 <= ch && ch <= 57) || (65 <= ch && ch <= 90) || (97 <= ch && ch <= 122))
                clean += ch;
            else
                clean += "_";
        }
        if (DEBUG)
            System.out.print("-->"+clean);
        return clean;
    }
    
    /** Returns String chosen randomly from filler texts (Ok, Sure, Alright, etc.)
      * 
      * @return A String.
      **/
    private static String getFillerText(){
        return FILLERS[(int)(Math.random()*FILLERS.length)];
    }

    /** Creates an ImageView object from a filename and adds to imageHash.
      *
      * @param fileName  The .txt file where the card is stored (eg. "/Resources/card/middleschool/esteem1.txt")
      **/
    public ImageView createImage(String fileName){
        if (DEBUG)
            System.out.println("            createImage:"+fileName);
        
        if (imageHash.containsKey(fileName))
            return imageHash.get(fileName);
        
        // Add to hash
        try {
            ImageView i = new ImageView(new Image(fileName));
            imageHash.put(fileName, i);
            return i;
        }catch (Exception e){ System.out.println("IMAGE IO FAIL:"+fileName+":"+e);}
        return null;
    }

    /** Creates a Card object from a filename and adds to cardHash.
      * 
      * @param fileName  The .txt file where the card is stored (eg. "/Resources/card/middleschool/esteem1.txt")
      **/
    public Card createCard(String fileName){
        if (DEBUG)
            System.out.println("\n       createCard: "+fileName);
        
        if (cardHash.containsKey(fileName))
            return cardHash.get(fileName);

        // Read text and name from file
        String text = "", name = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            text = reader.readLine();
            name = reader.readLine();
            
            if (DEBUG)
                System.out.println("text:"+text+":name:"+name);

            reader.close();
        } catch (Exception e) { System.out.println("create card: error: "+e);}

        Card c = new Card(
                fileName, text, name,
                createImage("/Images/card/front/" + cleanText(name.toLowerCase()) + "_" + deckName + ".png")
        );
        cardHash.put(fileName, c);
        return c;
    }

    /** Creates a Card object from a filename and adds to cardHash - but it's a result card so it has less information.
      * 
      * @param resultText  The text on the result card. The image is just this text rendered.
      **/
    public Card createResultCard(String resultText){     
        if (DEBUG)
            System.out.println("                                  createResultCard:"+resultText);
        // Read text and name from file
        String clean = cleanText(resultText);
        Card c = new Card(clean,resultText,"Result",resultImage);
        c.setLeftChoice(new Choice(getFillerText()));
        c.setRightChoice(new Choice(getFillerText()));
        tempCardHash.put(clean, c);
        
        return c;
    }
    

    /** Creates an ending card that's not added to cardHash.
      * 
      * @param resultText  The text on the result card. The image is just this text rendered.
      * @param typ         The type of ending
      **/
    public Card createEndingCard(String resultText, String typ){
        if (DEBUG)
            System.out.println("                                  createResultCard:"+resultText);
        // Read text and name from file
        String clean = cleanText(resultText);
        Card c = new Card(
             clean,resultText,typ,resultImage
        );
        c.setLeftChoice(new Choice(getFillerText()));
        c.setRightChoice(new Choice(getFillerText()));
        tempCardHash.put(clean, c);
        
        return c;
    }
    
    /** Creates the Choice objects for a Card object.
      * 
      * @param c  The Card whose Choices will be created.
      * 
      * Precondition: All Cards for the level (including nextCards, excluding result cards) have been initialized and are in cardHash.
      **/
    public void createChoices(Card c){
        if (DEBUG)
            System.out.println("\n\nCREATE CHOICES: "+c);

        BufferedReader reader;
        String choiceText, resultText, fileName;
        int cardNum = 0;
        Image choiceImage;
        boolean[] hasEffect;
        int[] effectAmount;
        List<Card> nextCards = new ArrayList<Card>();
        Choice leftChoice = null, rightChoice = null;

        try{
            fileName = "Resources/" + c.getFileName().substring(10,c.getFileName().indexOf("/card")) + "/choice/";
            fileName += c.getFileName().substring(c.getFileName().indexOf("card/")+5,c.getFileName().length()-4)+"_0.txt";
            if (DEBUG)
                System.out.println("                fileName:"+fileName);
            reader = new BufferedReader (new FileReader (fileName));

            choiceText = reader.readLine();
            if (choiceText.equals("-"))
                choiceText = getFillerText();
            resultText = reader.readLine();
            
            if (DEBUG)
                System.out.println("                    Choice Info:"+choiceText + " " + resultText);

            // Read info for effects
            hasEffect = new boolean[4];
            effectAmount = new int[4];
            for (int i = 0; i < 4; i++){
                hasEffect[i] = reader.readLine().equals("T");
                try{
                    effectAmount[i] = Integer.parseInt(reader.readLine());
                }catch (NumberFormatException e){System.out.println("effectAmount parse fail");}
            }
            if (DEBUG)
                for (int i = 0; i < 4; i++)
                    System.out.println("                       EFFECT:["+hasEffect[i]+","+effectAmount[i]+"]");

            // Read nextCards
            try{
                cardNum = Integer.parseInt(reader.readLine());
                nextCards = new ArrayList<Card>();
                nextCards.add(createResultCard(resultText));
                String a;
                for (int i = 0; i < cardNum; i++){
                    a = reader.readLine();
                    Card ca = cardHash.get("Resources/" + deckName + "/card/" + a + ".txt");
                    nextCards.add(ca);
                }
            }catch (NumberFormatException e){System.out.println("cardNum parse fail");}
            
            if (DEBUG)
                System.out.println("                            Next Cards:"+cardNum+","+nextCards);
            
            reader.close ();

            leftChoice = new Choice(choiceText, hasEffect, effectAmount, nextCards);
            choiceHash.put(fileName, leftChoice);
            
            if (DEBUG)
                System.out.println(leftChoice);

        }catch (IOException e){System.out.println("file io fail");}

        try{
            fileName = "Resources/" + c.getFileName().substring(10,c.getFileName().indexOf("/card")) + "/choice/";
            fileName += c.getFileName().substring(c.getFileName().indexOf("card/")+5,c.getFileName().length()-4)+"_1.txt";
            if (DEBUG)
                System.out.println("                fileName:"+fileName);
            reader = new BufferedReader (new FileReader (fileName));

            choiceText = reader.readLine();
            if (choiceText.equals("-"))
                choiceText = getFillerText();
            resultText = reader.readLine();
            
            if (DEBUG)
                System.out.println("                    Choice Info:"+choiceText + " " + resultText);

            // Read info for effects
            hasEffect = new boolean[4];
            effectAmount = new int[4];
            for (int i = 0; i < 4; i++){
                hasEffect[i] = reader.readLine().equals("T");
                try{
                    effectAmount[i] = Integer.parseInt(reader.readLine());
                }catch (NumberFormatException e){System.out.println("effectAmount parse fail");}
            }
            if (DEBUG)
                for (int i = 0; i < 4; i++)
                    System.out.println("                       EFFECT:["+hasEffect[i]+","+effectAmount[i]+"]");

            // Read nextCards
            try{
                cardNum = Integer.parseInt(reader.readLine());
                nextCards = new ArrayList<Card>();
                nextCards.add(createResultCard(resultText));
                String a;
                for (int i = 0; i < cardNum; i++){
                    a = reader.readLine();
                    Card ca = cardHash.get("Resources/" + deckName + "/card/" + a + ".txt");
                    nextCards.add(ca);
                }
            }catch (NumberFormatException e){System.out.println("cardNum parse fail");}
            
            if (DEBUG)
                System.out.println("                            Next Cards:"+cardNum+","+nextCards);
            
            reader.close ();

            rightChoice = new Choice(choiceText, hasEffect, effectAmount, nextCards);
            choiceHash.put(fileName, rightChoice);
            
            if (DEBUG)
                System.out.println(rightChoice+"\n");

        }catch (IOException e){System.out.println("file io fail");}
        
        c.setLeftChoice(leftChoice);
        c.setRightChoice(rightChoice);
    }

    /** Accessor for imageHash
      * 
      * @return  A hash storing all images related to the deck.
      **/
    public HashMap<String,ImageView> getImageHash() {return imageHash;}

    /** Accessor for cardHash
     * 
     * @return  A hash storing all cards in the deck.
     */
    public HashMap<String,Card> getCardHash() {return cardHash;}
}