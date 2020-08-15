package entity;

/**
 *
 * @author Arvin Ng
 */
public class PitTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ObstacleGridGenerator grid = new ObstacleGridGenerator();
        
        grid.obstacleGridGeneration(5, 7);
    }
    
}
