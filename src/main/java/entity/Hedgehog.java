package entity;

import entity.*;
import adt.StackInterface;
import java.util.Scanner;
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
    
    public boolean moveForward(Player player, int i ,StackInterface<Hedgehog> playerMovement, Cell[][] boardGrid){
        int x = player.getHedgehogs(i).getRow();
        int y = player.getHedgehogs(i).getColumn();
        Player temp = new Player(1,i);
        temp.setHedgeHog(i, x, (y+1));
     
        boolean checkObstacle = boardGrid[x-1][y].isObstacleEnabled();
        boolean obstacle = boardGrid[x-1][y].pushHedgehog(temp.getHedgehogs(i));
        if(checkObstacle == true){
            if (obstacle == true){
                player.setHedgeHog(i, x, (y+1));
                return true;
            }
            else{}
        }
        else{
            player.setHedgeHog(i, x, (y+1));
            return true;
        }
        
        return false;
    }
    
    public boolean moveVertical(Player player, int i ,StackInterface<Hedgehog> playerMovement, Cell[][] boardGrid){
        boolean checkObstacle;
        boolean obstacle;
        Scanner sc = new Scanner(System.in);
        Hedgehog move = new Hedgehog();
        int x = player.getHedgehogs(i).getRow(); // 
        int y = player.getHedgehogs(i).getColumn(); 
        Player temp = new Player(1,i);
        
        if (x == 4){
            return move.moveUp(i,x,y,temp,player,boardGrid);   
        }
        
        else if (x == 1){
            return move.moveDown(i,x,y,temp,player,boardGrid);  
        }
        
        else if (x == 2 || x == 3){
            System.out.print("Move up or down (1.Up , 2.Down) : ");
            int movement = sc.nextInt();
            while (movement != 1 || movement != 2)
                switch (movement) {
                    case 1:{
                        return move.moveUp(i,x,y,temp,player,boardGrid);
                    }
                    case 2:{
                        return move.moveDown(i,x,y,temp,player,boardGrid);    
                    }
                    default:{
                        System.out.println("Invalid Input!! Please Enter 1 or 2");
                        System.out.print("Move up or down (1.Up , 2.Down) : ");
                        movement = sc.nextInt();
                        break;
                    }
                }
        }
        return false;
    }
    
    public boolean moveUp(int i, int x, int y, Player temp,Player player, Cell[][] boardGrid ){
        temp.setHedgeHog(i, (x-1), y);
        boolean checkObstacle = boardGrid[x-2][y-1].isObstacleEnabled();
        boolean obstacle = boardGrid[x-2][y-1].pushHedgehog(temp.getHedgehogs(i));
        if(checkObstacle == true){
            if (obstacle == true){
                player.setHedgeHog(i, (x-1), y);
                return true;
            }
            else{}
        }
        else{
            player.setHedgeHog(i, (x-1), y);
            return true;
        }
        return false;
    }
    
    public boolean moveDown(int i, int x, int y, Player temp,Player player, Cell[][] boardGrid){
        temp.setHedgeHog(i, (x+1), y); // 2+1
        boolean checkObstacle = boardGrid[x][y-1].isObstacleEnabled();
        boolean obstacle = boardGrid[x][y-1].pushHedgehog(temp.getHedgehogs(i));
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
        }
        return false;
    }
}
