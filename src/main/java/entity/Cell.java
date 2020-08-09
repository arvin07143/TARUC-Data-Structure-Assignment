package entity;

import java.util.Stack;

/**
 *
 * @author Arvin Ng
 */
public class Cell {

    Stack<Hedgehogs> cellStack = new Stack<>();
    private boolean obstacleEnabled; //true when current cell is obstacle
    private int obstacleMode;

    public Cell(boolean obstacleEnabled, int obstacleMode) {
        this.obstacleEnabled = obstacleEnabled;
        this.obstacleMode = obstacleMode;
    }

    public boolean isObstacleEnabled() {
        return obstacleEnabled;
    }

    public void setObstacleEnabled(boolean obstacleEnabled) {
        this.obstacleEnabled = obstacleEnabled;
    }

    public int getObstacleMode() {
        return obstacleMode;
    }

    public void setObstacleMode(int obstacleMode) {
        this.obstacleMode = obstacleMode;
    }

    public boolean pushHedgehog(Hedgehogs pushedHedgehog) {
        if (this.obstacleEnabled == true) {
            switch (obstacleMode) {
                default:
                    break;
            }
        }
        else cellStack.push(pushedHedgehog);
        
        return true;
    }
}
