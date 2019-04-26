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
        super.gameDeck = Const.DECK_CACHE.get("Act2:2:1-Act2:2:4");
        add(super.gameDeck,Const.DECK_CACHE.get("Act2:3:1-Act2:3:16"),Const.DECK_CACHE.get("Act2:3:18-Act2:3:28"),Const.DECK_CACHE.get("Act2:3:30-Act2:3:51"),Const.DECK_CACHE.get("Act2:3:54-Act2:3:56"),Const.DECK_CACHE.get("Act2:0"),Const.DECK_CACHE.get("Act2:4:1-Act2:4:2"),Const.DECK_CACHE.get("Act2:4:4-Act2:4:9"),Const.DECK_CACHE.get("Act2:4:11-Act2:4:11"),Const.DECK_CACHE.get("Act2:4:13-Act2:4:27"),Const.DECK_CACHE.get("Act2:5:1-Act2:5:2"),Const.DECK_CACHE.get("Act2:5:5-Act2:5:6"),Const.DECK_CACHE.get("Act2:5:10-Act2:5:11"),Const.DECK_CACHE.get("Act2:5:14-Act2:5:16"),Const.DECK_CACHE.get("Act2:5:18-Act2:5:20"),Const.DECK_CACHE.get("Act2:5:22-Act2:5:25"),Const.DECK_CACHE.get("Act2:5:27-Act2:5:34"),Const.DECK_CACHE.get("Act2:5:37-Act2:5:62"),Const.DECK_CACHE.get("Act2:5:64-Act2:5:64"));
      }
      else if(lastActResult == 'B'){
        super.gameDeck = Const.DECK_CACHE.get("Act2:0");
        add(super.gameDeck,Const.DECK_CACHE.get("Act2:1"),Const.DECK_CACHE.get("Act2:2"),Const.DECK_CACHE.get("Act2:3"),Const.DECK_CACHE.get("Act2:4"),Const.DECK_CACHE.get("Act2:5"),Const.DECK_CACHE.get("Act2:6"),Const.DECK_CACHE.get("Act2:7"),Const.DECK_CACHE.get("Act2:8"));
      }
    }
    else if(super.state.equals("Main")){
      if(!scene6){
        if(lastActResult == 'G'){
          if(super.choices.length() == 130){
            if(super.choices.charAt(129) == 'L'){
              scene6 = true;
              currLen = 130;
              add(super.gameDeck,Const.DECK_CACHE.get("Act2:6:1-Act2:6:7"));
            }
            else if(super.choices.charAt(129) == 'R'){
              add(super.gameDeck,Const.DECK_CACHE.get("Act2:5:65-Act2:5:66"));
            }
          }
          else if(super.choices.length() == 132){
            scene6 = true;
            currLen = 132;
            add(super.gameDeck,Const.DECK_CACHE.get("Act2:6:1-Act2:6:7"));
          }
        }
        else if(lastActResult == 'B'){
          if(super.choices.length() == 148){
            scene6 = true;
            currLen = 148;
            add(super.gameDeck,Const.DECK_CACHE.get("Act2:6:1-Act2:6:7"));
          }
        }
      }
      else if(scene6){
        if(super.choices.length()-currLen == 7){
          if(super.choices.charAt(super.choices.length()-1) == 'L'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act2:6:8-Act2:6:8"),Const.DECK_CACHE.get("Act2:6:10-Act2:6:12"));
          }
          else if (super.choices.charAt(super.choices.length()-1) == 'R'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act2:6:9-Act2:6:12"));
            goodTally++;
            if(Const.GAME_DEBUG)
              System.out.println("Tally went up: "+goodTally);
          }
        }
        else if(super.choices.length()-currLen == 11){
          if(super.choices.charAt(super.choices.length()-1) == 'L'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act2:6:13-Act2:6:13"));
          }
          else if (super.choices.charAt(super.choices.length()-1) == 'R'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act2:6:14-Act2:6:16"));
            goodTally++;
            if(Const.GAME_DEBUG)
              System.out.println("Tally went up: "+goodTally);
          }
          if(lastActResult == 'B'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act2:6:17-Act2:6:17"),Const.DECK_CACHE.get("Act2:6:19-Act2:6:21"));
          }
          else if(lastActResult == 'G'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act2:6:18-Act2:6:21"));
          }
        }
        else if(super.choices.length()-currLen == 18){
          if(super.choices.charAt(super.choices.length()-1) == 'L'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act2:6:22-Act2:6:22"),Const.DECK_CACHE.get("Act2:6:24-Act2:6:35"));
            goodTally++;
            if(Const.GAME_DEBUG)
              System.out.println("Tally went up: "+goodTally);
          }
          else if (super.choices.charAt(super.choices.length()-1) == 'R'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act2:6:23-Act2:6:35"));
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