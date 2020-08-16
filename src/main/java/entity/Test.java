package entity;
import adt.*;
/**
 *
 * @author GV62 7RC
 */
public class Test {
    public static void main (String[] args){
        StackInterface<Hedgehog> playerMovement = new ArrayStack<>();
        Hedgehog store = new Hedgehog();
        
        Cell[][] boardGrid = new Cell[4][8];
        for(int i = 0 ; i < 4 ; i++){
            for (int y = 0 ; y <8 ; y++){
                boardGrid[i][y] = new Cell(false,1);
            }
        }
        boardGrid[1][1].setObstacleEnabled(true);
        boardGrid[1][5].setObstacleEnabled(true);
        boardGrid[2][1].setObstacleEnabled(true);
        boardGrid[3][6].setObstacleEnabled(true);
        
        Player p1 = new Player("Loke","0001",4);
        p1.setHedgeHog(1, 1, 1);
        playerMovement.push(p1.getHedgehogs(1));
        p1.setHedgeHog(2, 2, 2);
        playerMovement.push(p1.getHedgehogs(2));
        p1.setHedgeHog(3, 3, 3);
        playerMovement.push(p1.getHedgehogs(3));
        p1.setHedgeHog(4, 4, 4);
        playerMovement.push(p1.getHedgehogs(4));
        
        Player p2 = new Player("Jeff","0002",4);
        p2.setHedgeHog(1, 1, 1);
        playerMovement.push(p2.getHedgehogs(1));
        p2.setHedgeHog(2, 2, 2);
        playerMovement.push(p2.getHedgehogs(2));
        p2.setHedgeHog(3, 3, 3);
        playerMovement.push(p2.getHedgehogs(3));
        p2.setHedgeHog(4, 4, 5);
        playerMovement.push(p2.getHedgehogs(4));
        //store = store.chg(playerMovement.peek());
        
        //p2.setHedgeHog(4, 4, 4);
        //playerMovement.push(p2.getHedgehogs(4));
        //store.undo(p2, 4, store);
        
        //System.out.println(playerStack.peek());
        //store.showPreviousMovement(playerMovement);
        //store.viewAllMovement(playerMovement);
        //store.moveForward(p2, 4, playerMovement, boardGrid);
        //System.out.println(p2.getHedgehogs(4).getColumn());
        //System.out.println(p2.getHedgehogs(4).getRow());
        store.moveVertical(p2, 1, playerMovement, boardGrid); // 2 2
        System.out.println(p2.getHedgehogs(1).getRow() + " " + p2.getHedgehogs(1).getColumn());
    }
}
