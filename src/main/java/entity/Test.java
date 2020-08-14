package entity;
import adt.*;
/**
 *
 * @author GV62 7RC
 */
public class Test {
    public static void main (String[] args){
        StackInterface<Hedgehog> p = new LinkedStack<>();
        Player p1 = new Player("Loke","0001",1);
        p1.setHedgeHog(1, 1, 1);
        p.push(p1.getHedgehogs(1));
        System.out.println(p.pop());
    }
}
