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
      File file = new File("BlackJack save game.txt" + handId);
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
      File file = new File("BlackJack save game.txt");
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
    decode(data);
  }
  
  private static void decode(String data){
    String[] dataSplit = data.split("{");
    ArrayList <String[]> splitsplit= new ArrayList<String[]>();
    for(byte i = 0; i < dataSplit.length; i++){
      splitsplit.add(dataSplit[i].split(","));
    }
    for(byte i = 0; i < splitsplit.size(); i++){
      String[] split = splitsplit.remove(i);
      for(byte j = 0; j < split.length; j++){
        Scanner splitPoint = new Scanner(split[j]);
        if(splitPoint.hasNext()){
          splitPoint.nextByte();
        }
      }
    }
  }
}