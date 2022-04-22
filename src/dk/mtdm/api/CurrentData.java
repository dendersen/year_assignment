package dk.mtdm.api;

import dk.mtdm.backend.BlackJack.Table;

public class CurrentData {
  public byte playerID;
  public boolean[] AVAILABLE_ACTIONS;

  public CurrentData(byte PlayerID) {
    playerID = PlayerID;
    AVAILABLE_ACTIONS = Table.availableActions(playerID);
  }
  public void setAction(){
    
  }
}