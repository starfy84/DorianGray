import java.util.*;
import java.io.*;
public class Act0 extends Game{
  public Act0(){
    super("Act0","richpersonhouse");
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
      super.gameDeck = gen(1,1,1,2);
    }
    else if(super.state.equals("Main")){
      if(super.choices.length() == 2){
        if(super.choices.charAt(super.choices.length()-1) == 'L'){
          add(super.gameDeck,gen(1,3,1,3),gen(1,5,1,11));
        }
        else if(super.choices.charAt(super.choices.length()-1) == 'R'){
          add(super.gameDeck,gen(1,4,1,11));
        }
      }
    }
  }
  public void conditional(){
  }
  public void sceneChange(){}
}