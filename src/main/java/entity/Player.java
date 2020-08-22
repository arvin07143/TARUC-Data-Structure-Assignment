package entity;

import ui.ImageLoader;
import javax.swing.*;
import java.awt.*;
import adt.*;

public class Player implements Comparable<Player> {

    private int id;
    private boolean winnable ;
    private static int hedgehogCount;
    private ListInterface<Hedgehog> hedgehogs;
    private int finishedHedgehogs = 0;

    public final Color[] colorOptions = { Color.red, Color.green, Color.blue, Color.orange};
    private final String[] colorNames = { "Red", "Green", "Blue", "Orange"};
    private Color playerColor;

    public final String[] playerImageName = {"pI1.png", "pI2.png", "pI3.png", "pI4.png"};
    private ImageIcon playerImage;

    //Constructors
    public Player(int id, int hedgehogCount) {
        this.id = id;
        this.hedgehogCount = hedgehogCount;
        this.winnable = true;

        playerColor = colorOptions[id]; //setting color based on id
        playerImage = ImageLoader.loadIcon(playerImageName[id]);

        hedgehogs = new ArrayList<>();
        for (int i = 0; i < hedgehogCount; i++) {
            hedgehogs.add(new Hedgehog(id));
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

    public ListInterface<Hedgehog> getHedgehogs() {
        return hedgehogs;
    }

    public Hedgehog getHedgehogs(int hedgehogNo){
        return hedgehogs.get(hedgehogNo-1);
    }

    public boolean isWinnable() {
        return winnable;
    }

    public ImageIcon getPlayerImage() {
        return playerImage;
    }

    public int getFinishedHedgehogs() {
        return finishedHedgehogs;
    }

    //Set methods

    public void setWinnable(boolean winnable) {
        this.winnable = winnable;
    }

    public void SetPlayerColor(Color color) {
        this.playerColor = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHedgehogCount(int hedgehogCount) {
        this.hedgehogCount = hedgehogCount;
    }

    public String getColorName(){
        return colorNames[id];
    }

    public void setHedgeHog(int id, int hedgehogNo, int row, int column, StackInterface<Hedgehog> playerMovement) {
        hedgehogs.get(hedgehogNo-1).setRow(row);
        hedgehogs.get(hedgehogNo-1).setColumn(column);
    }

    public void setFinishedHedgehogs(int finishedHedgehogs) {
        this.finishedHedgehogs = finishedHedgehogs;
    }

    
    //toString
    @Override
    public String toString(){
        StringBuilder outputStr = new StringBuilder();
        for (int i = 0; i < hedgehogCount; i++) {
            outputStr.append("Hedgehog ").append(i + 1).append(":").append(hedgehogs.get(i)).append("\n\n");
        }
        return outputStr.toString();
    }


    @Override
    public int compareTo(Player o) {
        if(o.getFinishedHedgehogs() == this.getFinishedHedgehogs()) return 0;
        else if(o.getFinishedHedgehogs() < this.getFinishedHedgehogs()) return -1;
        else return 1;
    }

}
