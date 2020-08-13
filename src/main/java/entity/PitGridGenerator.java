package entity;

import java.util.Random;

/**
 *
 * @author Arvin Ng
 */
public class PitGridGenerator {

    private static Random random = new Random();

    int pitCount;

    //true = pit
    public boolean[][] pitGridGeneration(int rows, int columns) {
        boolean[][] pitGrid = new boolean[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pitGrid[i][j] = false;
            }
        }

        while (pitCount < rows) {
            boolean failToAdd = false;
            int row = random.nextInt(rows); //generate rand from 0 - number of columns
            int col = random.nextInt(columns - 1) + 1; // 1-7 to prevent first column from being a pit

            for (int i = 0; i < rows; i++) {
                if (pitGrid[i][col]) {
                    failToAdd = true;
                }
            }

            for (int i = 0; i < columns; i++) {
                if (pitGrid[row][i]) {
                    failToAdd = true;
                }
            }

            if (!failToAdd) {
                pitGrid[row][col] = true;
                pitCount++;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(pitGrid[i][j] + " ");
            }
            System.out.println("");
        }

        return pitGrid;
    }
}
