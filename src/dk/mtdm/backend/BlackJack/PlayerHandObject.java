package dk.mtdm.backend.BlackJack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class PlayerHandObject {
  private static ArrayList<CardObject> hand = new ArrayList<CardObject>();
  
  public static void addCard(CardObject card){
    hand.add(card);
  }
  public static ArrayList<CardObject> getHand(){
    return(hand);
  }
  public static void save(byte handId){
    try{
      File myObj = new File("BlackJack save game.txt" + handId);
      if(myObj.createNewFile()){
        System.out.println("file created: " + myObj.getName());
      }else {
        System.out.println("file already exists.");
      }
    }catch (IOException e){
      System.out.println("an error occured while making file");
      e.printStackTrace();
    }

    try {
      FileWriter myWriter = new FileWriter("BlackJack save game.txt");
      myWriter.write("Files in Java might be tricky, but it is fun enough!");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  public static void load(){
    String data = "";
    try {
      File myObj = new File("BlackJack save game.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        data += myReader.nextLine();
        data += "\n";
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    hand = decode(data);
  }
  
  private static ArrayList<CardObject> decode(String data){
    ArrayList<CardObject> hand = new ArrayList<CardObject>();
    String[] dataSplit = data.split("{");
    for(byte i = 0; i < dataSplit.length; i++){
      dataSplit[1].replace("}", "");
    }
    return(hand);
  }
}