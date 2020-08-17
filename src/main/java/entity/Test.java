package entity;
import adt.*;
/**
 *
 * @author GV62 7RC
 */
public class Test {
    public static void main (String[] args){



        QueueInterface<Player> player = new ArrayQueue<>() {};       
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
        
        Player p1 = new Player(0,4);
        p1.setHedgeHog(1, 1, 1,playerMovement);
        p1.setHedgeHog(2, 2, 2,playerMovement);
        p1.setHedgeHog(3, 3, 3,playerMovement);
        p1.setHedgeHog(4, 4, 4,playerMovement);
        
        Player p2 = new Player(1,4);
        p2.setHedgeHog(1, 1, 1,playerMovement);
        p2.setHedgeHog(2, 2, 2,playerMovement);
        p2.setHedgeHog(3, 3, 3,playerMovement);
        p2.setHedgeHog(4, 4, 5,playerMovement);
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
        store = store.moveVertical(p2, 1, playerMovement, boardGrid,store); // 2 2
        store = store.moveVertical(p2, 1, playerMovement, boardGrid,store);
        System.out.println(p2.getHedgehogs(1).getRow() + " " + p2.getHedgehogs(1).getColumn());
        store.showPreviousMovement(playerMovement);
        store.viewAllMovement(playerMovement);

    }
}
