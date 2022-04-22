package dk.mtdm.api;

public class DataTransfer {
  public byte playerID;
  public boolean chosenaction;
  public boolean[] availableActions;

  public DataTransfer(byte playerID, boolean[] availableActions) {
    this.playerID = playerID;
    this.availableActions = availableActions;
  }
}