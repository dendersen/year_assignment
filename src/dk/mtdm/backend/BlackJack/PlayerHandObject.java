package dk.mtdm.backend.BlackJack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerHandObject {
  private static ArrayList<CardObject> hand = new ArrayList<CardObject>();
  
  public static void addCard(CardObject card){
    hand.add(card);
  }
  public static ArrayList<CardObject> getHand(){
    return(hand);
  }
  public static void main(){
    try{
      File myObj = new File("filename.txt");
      if(myObj.createNewFile()){
        System.out.println("file created: " + myObj.getName());
      }else {
        System.out.println("file already exists.");
      }
    }catch (IOException e){
      System.out.println("an error occured while making file");
      e.printStackTrace();
    }
  }
}