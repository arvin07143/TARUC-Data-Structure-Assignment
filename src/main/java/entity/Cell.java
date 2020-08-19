package entity;
import adt.*;
import java.util.Random;

/**
 *
 * @author GV62 7RC
 */
public class Cell{
    //Attributes
    StackInterface<Hedgehog> cellStack = new LinkedStack<>();
    private boolean obstacleEnabled; //true when current cell is obstacle
    private static int obstacleMode; //0.Normal 1.Wall 2.Pit 3.Blackhole

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
    
    public StackInterface<Hedgehog> getReversedCellStack(){
        StackInterface<Hedgehog> reversedCellStack = new LinkedStack<>();
        for (int i = getCellStackSize() - 1; i >= 0; i--){
            reversedCellStack.push(this.cellStack.find(i));        
        }
        return reversedCellStack;
    }
    
    public boolean pushHedgehog(Hedgehog pushingHedgehog) {
        if (this.obstacleEnabled == true) {
            System.out.println(obstacleMode);
            switch (obstacleMode) {
                case 0: // just push the hedgehog normally
                    break;
                    
                case 1: //wall (not supposed to be able to push into a wall)
                    return false;
                    
                case 2: //pit 
                    if (getCellStackSize() == 0){ 
                        pushingHedgehog.setStuck(true); //1st hedgehog that enters will get stuck
                    }
                    break;
                    
                case 3: //blackhole 
;                   pushingHedgehog.setStuck(true); //all hedgehogs that enter will get stuck
                    break;
                      
                default:
                    break;
            }
        }
        cellStack.push(pushingHedgehog);
        return true;
    }
    
    public Hedgehog popHedgehog(){
        Hedgehog poppedHedgehog = null;
        if (!cellStack.peek().isStuck()) {
            poppedHedgehog = cellStack.pop();
        }
        return poppedHedgehog;
    }
}
