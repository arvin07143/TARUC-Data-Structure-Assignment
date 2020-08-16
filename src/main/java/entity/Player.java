package entity;

import adt.ArrayQueue;
import adt.QueueInterface;
import java.util.Scanner;
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
    private QueueInterface<Player> player;
    private static int MAX_PLAYER = 4;

    //Constructors
    public Player() {
        player = new ArrayQueue<Player>(MAX_PLAYER) {};
        reset();
    }


    public Player(Color color, String id, int hedgehogCount) {
        this.playerColor = color;
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
    
    public void pass(Player currentPlayer,QueueInterface<Player> player){
        currentPlayer = player.peek();
        boolean isPass = true;
        System.out.println("Pls choose ur movement");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch(choice){
            case 1 : 
                 isPass = true;
                 System.out.println("You have pass ur movement.");
                 break;
            case 2 : 
                isPass = false; 
                System.out.println("Pls choose ur next movement");
                break;
        }
    }
    
    public void afterLastMovement(Player currentPlayer,Player nextPlayer,QueueInterface<Player> player){
        player.enqueue(currentPlayer); 
        player.enqueue(nextPlayer); 
        Player p1 = player.peek();
        System.out.print(p1);
        boolean isWon = true;
        if (!isWon){
        player.dequeue();
        player.enqueue(player.dequeue());
        }
        else{
            player.dequeue();
        }
    }
    
    public void leftLastPlayer(QueueInterface<Player> player){
       if(player.getSize() == 1){
           reset();
        }
       
    }
    
    public void addPlayer(Player playerArr[]){
        for (int i = 0; i < player.getSize();i++){
            player.enqueue(playerArr[i]);
        }
    }
    
    public final void reset() {
    player.clear();
    }

}
