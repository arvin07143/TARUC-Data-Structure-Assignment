package entity;
import adt.*;
import java.util.Random;

/**
 *
 * @author GV62 7RC
 */
public class Cell {
    //Attributes
    StackInterface<Hedgehog> cellStack = new LinkedStack<>();
    private boolean obstacleEnabled; //true when current cell is obstacle
    private static int obstacleMode; //-1.Normal 1.Wall 2.Pit 3.Blackhole

    //Constructors
    public Cell(){
        this(false, 0);
    }
    public Cell(boolean obstacleEnabled, int obstacleMode) {
        this.obstacleEnabled = obstacleEnabled;
        this.obstacleMode = obstacleMode;
    }
   
    //Setter
    public void setCellStack(StackInterface<Hedgehog> cellStack) {
        this.cellStack = cellStack;
    }
    public void setObstacleEnabled(boolean obstacleEnabled) {
        this.obstacleEnabled = obstacleEnabled;
    }
    public void setObstacleMode(int obstacleMode) {
        this.obstacleMode = obstacleMode;
    }
    
    //Getter
    public StackInterface<Hedgehog> getCellStack() {
        return cellStack;
    }
    public int getObstacleMode() {
        return obstacleMode;
    }
    public boolean isObstacleEnabled() {
        return obstacleEnabled;
    }
       
    //methods
    public int getCellStackSize(){
        return cellStack.getSize();
    }
    
    public boolean pushHedgehog(Hedgehog pushingHedgehog) {
        if (this.obstacleEnabled == true) {
            switch (obstacleMode) {
                case 1: //wall 
                    return false;
                    
                case 2: //pit 
                    if (cellStack.getSize() == 0){
                        pushingHedgehog.setStuck(true); 
                    }
                    break;
                    
                case 3: //blackhole 
;                   pushingHedgehog.setStuck(true); 
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
                case 2: //pit 
                    if (!poppingHedgehog.isStuck()){
                        poppedHedgehog = cellStack.pop();
                    }     
                    break;
        
                default: //case 1 just returns null; case 3 will not allow any hedgehog to leave, thus returning null also
                    break;
            }
        } else {
            poppedHedgehog = cellStack.pop();
        }
        return poppedHedgehog;
    }
}
