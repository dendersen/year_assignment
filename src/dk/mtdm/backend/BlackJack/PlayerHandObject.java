package dk.mtdm.backend.BlackJack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;

import java.io.FileNotFoundException;

public class PlayerHandObject {
  private ArrayList<CardObject> hand = new ArrayList<CardObject>();
  private ArrayList<CardObject> hand2 = new ArrayList<CardObject>();
  public boolean IS_AI = false;
  public boolean IS_ALIVE = true;
  private AI ai;

  public void addCard(CardObject card){
    hand.add(card);
  }
  public ArrayList<CardObject> getHand(){
    return(hand);
  }
  public void save(byte handId, String Game){
    try{
      File file = new File("Saves\\" + handId + "." + Game);
      if(file.createNewFile()){
        System.out.println("file created: " + file.getName());
      }else {
        System.out.println("file already exists.");
      }
    }catch (IOException e){
      System.out.println("an error occured while making file");
      e.printStackTrace();
    }

    try {
      FileWriter myWriter = new FileWriter("Saves\\" + handId + "." + Game);
      myWriter.write(encode());
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private String encode(){
    String data = "";
    for(byte i = 0; i < hand.size();i++){
      data += hand.get(i).getNumber();
      data += " ; ";
      data += hand.get(i).getSymbol();
      data += " ";
    }
    return(data);
  }

  public void load(byte handId, String Game){
    String data = "";
    try {
      File file = new File("Saves\\" + handId + "." + Game);
      Scanner myReader = new Scanner(file);
      while (myReader.hasNextLine()) {
        data += myReader.nextLine();
        data += "\n";
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    decode(data, handId);
  }
  
  private void decode(String data, byte handId){
    String[] dataSplit = data.split(",");//splits hand into cards
      for(byte j = 0; j < dataSplit.length; j++){
        try (Scanner splitPoint = new Scanner(dataSplit[j])) {
          byte var1 = -1;
          byte var2 = -1;
          if(splitPoint.hasNext()){ //collects number
            var1 = splitPoint.nextByte();
          }
          else System.out.println("missing number on card: " + j + ";  in hand: " + handId);
          if(splitPoint.hasNext()){ 
            splitPoint.skip(" ; ");
            var2 = splitPoint.nextByte();
          }
          else System.out.println("missing symbol on card: " + j + ";  in hand: " + handId);
          CardObject card = new CardObject(var1, var2);
          hand.add(card);
        }
      }
  }

  public void addToDiscard(CardObject card) {
    hand2.add(card);
  }

  public CardObject[] emptyDiscard(){
    int amount = hand2.size();
    
    CardObject[] cards = new CardObject [amount];
    for (byte i = 0; i < amount ; i++){
      cards[i] = hand2.remove(0);
    }
    return(cards);
  }

  public void implimentAI(AI ai){
    this.ai = ai;
  }

  public boolean aiAction(){
    return(ai.action());
  }
}