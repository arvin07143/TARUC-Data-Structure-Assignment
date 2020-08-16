package entity;

import ui.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class Player {

    private int id;
    private Color[] colorOptions = { Color.red, Color.green, Color.blue, Color.orange};
    private String[] colorNames = { "Red", "Green", "Blue", "Orange"};
    private Color playerColor;
    private String[] playerImageName = {"pI1.png", "pI2.png", "pI3.png", "pI4.png"};
    private ImageIcon playerImage;

    private static int hedgehogCount;
    private Hedgehog[] hedgehogs;

    //Constructors
    public Player() {
    }

    public Player(int id, int hedgehogCount) {
        this.id = id;
        this.hedgehogCount = hedgehogCount;

        playerColor = colorOptions[id]; //setting color based on id
        playerImage = ImageLoader.loadIcon(playerImageName[id]);

        hedgehogs = new Hedgehog[hedgehogCount];
        for (int i = 0; i < hedgehogCount; i++) {
            hedgehogs[i] = new Hedgehog();
        }
    }

    //Get methods

    public int getId() {
        return id;
    }

    public int getHedgehogCount() {
        return hedgehogCount;
    }

    public Hedgehog[] getHedgehogs() {
        return hedgehogs;
    }

    public Hedgehog getHedgehogs(int hedgehogNo){
        return hedgehogs[hedgehogNo-1];
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public ImageIcon getPlayerImage() {
        return playerImage;
    }

    //Set methods
    public void setId(int id) {
        this.id = id;
    }

    public void setHedgehogCount(int hedgehogCount) {
        this.hedgehogCount = hedgehogCount;
    }

    public void setHedgeHog(int hedgehogNo, int row, int column) {
        hedgehogs[hedgehogNo-1].setRow(row);
        hedgehogs[hedgehogNo-1].setColumn(column);
    }
    
    //toString
    public String toString(){
        String outputStr = "";
        for (int i = 0; i < hedgehogCount; i++) {
            
            outputStr += "Hedgehog " + (i + 1) + ":" + hedgehogs[i] + "\n\n";
        }
        return outputStr;
    }
}
