package dk.mtdm.backend.BlackJack;


public class CardObject {

  private final byte symbol;
  private final byte number;
  private final byte set;
  
  public CardObject( byte number, byte symbol, byte set){
    this.number = number;
    this.symbol = symbol;
    this.set = set;
  }

  public byte getNumber(){
    return number;
  }

  public byte getSymbol(){
    return symbol;
  }

  public byte getSet(){
    return set;
  }
  
}
