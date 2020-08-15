package entity;
import entity.*;

import adt.StackInterface;

/**
 *
 * @author Arvin Ng
 */
public class Hedgehog {

    private int row;
    private int column;
    private boolean stuck;

    public Hedgehog() {
        this.row = -1;
        this.column = -1;
        this.stuck = false;
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
    
    //toString
    public String toString(){
        return "\nRow: " + row + "\nColumn: " + column;
    }
    
    public void undo (Player player, int i, Hedgehog store){
        int x = store.getRow();
        int y = store.getColumn();
        player.setHedgeHog(i, x, y);
    }
    
    public Hedgehog chg(Hedgehog x){
        Hedgehog y = new Hedgehog();
        y.setColumn(x.getColumn());
        y.setRow(x.getRow());
        
        return y;
    }
    
    public void viewAllMovement(StackInterface<Hedgehog> playerMovement){
        for(int i = playerMovement.getSize()-1 ; i >= 0 ; i--){
            System.out.println("\n--------------\nMove " + (i+1) + " " + playerMovement.pop());
        }
    }
    
    public void showPreviousMovement(StackInterface<Hedgehog> playerMovement){
        System.out.print("Move " + playerMovement.getSize());
        System.out.println(playerMovement.peek());
    }
    
}
