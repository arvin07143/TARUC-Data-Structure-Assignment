package entity;
import adt.*;
/**
 *
 * @author GV62 7RC
 */
public class Test {
    public static void main (String[] args){



<<<<<<< Updated upstream
        QueueInterface<Player> player = new ArrayQueue<>() {};       
=======
       /* QueueInterface<Player> player;
        player = new ArrayQueue<>() {};
        Player p1 = new Player("Loke","0001",4);
          p1.setHedgeHog(1, 1, 1);
          p1.setHedgeHog(2, 2, 2);
          p1.setHedgeHog(3, 3, 3);
          p1.setHedgeHog(4, 4, 4);
              
        Player p2 = new Player("Jeff","0002",4);
         p2.setHedgeHog(4, 4, 4);
         p2.setHedgeHog(3, 3, 3);
         p2.setHedgeHog(2, 2, 2);
         p2.setHedgeHog(1, 1, 1);
          
                 
        Player p3 = new Player("Cch","0002",4);
         p3.setHedgeHog(3, 4, 4);
         p3.setHedgeHog(4, 3, 3);
         p3.setHedgeHog(1, 2, 2);
         p3.setHedgeHog(2, 1, 1);

       p3.afterLastMovement(p1,p3,player);
        */

        


>>>>>>> Stashed changes
        StackInterface<Hedgehog> playerMovement = new ArrayStack<>();
        Hedgehog store = new Hedgehog(4);
        
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
