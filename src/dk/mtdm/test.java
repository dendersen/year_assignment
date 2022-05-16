package dk.mtdm;

import dk.mtdm.frontend.MyCanvas;

public class test {
  public static MyCanvas hello = new MyCanvas("test");
  
  public static void main(String[] args) {
    hello.start();
    hello.run("test");
    hello.run("test");
    hello.run("test");
    hello.run("test");
  }

}
