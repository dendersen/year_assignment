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
    switch (getSymbol()){
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

  public String getNumberString(){
    String string = "";
    switch(getNumber()){
      case 1:
      string = "A";
      break;
      case 2:
      string = "2";
      break;
      case 3:
      string = "3";
      break;
      case 4:
      string = "4";
      break;
      case 5:
      string = "5";
      break;
      case 6:
      string = "6";
      break;
      case 7:
      string = "7";
      break;
      case 8:
      string = "8";
      break;
      case 9:
      string = "9";
      break;
      case 10:
      string = "X";
      break;
      case 11:
      string = "B";
      break;
      case 12:
      string = "D";
      break;
      case 13:
      string = "K";
      break;

    }
    return string;
  }
}
