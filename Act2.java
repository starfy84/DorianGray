import javafx.scene.image.*;
import java.io.*;
import java.util.*;
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
public class Act2 extends Game{
  private char lastActResult;
  private boolean scene6;
  private int currLen , goodTally;
  public Act2(){
    super("Act2","richpersonhouse");
    scene6 = false;
    currLen = 0;
    goodTally = 0;
  }
  private Deck gen(int bScene, int bCard, int eScene,int eCard){
    return super.deckGenerator.genDeck(bScene,bCard,eScene,eCard);
  }
  private void add(Deck d,Deck...l){
    for(Deck x:l)
      d.getDeck().addAll(x.getDeck());
  }
  public void addToDeck(){
    if(super.state.equals("Init")){
      String s = "";
      try{
        BufferedReader in = new BufferedReader(new FileReader("saves/Act1"));
        s = in.readLine();
      }catch(IOException e){}
      lastActResult = s.charAt(s.length()-1);
      if(lastActResult == 'G'){
        super.gameDeck = gen(2,1,2,4);
        add(super.gameDeck,gen(3,1,3,16),gen(3,18,3,28),gen(3,30,3,51),gen(3,54,3,56),gen(4,1,4,2),gen(4,4,4,9),gen(4,11,4,11),gen(4,13,4,27),gen(5,1,5,2),gen(5,5,5,6),gen(5,10,5,11),gen(5,14,5,16),gen(5,18,5,20),gen(5,22,5,25),gen(5,27,5,34),gen(5,37,5,62),gen(5,65,5,64));
      }
      else if(lastActResult == 'B'){
        super.gameDeck = gen(1,1,1,8);
        add(super.gameDeck,gen(3,1,3,1),gen(3,4,3,54),gen(3,57,3,57),gen(4,1,4,10),gen(4,12,4,27),gen(5,1,5,15),gen(5,17,5,21),gen(5,23,5,63));
      }
    }
    else if(super.state.equals("Main")){
      if(!scene6){
        if(lastActResult == 'G'){
          if(super.choices.length() == 130){
            if(super.choices.charAt(129) == 'L'){
              scene6 = true;
              currLen = 130;
              add(super.gameDeck,gen(6,1,6,7));
            }
            else if(super.choices.charAt(129) == 'R'){
              add(super.gameDeck,gen(5,65,5,66));
            }
          }
          else if(super.choices.length() == 132){
            scene6 = true;
            currLen = 132;
            add(super.gameDeck,gen(6,1,6,7));
          }
        }
        else if(lastActResult == 'B'){
          if(super.choices.length() == 148){
            scene6 = true;
            currLen = 148;
            add(super.gameDeck,gen(6,1,6,7));
          }
        }
      }
      else if(scene6){
        if(super.choices.length()-currLen == 7){
          if(super.choices.charAt(super.choices.length()-1) == 'L'){
            add(super.gameDeck,gen(6,8,6,8),gen(6,10,6,12));
          }
          else if (super.choices.charAt(super.choices.length()-1) == 'R'){
            add(super.gameDeck,gen(6,9,6,12));
            goodTally++;
            if(Const.GAME_DEBUG)
              System.out.println("Tally went up: "+goodTally);
          }
        }
        else if(super.choices.length()-currLen == 11){
          if(super.choices.charAt(super.choices.length()-1) == 'L'){
            add(super.gameDeck,gen(6,13,6,13));
          }
          else if (super.choices.charAt(super.choices.length()-1) == 'R'){
            add(super.gameDeck,gen(6,14,6,16));
            goodTally++;
            if(Const.GAME_DEBUG)
              System.out.println("Tally went up: "+goodTally);
          }
          if(lastActResult == 'B'){
            add(super.gameDeck,gen(6,17,6,17),gen(6,19,6,21));
          }
          else if(lastActResult == 'G'){
            add(super.gameDeck,gen(6,18,6,21));
          }
        }
        else if(super.choices.length()-currLen == 18){
          if(super.choices.charAt(super.choices.length()-1) == 'L'){
            add(super.gameDeck,gen(6,22,6,22),gen(6,24,6,35));
            goodTally++;
            if(Const.GAME_DEBUG)
              System.out.println("Tally went up: "+goodTally);
          }
          else if (super.choices.charAt(super.choices.length()-1) == 'R'){
            add(super.gameDeck,gen(6,23,6,35));
          }
        }
      }
    }
  }
  public void conditional(){
    try{
      BufferedReader in = new BufferedReader(new FileReader("saves/Act2"));
      String s = in.readLine();
      PrintWriter out = new PrintWriter(new FileWriter("saves/Act2",true));
      if(goodTally >= 2){
        out.print("G");
      }
      else{
        out.print("B");
      }
      out.close();
    }catch(Exception e){}
  }
  public void sceneChange(){
    if(super.currentCard.scene.equals("5") && super.currentCard.cardn.equals("1")){
        FadeTransition fa = new FadeTransition(Duration.millis(3000),background);
        fa.setFromValue(1f);
        fa.setToValue(0f);
        fa.setAutoReverse(true);
        fa.setCycleCount(1);

              FadeTransition ta = new FadeTransition(Duration.millis(3000),background);
      ta.setFromValue(0f);
      ta.setToValue(1f);
      ta.setAutoReverse(true);
      ta.setCycleCount(1);

              fa.setOnFinished(new EventHandler<ActionEvent>() {
       @Override
       public void handle(ActionEvent t) {
        background.setImage(new Image("Images/game/backgrounds/slums.png"));
         ta.play();
       }
     });
      fa.play();
    }
    else if(super.currentCard.scene.equals("6") && super.currentCard.cardn.equals("1")){
        FadeTransition fa = new FadeTransition(Duration.millis(3000),background);
        fa.setFromValue(1f);
        fa.setToValue(0f);
        fa.setAutoReverse(true);
        fa.setCycleCount(1);

              FadeTransition ta = new FadeTransition(Duration.millis(3000),background);
      ta.setFromValue(0f);
      ta.setToValue(1f);
      ta.setAutoReverse(true);
      ta.setCycleCount(1);

              fa.setOnFinished(new EventHandler<ActionEvent>() {
       @Override
       public void handle(ActionEvent t) {
        background.setImage(new Image("Images/game/backgrounds/richpersonhouse.png"));
         ta.play();
       }
     });
      fa.play();
    }
  }
}