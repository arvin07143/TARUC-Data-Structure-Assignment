package entity;
import adt.*;
/**
 *
 * @author GV62 7RC
 */
public class Test {
    public static void main (String[] args){
        StackInterface<Player> playerStack = new LinkedStack<>();
        Player p1 = new Player("Loke","0001",4);
        p1.setHedgeHog(1, 1, 1);
        p1.setHedgeHog(2, 2, 2);
        p1.setHedgeHog(3, 3, 3);
        p1.setHedgeHog(4, 4, 4);
        playerStack.push(p1);
        Player p2 = new Player("Jeff","0002",4);
        p2.setHedgeHog(1, 1, 1);
        p2.setHedgeHog(2, 2, 2);
        p2.setHedgeHog(3, 3, 3);
        p2.setHedgeHog(4, 4, 4);
        playerStack.push(p2);
        System.out.println(playerStack);
    }
}
