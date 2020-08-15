package entity;
import adt.*;
/**
 *
 * @author GV62 7RC
 */
public class Test {
    public static void main (String[] args){
        Player p1 = new Player("Loke","0001",4);
        Player p2 = new Player("Jeff","0002",4);
        
        p1.turn(p1,p2);
    }
}
