package dk.mtdm.backend.BlackJack;


public class CardObject {

  private final byte symbol;
  private final byte number;
  
  public CardObject( byte number, byte symbol){
    this.number = number;
    this.symbol = symbol;
  }

  public byte getNumber(){
    return number;
  }

  public byte getSymbol(){
    return symbol;
  }
}
