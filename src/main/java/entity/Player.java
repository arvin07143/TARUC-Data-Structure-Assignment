package entity;

import adt.ArrayQueue;
import adt.QueueInterface;
import java.util.Scanner;
import ui.ImageLoader;
import javax.swing.*;
import java.awt.*;

public class Player {

    private int id;
    private boolean winnable ;
    public Color[] colorOptions = { Color.red, Color.green, Color.blue, Color.orange};
    private String[] colorNames = { "Red", "Green", "Blue", "Orange"};
    private Color playerColor;
    public String[] playerImageName = {"pI1.png", "pI2.png", "pI3.png", "pI4.png"};
    private ImageIcon playerImage;
    private static int hedgehogCount;
    private Hedgehog[] hedgehogs;
    private QueueInterface<Player> player;
    private static int MAX_PLAYER = 4;
    private int finishedHedgehogs = 0;

    //Constructors
    public Player() {
        player = new ArrayQueue<Player>(MAX_PLAYER) {};
        reset();
    }

    public Player(int id, int hedgehogCount) {

        this.id = id;
        this.hedgehogCount = hedgehogCount;
        this.winnable = true;

        playerColor = colorOptions[id]; //setting color based on id
        playerImage = ImageLoader.loadIcon(playerImageName[id]);

        hedgehogs = new Hedgehog[hedgehogCount];
        for (int i = 0; i < hedgehogCount; i++) {
            hedgehogs[i] = new Hedgehog(id);
        }
    }

    //Get methods

    public Color getPlayerColor() {
        return playerColor;
    }


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

    public boolean isWinnable() {
        return winnable;
    }

    public void setWinnable(boolean winnable) {
        this.winnable = winnable;
    }

    //Set methods
    public void SetPlayerColor(Color color) {
        this.playerColor = color;
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

    public int getFinishedHedgehogs() {
        return finishedHedgehogs;
    }

    public void setFinishedHedgehogs(int finishedHedgehogs) {
        this.finishedHedgehogs = finishedHedgehogs;
    }

    public String getColorName(){
        return colorNames[id];
    }
    
    //toString
    @Override
    public String toString(){
        String outputStr = "";
        for (int i = 0; i < hedgehogCount; i++) {
            outputStr += "Hedgehog " + (i + 1) + ":" + hedgehogs[i] + "\n\n";
        }
        return outputStr;
    }
    
    public final void reset() {
    player.clear();
    }

}
