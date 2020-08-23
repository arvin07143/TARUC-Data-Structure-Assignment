package entity;
import adt.*;

public class Cell{
    //Attributes
    private StackInterface<Hedgehog> cellStack = new LinkedStack<>();
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
   
    //Setters
    public void setCellStack(StackInterface<Hedgehog> cellStack) {
        this.cellStack = cellStack;
    }
    public void setObstacleEnabled(boolean obstacleEnabled) {
        this.obstacleEnabled = obstacleEnabled;
    }
    public void setObstacleMode(int obstacleMode) {
        this.obstacleMode = obstacleMode;
    }
    
    //Getters
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
                    
                case 2: //pit 1st hedgehog that enters will get stuck
                    if (getCellStackSize() == 0){ 
                        pushingHedgehog.setStuck(true);
                    }
                    break;
                    
                case 3: //blackhole all hedgehogs that enter will get stuck
;                   pushingHedgehog.setStuck(true);
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
    
    public boolean equals(Cell cellEntry){
        return cellEntry.getCellStack() == this.getCellStack();
    }
    
    //toString
    public String toString(){
        String str = "\nCell Stack: " + cellStack.toString() + "\nObstacle Enabled: " + (obstacleEnabled ? "Yes" : "No") + "\nObstacle Mode: ";
        switch(obstacleMode){
            case 0:
                str += "None";
                break;
            case 1:
                str += "Walls";
                break;
            case 2:
                str += "Pits";
                break;
            case 3:
                str += "Black Holes";
                break;            
        }
        return str;
    }
}
