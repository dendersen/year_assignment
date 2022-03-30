package dk.mtdm.backend.BlackJack;

import java.util.ArrayList;

public class PlayerHandObject {
  private ArrayList<CardObject> hand = new ArrayList<>();
  
  public PlayerHandObject(CardObject hand){
    this.hand.add(hand);
  }
}