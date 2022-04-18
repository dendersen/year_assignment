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

  public String getSymbolString(){
    String string = "";
    switch (getNumber()){
      case 1:
      string = "hjerter";
      break;
      case 2:
      string = "romber";
      break;
      case 3:
      string = "klør";
      break;
      case 4:
      string = "spar";
      break;
    }
    return string;
  }
}
