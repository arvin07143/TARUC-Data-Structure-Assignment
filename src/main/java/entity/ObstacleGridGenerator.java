package entity;

import java.util.Random;

/**
 *
 * @author Arvin Ng
 */
public class ObstacleGridGenerator {

    private static Random random = new Random();

    int obstacleCount;

    //true = pit
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
            int col = random.nextInt(columns - 1) + 1; // 1 - 6 to prevent first column from being an obstacle

            for (int i = 0; i < rows; i++) {
                if (obstacleGrid[i][col]) {
                    failToAdd = true;
                }
            }

            for (int i = 0; i < columns; i++) {
                if (obstacleGrid[row][i]) {
                    failToAdd = true;
                }
            }

            if (!failToAdd) {
                obstacleGrid[row][col] = true;
                obstacleCount++;
            }
        }

       /* for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(obstacleGrid[i][j] + " ");
            }
            System.out.println("");
        }*/

        return obstacleGrid;
    }
}
