import java.util.*;
import java.io.*;
public class Act1 extends Game{
  public Act1(){
    super("Act1","garden");
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
      super.gameDeck = Const.DECK_CACHE.get("Act1 Init");
    }
    else if(super.state.equals("Main")){
      if(super.choices.length() == 18){
        if(super.choices.charAt(17) == 'L'){
          if(Const.GAME_DEBUG)
            System.out.println("11, 13, 15-21, 23-50 added");
          super.gameDeck.getDeck().addAll(Const.DECK_CACHE.get("Act1: 2:11 2:13 2:15-2:21 2:23-2:50").getDeck());
        }
        else{
          if(Const.GAME_DEBUG)
            System.out.println("12-14, 22 added");
          super.gameDeck.getDeck().addAll(Const.DECK_CACHE.get("Act1: 2:12-2:14 2:22").getDeck());
        }
      }
      else if(super.choices.length() == 22 && super.choices.charAt(17) == 'R'){
        if(super.choices.charAt(21) == 'L'){
          if(Const.GAME_DEBUG)
            System.out.println("51-53 added");
          super.gameDeck.getDeck().addAll(Const.DECK_CACHE.get("Act1:2:51-Act1:2:53").getDeck());
        }
        else{
          if(Const.GAME_DEBUG)
            System.out.println("23-49 added");
          super.gameDeck.getDeck().addAll(Const.DECK_CACHE.get("Act1:2:23-Act1:2:50").getDeck());
        }
      }
    }
  }
  public void conditional(){
    try{
      BufferedReader in = new BufferedReader(new FileReader("saves/Act1"));
      String s = in.readLine();
      PrintWriter out = new PrintWriter(new FileWriter("saves/Act1",true));
      if(s.length() == 25){
        out.print("G");
      }
      else if(s.length() == 54 || s.length() == 49){
        out.print("B");
      }
      out.close();
    }catch(Exception e){}
  }
  public void sceneChange(){}
}