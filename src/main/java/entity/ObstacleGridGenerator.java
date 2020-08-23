package entity;
import java.util.Random;

public class ObstacleGridGenerator {
    //Attributes
    private static Random random = new Random();
    private int obstacleCount;
    private int maxInvalidCounter;

    //Constructors
    public ObstacleGridGenerator(){
        this(0,0);
    }
    public ObstacleGridGenerator(int obstacleCount, int maxInvalidCounter){
        this.obstacleCount = obstacleCount;
        this.maxInvalidCounter = maxInvalidCounter;
    }
    
    //Setters
    public void setObstacleCount(int obstacleCount) {
        this.obstacleCount = obstacleCount;
    }
    public void setMaxInvalidCounter(int maxInvalidCounter) {
        this.maxInvalidCounter = maxInvalidCounter;
    }
    public static void setRandom(Random random) {
        ObstacleGridGenerator.random = random;
    }
    
    //Getters
    public int getObstacleCount() {    
        return obstacleCount;
    }
    public int getMaxInvalidCounter() {    
        return maxInvalidCounter;
    }
    public static Random getRandom() {
        return random;
    }

    //methods
    public boolean[][] obstacleGridGeneration(int rows, int columns) {
        boolean[][] obstacleGrid = new boolean[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                obstacleGrid[i][j] = false;
            }
        }

        while (obstacleCount < rows) {
            boolean failToAdd = false;
            int row = random.nextInt(rows); //generate rand from 0 - 3
            int col = random.nextInt(columns - 2) + 1; // 1 - 6 to prevent first column from being an obstacle

            for (int i = 0; i < columns; i++) {
                if (obstacleGrid[row][i]) {
                    failToAdd = true;
                }
            }
            if (!failToAdd && !isSurroundingObstacleEnabled(obstacleGrid, rows, columns, row, col)){
                obstacleGrid[row][col] = true;
                obstacleCount++; 
                continue;
            }
            if (obstacleCount == 3){
                maxInvalidCounter++;
                if (maxInvalidCounter > 100){
                    obstacleGrid = forcedObstacleGeneration(obstacleGrid, rows, columns);
                    break;
                }
            }
        }
        return obstacleGrid;
    }
    
    public boolean[][] forcedObstacleGeneration(boolean[][] obstacleGrid, int rows, int columns){
        while (obstacleCount < rows) {
            boolean failToAdd = false;
            int row = random.nextInt(rows); //generate rand from 0 - 3
            int col = random.nextInt(columns - 1) + 1; // 1 - 6 to prevent first column from being an obstacle
            
            for (int i = 0; i < columns; i++) {
                if (obstacleGrid[row][i]) {
                    failToAdd = true;
                }
            }
            if (!failToAdd){
                obstacleGrid[row][col] = true;
                obstacleCount++; 
                continue;
            }
        }
        return obstacleGrid;
    }
            
    
    public boolean isSurroundingObstacleEnabled(boolean[][] obstacleGrid, int rows, int columns, int row, int col){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (col != 6){
                    if (row == 0){
                        for (int x = row; x < row + 2; x++){
                            for (int y = col - 1; y < col + 2; y++){
                                if (obstacleGrid[x][y]){
                                    return true;
                                }
                            }
                        }
                    }
                    else if (row == rows - 1){
                        for (int x = row - 1; x < row + 1; x++){
                            for (int y = col - 1; y < col + 2; y++){
                                if (obstacleGrid[x][y]){
                                    return true;
                                }
                            }
                        }
                    }
                    else if (row == 1 || row == 2){
                        for (int x = row - 1; x < row + 2; x++){
                            for (int y = col - 1; y < col + 2; y++){
                                if (obstacleGrid[x][y]){
                                    return true;
                                }
                            }
                        }
                    }
                }
                else {
                    if (row == 0){
                        for (int x = row; x < row + 2; x++){
                            for (int y = col - 1; y < col + 1; y++){
                                if (obstacleGrid[x][y]){
                                    return true;
                                }
                            }
                        }
                    }
                    else if (row == rows - 1){
                        for (int x = row - 1; x < row + 1; x++){
                            for (int y = col - 1; y < col + 1; y++){
                                if (obstacleGrid[x][y]){
                                    return true;
                                }
                            }
                        }
                    }
                    else if (row == 1 || row == 2){
                        for (int x = row - 1; x < row + 2; x++){
                            for (int y = col - 1; y < col + 1; y++){
                                if (obstacleGrid[x][y]){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }    
        return false;
    }
}
