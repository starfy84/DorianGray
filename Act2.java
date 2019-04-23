import java.util.*;
import java.io.*;
public class Act2 extends Game{
  private char lastActResult;
  public Act2(){
    super("Act2","richpersonhouse");
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
        super.gameDeck = gen(2,1,2,3);
        add(super.gameDeck,gen(3,1,3,16),gen(3,18,3,28),gen(3,30,3,51),gen(3,54,3,56),gen(4,1,4,2),gen(4,4,4,9),gen(4,11,4,11),gen(4,13,4,27),gen(5,1,5,2),gen(5,5,5,6),gen(5,10,5,11),gen(5,14,5,16),gen(5,18,5,20),gen(5,22,5,25),gen(5,27,5,34),gen(5,37,5,63),gen(5,65,5,65));
      }
      else if(lastActResult == 'B'){
        super.gameDeck = gen(1,1,1,8);
        add(super.gameDeck,gen(3,1,3,16),gen(3,1,3,1),gen(3,4,3,54),gen(3,57,3,57),gen(4,1,4,10),gen(4,12,4,27),gen(5,1,5,15),gen(5,17,5,21),gen(5,23,5,64));
      }
    }
  }
  public void conditional(){

  }
}