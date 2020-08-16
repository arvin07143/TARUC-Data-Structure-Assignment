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
    
    public boolean moveX(Player player, int i ,StackInterface<Hedgehog> playerMovement, Cell[][] boardGrid){
        int x = player.getHedgehogs(i).getRow();
        int y = player.getHedgehogs(i).getColumn();
        Player temp = new Player("temp","0000",i);
        temp.setHedgeHog(i, (x+1), y);
     
        System.out.println(boardGrid[x+1][y].isObstacleEnabled());
        /*boolean obstacle = boardGrid[x+1][y].pushHedgehog(temp.getHedgehogs(i));
        if(checkObstacle == true){
            if (obstacle == true){
            player.setHedgeHog(i, (x+1), y);
            return true;
            }
            else{}
           
        }
        else{
            player.setHedgeHog(i, (x+1), y);
            return true;
        }*/
        return false;
    }
}
