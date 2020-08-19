package entity;

import entity.*;
import adt.*;
import java.util.Scanner;
/**
 *
 * @author Arvin Ng
 */
public class Hedgehog {

    private int row;
    private int column;
    private boolean stuck;
    private boolean disabled;
    private int id;
    StackInterface<Hedgehog> tempTest = new ArrayStack<>();

    public Hedgehog(){}
    
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
        return "\nRow: " + row + "\nColumn: " + column + "\nDisabled: " + isDisabled() + "\nID" + id;
    }
    
    public void undo (Player player, int i, Hedgehog store, StackInterface<Hedgehog> playerMovement){
        playerMovement.pop();
        int x = store.getRow();
        int y = store.getColumn();
        player.setHedgeHog(player.getId(), i, x, y,playerMovement);
    }
    
    public Hedgehog chg(Hedgehog hedgehog, int hedgehogNo ){
        Hedgehog newHedgehog = new Hedgehog();
        newHedgehog.setRow(hedgehog.getRow());
        newHedgehog.setColumn(hedgehog.getColumn());       
       
        return newHedgehog;
    }
    
    public void viewAllMovement(StackInterface<Hedgehog> playerMovement){
        for(int i = playerMovement.getSize() ; i >= 0 ; i--){
            System.out.println("\n--------------\nMove " + (i+1) + " " + playerMovement.pop());
        }
    }
    
    public void showPreviousMovement(StackInterface<Hedgehog> playerMovement){
        System.out.print("Move " + (playerMovement.getSize()+1));
        System.out.println(playerMovement.peek());
    }
    
    public Hedgehog moveForward(Player player, int i ,StackInterface<Hedgehog> playerMovement, Cell[][] boardGrid, Hedgehog store){
        int x = player.getHedgehogs(i).getRow();
        int y = player.getHedgehogs(i).getColumn();
        Player temp = new Player(1,i);
        temp.setHedgeHog(1,i, x, (y+1),tempTest);
     
        boolean checkObstacle = boardGrid[x-1][y].isObstacleEnabled();
        boolean obstacle = boardGrid[x-1][y].pushHedgehog(temp.getHedgehogs(i));
        if(checkObstacle == true){
            if (obstacle == true){
                store = store.chg(player.getHedgehogs(i), i);
                player.setHedgeHog(player.getId(), i, x, (y+1),playerMovement);
            }
            else{}
        }
        else{
            store = store.chg(player.getHedgehogs(i), i);
            player.setHedgeHog(player.getId(), i, x, (y+1),playerMovement);
        }
       
        return store;
    }
    
    public Hedgehog moveVertical(Player player, int i ,StackInterface<Hedgehog> playerMovement, Cell[][] boardGrid, Hedgehog store){
        int x = player.getHedgehogs(i).getRow();
        int y = player.getHedgehogs(i).getColumn();
        Scanner sc = new Scanner(System.in);
        Player temp = new Player(0,i);
        
        if (x == 4){
            store = store.moveUp(i,x,y,temp,player,boardGrid,playerMovement,store);   
        }
        
        else if (x == 1){
            store = store.moveDown(i,x,y,temp,player,boardGrid,playerMovement,store);  
        }
        
        else if (x == 2 || x == 3){
            System.out.print("Move up or down (1.Up , 2.Down) : ");
            int movement = sc.nextInt();
            boolean check = false;
            while (check == false)
                if (movement == 1){
                        store = store.moveUp(i,x,y,temp,player,boardGrid,playerMovement,store);
                        check = true;
                }
                else if (movement == 2){
                        store = store.moveDown(i,x,y,temp,player,boardGrid,playerMovement,store);
                        check = true;
                }
                else{
                        System.out.println("Invalid Input!! Please Enter 1 or 2");
                        System.out.print("Move up or down (1.Up , 2.Down) : ");
                        movement = sc.nextInt();
                        break;
                }
            }
        return store;
    }
 
    public Hedgehog moveUp(int i, int x, int y, Player temp,Player player, Cell[][] boardGrid, StackInterface<Hedgehog> playerMovement, Hedgehog store){
        temp.setHedgeHog(0, i, (x-1), y, tempTest);
        boolean checkObstacle = boardGrid[x-2][y-1].isObstacleEnabled();
        boolean obstacle = boardGrid[x-2][y-1].pushHedgehog(temp.getHedgehogs(i));
        if(checkObstacle == true){
            if (obstacle == true){
                store = store.chg(player.getHedgehogs(i), i);
                player.setHedgeHog(player.getId(), i, (x-1), y, playerMovement);
            }
            else{}
        }
        else{
            store = store.chg(player.getHedgehogs(i), i);
            player.setHedgeHog(player.getId(), i, (x-1), y, playerMovement);
        }
        return store;
    }
    
    public Hedgehog moveDown(int i, int x, int y, Player temp,Player player, Cell[][] boardGrid, StackInterface<Hedgehog> playerMovement, Hedgehog store){
        temp.setHedgeHog(0, i, (x+1), y, tempTest); // 2+1
        boolean checkObstacle = boardGrid[x][y-1].isObstacleEnabled();
        boolean obstacle = boardGrid[x][y-1].pushHedgehog(temp.getHedgehogs(i));
        if(checkObstacle == true){
            if (obstacle == true){
                store = store.chg(player.getHedgehogs(i), i);
                player.setHedgeHog(player.getId(), i, (x+1), y, playerMovement);
            }
            else{}
        }
        else{
            store = store.chg(player.getHedgehogs(i), i);
            player.setHedgeHog(player.getId(), i, (x+1), y, playerMovement);
        }
        return store;
    }
}
