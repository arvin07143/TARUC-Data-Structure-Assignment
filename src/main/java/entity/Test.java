package entity;
import adt.*;
/**
 *
 * @author GV62 7RC
 */
public class Test {
    public static void main (String[] args){


        QueueInterface<Player> player;
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
        

        

    }
}
