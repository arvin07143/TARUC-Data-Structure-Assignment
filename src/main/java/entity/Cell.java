package entity;
import adt.*;
import java.util.Random;

/**
 *
 * @author Arvin Ng
 */
public class Cell {
    //Attributes
    StackInterface<Hedgehog> cellStack = new LinkedStack<>();
    private boolean obstacleEnabled; //true when current cell is obstacle
    private static int obstacleMode; //1.Wall 2.Pit 3.Wormhole

    //Constructor
    public Cell(boolean obstacleEnabled, int obstacleMode) {
        this.obstacleEnabled = obstacleEnabled;
        this.obstacleMode = obstacleMode;
    }
   
    //Setter
    public void setObstacleEnabled(boolean obstacleEnabled) {
        this.obstacleEnabled = obstacleEnabled;
    }
    public void setObstacleMode(int obstacleMode) {
        this.obstacleMode = obstacleMode;
    }
    
    //Getter
    public int getObstacleMode() {
        return obstacleMode;
    }
    public boolean isObstacleEnabled() {
        return obstacleEnabled;
    }
       
    //methods
    public boolean pushHedgehog(Hedgehog pushingHedgehog) {
        if (this.obstacleEnabled == true) {
            switch (obstacleMode) {
                case 1: //wall (done)
                    return false;
                    
                case 2: //pit (done)
                    if (cellStack.getSize() == 0){
                        pushingHedgehog.setStuck(true); 
                    }
                    break;
                    
                case 3: //wormhole (not finished)
                    obstacleEnabled = false;
                    Random random = new Random();
                    pushingHedgehog.setRow(1); // <<<< still figuring out
                    break;
                                  
                default:
                    break;
            }
        }
        cellStack.push(pushingHedgehog);
        return true;
    }
    
    public Hedgehog popHedgehog(Hedgehog poppingHedgehog){
        Hedgehog poppedHedgehog = null;
        if (this.obstacleEnabled == true) {
            switch (obstacleMode) {
                case 1: //wall (done)
                    break;
                    
                case 2: //pit (done)
                    if (cellStack.getSize() > 1){
                        poppedHedgehog = cellStack.pop();
                        break;
                    }     
                    break;
                            
                case 3: //wormhole (not finished)
                    break;
                                  
                default:
                    break;
            }
        } else {
            poppedHedgehog = cellStack.pop();
        }

        return poppedHedgehog;
    }
}
