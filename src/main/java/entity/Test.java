package entity;
import adt.*;
/**
 *
 * @author GV62 7RC
 */
public class Test {
    public static void main (String[] args){
        Player p1 = new Player("Yellow","0001",4);
        Player p2 = new Player("Black","0002",4);
        p1.setHedgeHog(1, 1, 1);
        p1.setHedgeHog(2, 2, 2);
        p1.setHedgeHog(3, 3, 3);
        p1.setHedgeHog(4, 4, 4);
        p2.setHedgeHog(1, 1, 1);
        p2.setHedgeHog(2, 2, 2);
        p2.setHedgeHog(3, 3, 3);
        p2.setHedgeHog(4, 4, 4);
        
        p1.addPlayer(p2);
    }
}
