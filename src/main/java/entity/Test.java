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
        Player p1 = new Player(0,4);
        p1.setHedgeHog(1, 1, 1);
        playerMovement.push(p1.getHedgehogs(1));
        p1.setHedgeHog(2, 2, 2);
        playerMovement.push(p1.getHedgehogs(2));
        p1.setHedgeHog(3, 3, 3);
        playerMovement.push(p1.getHedgehogs(3));
        p1.setHedgeHog(4, 4, 4);
        playerMovement.push(p1.getHedgehogs(4));
        
        Player p2 = new Player(1,4);
        p2.setHedgeHog(1, 1, 1);
        playerMovement.push(p2.getHedgehogs(1));
        p2.setHedgeHog(2, 2, 2);
        playerMovement.push(p2.getHedgehogs(2));
        p2.setHedgeHog(3, 3, 3);
        playerMovement.push(p2.getHedgehogs(3));
        p2.setHedgeHog(4, 4, 4);
        playerMovement.push(p2.getHedgehogs(4));
        store = store.chg(playerMovement.peek());
        
        p2.setHedgeHog(4, 4, 4);
        playerMovement.push(p2.getHedgehogs(4));
        store.undo(p2, 4, store);
        
        //System.out.println(playerStack.peek());
        store.showPreviousMovement(playerMovement);
        store.viewAllMovement(playerMovement);
    }
}
