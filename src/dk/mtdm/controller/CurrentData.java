package dk.mtdm.controller;

import dk.mtdm.backend.BlackJack.Table;

public class CurrentData {
  public final byte playerID;
  public boolean[] AVAILABLE_ACTIONS;
  public boolean action;
  public boolean dealer;
  public boolean aae;

  
  public CurrentData(byte PlayerID) {
    playerID = PlayerID;
    AVAILABLE_ACTIONS = Table.availableActions(playerID);
    aae = false;
    System.out.println(playerID);
  }
  
  public CurrentData(byte PlayerID, boolean ai){
    playerID = PlayerID;
    AVAILABLE_ACTIONS = Table.availableActions(playerID);
    aae = ai;
  }

  public void setDealer(boolean dealer){
    this.dealer = dealer;
  }
  
  /**
   * 
   * @param Action true = hit, false = stand
   */
  public void setAction(boolean Action){
    action = Action;
  }
}