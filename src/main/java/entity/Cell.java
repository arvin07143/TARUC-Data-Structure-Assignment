package entity;
import adt.*;

/**
 *
 * @author GV62 7RC
 */
public class Cell{
    //Attributes
    StackInterface<Hedgehog> cellStack = new LinkedStack<>();
    private boolean obstacleEnabled; //true when current cell is obstacle
    private int obstacleMode; //0.Normal 1.Wall 2.Pit 3.Blackhole

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
        return this.cellStack.getSize();
    }
    
    public StackInterface<Hedgehog> getReversedCellStack(){
        return this.cellStack.reverse();
    }
    
    public boolean pushHedgehog(Hedgehog pushingHedgehog) {
        if (this.obstacleEnabled) {
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
                    System.out.println("STUCK YO MAMA");
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
