package entity;
import adt.*;
/**
 *
 * @author GV62 7RC
 */
public class Test {
    public static void main (String[] args){
        StackInterface<Hedgehog> playerStack = new ArrayStack<>();
        Hedgehog store = new Hedgehog();
        Player p1 = new Player("Loke","0001",4);
        p1.setHedgeHog(1, 1, 1);
        playerStack.push(p1.getHedgehogs(1));
        p1.setHedgeHog(2, 2, 2);
        playerStack.push(p1.getHedgehogs(2));
        p1.setHedgeHog(3, 3, 3);
        playerStack.push(p1.getHedgehogs(3));
        p1.setHedgeHog(4, 4, 4);
        playerStack.push(p1.getHedgehogs(4));
        
        Player p2 = new Player("Jeff","0002",4);
        p2.setHedgeHog(1, 1, 1);
        playerStack.push(p2.getHedgehogs(1));
        p2.setHedgeHog(2, 2, 2);
        playerStack.push(p2.getHedgehogs(2));
        p2.setHedgeHog(3, 3, 3);
        playerStack.push(p2.getHedgehogs(3));
        p2.setHedgeHog(4, 4, 4);
        playerStack.push(p2.getHedgehogs(4));
        store = store.chg(playerStack.peek());
        
        p2.setHedgeHog(4, 4, 4);
        playerStack.push(p2.getHedgehogs(4));
        store.undo(p2, 4, store);
        
        System.out.println(playerStack.peek());
    }
}
