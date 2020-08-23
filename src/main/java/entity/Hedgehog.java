package entity;

import javax.swing.*;
import entity.*;
import adt.*;
import java.util.Scanner;

public class Hedgehog {

    private int id;
    private int row;
    private int column;

    private boolean stuck;
    private boolean disabled;
    private StackInterface<Hedgehog> playerMovement = new ArrayStack<>();

    public Hedgehog() {
    }

    public Hedgehog(int id) {
        this.row = -1;
        this.column = -1;
        this.stuck = false;
        this.disabled = false;
        this.id = id;
    }
    
    //Setters
    public void setRow(int row) {
        this.row = row;
    }
    
    public void setColumn(int column) {
        this.column = column;
    }
    
    public void setStuck(boolean stuck){
        this.stuck = stuck;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    //Getters
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public boolean isStuck(){
        return stuck;
    }

    public int getId() {
        return id;
    }
    
    
    //toString
    public String toString(){
        return "\nHedgehog Owner: Player " + (id+1) + "\nRow: " + row + "\nColumn: " + column + "\nDisabled: " + isDisabled();
    }
    
    public Hedgehog undo (Board gameBoard){
        Hedgehog newCoor = new Hedgehog();
        int initRow = playerMovement.peek().getRow();
        int initColumn = playerMovement.peek().getColumn();
        playerMovement.pop();
        playerMovement.pop();
        
        int finalRow = gameBoard.getPreviousMovement().getRow();
        int finalColumn = gameBoard.getPreviousMovement().getColumn();
        newCoor.setColumn(finalColumn);
        newCoor.setRow(finalRow);
        newCoor.setStuck(false);
        gameBoard.moveToken(initRow, initColumn, finalRow, finalColumn);

        return newCoor;
    }
    
    public void viewAllMovement(){
        for(int i = playerMovement.getSize()-1 ; i >= 0 ; i--){
            System.out.println("\n--------------\nMove " + (i+1) + " " + playerMovement.pop());
        }
    }
    
    public void showPreviousMovement(JTextArea hedgehogMovement){
        System.out.println(playerMovement.peek());
        hedgehogMovement.append("Move " + (playerMovement.getSize()) + "\n" + "Hedgehog Owner: Player " + (playerMovement.peek().getId()+1) + 
                "\nRow\t   : " + (playerMovement.peek().getRow()+1) + "\nColumn\t   : " + (playerMovement.peek().getColumn()+1) + 
                "\n----------------------------------------------------------\n");
    }
    
    public void pushHedgehog(Hedgehog tempHedge){
        playerMovement.push(tempHedge);
        System.out.println(playerMovement.getSize());
        System.out.println(playerMovement.peek());
    }
    
    public Hedgehog popHedgehog(){
        if(!playerMovement.isEmpty())
            return playerMovement.pop();
        else return null;
    }
}
