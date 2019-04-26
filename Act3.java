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
public class Act3 extends Game{
  private char act1Res,act2Res;
  public Act3(){
    super("Act3","theatre");

  }
  private void add(Deck d,Deck...l){
    for(Deck x:l)
      d.getDeck().addAll(x.getDeck());
  }
  private Deck gen(int bScene, int bCard, int eScene, int eCard){
    return super.deckGenerator.genDeck(bScene,bCard,eScene,eCard);
  }
  public void addToDeck(){
    if(super.state.equals("Init")){
      String s = "";
      try{
        BufferedReader in = new BufferedReader(new FileReader("saves/Act1"));
        s = in.readLine();
        act1Res = s.charAt(s.length()-1);
        in = new BufferedReader(new FileReader("saves/Act2"));
        s = in.readLine();
        act2Res = s.charAt(s.length()-1);
      }catch(IOException e){}
      if(act1Res=='B' && act2Res == 'B'){
        super.gameDeck = Const.DECK_CACHE.get("Act3:BB BG Init");
        add(super.gameDeck,Const.DECK_CACHE.get("Act3:1:42-Act3:1:62"),Const.DECK_CACHE.get("Act3:2:1-Act3:2:66"),Const.DECK_CACHE.get("Act3:4:1-Act3:4:19"));
      }
      else if(act1Res == 'G' && act2Res == 'G'){
        super.gameDeck = Const.DECK_CACHE.get("Act3:GG Init");
        add(super.gameDeck,Const.DECK_CACHE.get("Act3:1:6-Act3:1:38"),Const.DECK_CACHE.get("Act3:1:40-Act3:1:61"),Const.DECK_CACHE.get("Act3:1:63-Act3:1:63"));
      }
      else{
        super.gameDeck = Const.DECK_CACHE.get("Act3:BB BG Init");
        add(super.gameDeck,Const.DECK_CACHE.get("Act3:1:42-Act3:1:61"),Const.DECK_CACHE.get("Act3:1:63-Act3:1:63"));
      }
      
    }
    else if(super.state.equals("Main")){
      if((act1Res == 'B' && act2Res == 'G') || (act1Res == 'G' && act2Res == 'B')){
        if(super.choices.length() == 68){
          if(super.choices.charAt(65) == 'R' && super.choices.charAt(67) == 'L'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act3:3:32-Act3:3:32"));          
          }
          else{
            add(super.gameDeck,Const.DECK_CACHE.get("Act3:33-Act3:3:68"),Const.DECK_CACHE.get("Act3:4:1-Act3:4:19"));
          }
        }
        else if(super.choices.length() == 91){
          if(super.choices.charAt(90) == 'L'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act3:3:69-Act3:3:74"),Const.DECK_CACHE.get("Act3:5:1-Act3:5:21"));
          }
          else if(super.choices.charAt(90) == 'R'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act3:33-Act3:3:68"),Const.DECK_CACHE.get("Act3:4:1-Act3:4:19"));
          }
        }
        else if(super.choices.length() == 60){
          if(super.choices.charAt(59) == 'L'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act3:3:1-Act3:3:30"));
          }
          else if(super.choices.charAt(59) == 'R'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act3:2:1-Act3:2:66"),Const.DECK_CACHE.get("Act3:4:1-Act3:4:19"));
          }
        } 
      }
      else if(act1Res == 'G' && act2Res == 'G'){
        if(super.choices.length() == 68){
          if(super.choices.charAt(65) == 'R' && super.choices.charAt(67) == 'L'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act3:3:32-Act3:3:32"));
          }
          else{
            add(super.gameDeck,Const.DECK_CACHE.get("Act3:33-Act3:3:68"),Const.DECK_CACHE.get("Act3:4:1-Act3:4:19"));
            
          }
        }
        else if(super.choices.length() == 91){
          if(super.choices.charAt(90) == 'L'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act3:3:69-Act3:3:74"),Const.DECK_CACHE.get("Act3:6:1-Act3:6:18"));
          }
          else if(super.choices.charAt(90) == 'R'){
            add(super.gameDeck,Const.DECK_CACHE.get("Act3:33-Act3:3:68"),Const.DECK_CACHE.get("Act3:4:1-Act3:4:19"));
          }
        }
              else if(super.choices.length() == 60){
        if(super.choices.charAt(59) == 'L'){
          add(super.gameDeck,Const.DECK_CACHE.get("Act3:3:1-Act3:3:30"));
        }
        else if(super.choices.charAt(59) == 'R'){
          add(super.gameDeck,Const.DECK_CACHE.get("Act3:2:1-Act3:2:66"),Const.DECK_CACHE.get("Act3:4:1-Act3:4:19"));
        }
      }
      }
    }
  }
  public void conditional(){
    try{
      BufferedReader in = new BufferedReader(new FileReader("saves/Act3"));
      String s = in.readLine();
      PrintWriter out = new PrintWriter(new FileWriter("../saves/Act3",true));
      if(s.length() == 117 || s.length() == 120){
        out.print("G");
      }
      else{
        out.print("B");
      }
      out.close();
    }catch(Exception e){}
  }
  public void sceneChange(){
    if(super.currentCard.scene.equals("4")){
      bg("Images/game/backgrounds/richpersonhouse.png");
    }
    else if(super.currentCard.scene.equals("5")){
      if(super.currentCard.cardn.equals("1")){
        bg("Images/game/backgrounds/garden.png");
      }
      else if(super.currentCard.cardn.equals("3")||super.currentCard.cardn.equals("13")){
        bg("Images/game/backgrounds/richpersonhouse.png");
      }
      else if(super.currentCard.cardn.equals("9")){
        bg("Images/game/backgrounds/theatre.png");
      }
    }
    else if(super.currentCard.scene.equals("6")){
      if(super.currentCard.cardn.equals("1")){
        bg("Images/game/backgrounds/garden.png");
      }
      else if(super.currentCard.cardn.equals("3")){
        bg("Images/game/backgrounds/richpersonhouse.png");
      }
    }
  }
    private void bg(String str){
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
        background.setImage(new Image(str));
         ta.play();
       }
     });
      fa.play();
  }
}