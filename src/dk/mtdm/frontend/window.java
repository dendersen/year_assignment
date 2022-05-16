package dk.mtdm.frontend;

import processing.core.PApplet;

public class window extends PApplet {
    public static void main(String[] args) {
        PApplet.main("window");
    }

    public void settings() {
        size(1000,1000);
    }

    public void draw() {
        background(220);
        rect(mouseX,mouseY,30,30);
    }
}
