import java.util.*;
import java.io.*;
public class Act1 extends Game{
  public Act1(){
    super("Act1");

    
    Deck d = new Deck();
    long start = System.currentTimeMillis();
    Const.DECK_CACHE.put("Act1:2:11-Act1:2:11",super.deckGenerator.genDeck(2,11,2,11));
    Const.DECK_CACHE.put("Act1:2:13-Act1:2:13",super.deckGenerator.genDeck(2,13,2,13));
    Const.DECK_CACHE.put("Act1:2:15-Act1:2:21",super.deckGenerator.genDeck(2,15,2,21));
    Const.DECK_CACHE.put("Act1:2:23-Act1:2:49",super.deckGenerator.genDeck(2,23,2,49));
    System.out.println(((System.currentTimeMillis()-start)/1000.0) + " seconds");
    add(d,Const.DECK_CACHE.get("Act1:2:11-Act1:2:11"),Const.DECK_CACHE.get("Act1:2:13-Act1:2:13"),Const.DECK_CACHE.get("Act1:2:15-Act1:2:21"),Const.DECK_CACHE.get("Act1:2:23-Act1:2:49"));
    Const.DECK_CACHE.put("Act1: 2:11 2:13 2:15-2:21 2:23-2:49",d);
    

    start = System.currentTimeMillis();
    d = new Deck();
    Const.DECK_CACHE.put("Act1:2:12-Act1:2:14",super.deckGenerator.genDeck(2,12,2,14));
    Const.DECK_CACHE.put("Act1:2:22-Act1:2:22",super.deckGenerator.genDeck(2,22,2,22));
    add(d,Const.DECK_CACHE.get("Act1:2:12-Act1:2:14"),Const.DECK_CACHE.get("Act1:2:22-Act1:2:22"));
    Const.DECK_CACHE.put("Act1: 2:12-2:14 2:22",d);
    System.out.println(((System.currentTimeMillis()-start)/1000.0) + " seconds");

    start = System.currentTimeMillis();
    Const.DECK_CACHE.put("Act1:2:51-Act1:2:53",super.deckGenerator.genDeck(2,51,2,53));
    System.out.println(((System.currentTimeMillis()-start)/1000.0) + " seconds");
  }
  private void add(Deck d,Deck...l){
    for(Deck x:l)
      d.getDeck().addAll(x.getDeck());
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
          super.gameDeck.getDeck().addAll(Const.DECK_CACHE.get("Act1: 2:11 2:13 2:15-2:21 2:23-2:49").getDeck());
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
          super.gameDeck.getDeck().addAll(Const.DECK_CACHE.get("Act1:2:23-Act1:2:49").getDeck());
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