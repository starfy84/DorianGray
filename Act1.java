import java.util.*;
import java.io.*;
public class Act1 extends Game{
  public Act1(){
    super("Act1");
    System.out.println("TEST");
  }
  private void add(LinkedList<Card>...l){
    for(LinkedList<Card> x:l)
      super.gameDeck.getDeck().addAll(x);
  }
  public void addToDeck(){
    if(super.state.equals("Init")){
      super.gameDeck = super.deckGenerator.genDeck(1,1,2,10);
    }
    else if(super.state.equals("Main")){
      if(super.choices.length() == 18){
        if(super.choices.charAt(17) == 'L'){
          if(Const.GAME_DEBUG)
            System.out.println("11, 13, 15-21, 23-49 added");
          add(super.deckGenerator.genDeck(2,11,2,11).getDeck(),super.deckGenerator.genDeck(2,13,2,13).getDeck(),super.deckGenerator.genDeck(2,15,2,21).getDeck(),super.deckGenerator.genDeck(2,23,2,49).getDeck());
        }
        else{
          if(Const.GAME_DEBUG)
            System.out.println("12-14, 22 added");
          add(super.deckGenerator.genDeck(2,12,2,14).getDeck(),super.deckGenerator.genDeck(2,22,2,22).getDeck());
        }
      }
      else if(super.choices.length() == 22 && super.choices.charAt(17) == 'R'){
        if(super.choices.charAt(21) == 'L'){
          if(Const.GAME_DEBUG)
            System.out.println("51-53 added");
          add(super.deckGenerator.genDeck(2,51,2,53).getDeck());
        }
        else{
          if(Const.GAME_DEBUG)
            System.out.println("23-49 added");
          add(super.deckGenerator.genDeck(2,23,2,49).getDeck());
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
    }catch(Exception e){}
  }
}