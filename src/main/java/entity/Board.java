package entity;

/**
 *
 * @author Arvin Ng
 */
public class Board {

    /**
     * Placement stage
     */
    public final int PLACEMENT = 0;
    /**
     * Game play (movement) stage
     */
    public final int PLAY = 1;
    /**
     * Game over stage. Nothing can be done
     */
    public final int GAME_OVER = 2;

    /**
     * A final cell
     */
    public final int FINISHED = -2;
    /**
     * A normal cell, movement enabled
     */
    public final int NORMAL = -1;
    /**
     * Wall. No token contained can move into the cell.
     */
    public final int WALLS = 1;
    /**
     * Pit. 1st token to fall into the pit will be trapped forever. Other tokens can then move through it normally
     */
    public final int PITS = 2;
    /**
     * Black hole. No token contained can move. Ever.
     */
    public final int BLACK_HOLES = 3;

    
    public int rowCount = 4;
    public int columnCount = 7;

    public int playerCount;
    public int hedgehogCount;
    public int winCount;

    public Cell[][] boardGrid = new Cell[rowCount][columnCount];
    public Player currentPlayer;
    public int diceNumber;
    private boolean sideMoved;			//indicates whether a side move has been made
    private boolean forwardMoved;		//indicates whether a forward move has been made

    public Board(int playerCount, int hedgehogCount, int winCount) {
        this.playerCount = playerCount;
        this.hedgehogCount = hedgehogCount;
        this.winCount = winCount;

        ObstacleGridGenerator obstacleGen = new ObstacleGridGenerator();
        boolean[][] obstacleGrid = obstacleGen.obstacleGridGeneration(rowCount, columnCount);

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (obstacleGrid[i][j]) {
                    boardGrid[i][j] = new Cell(true, 0); //true = pit
                } else {
                    boardGrid[i][j] = new Cell(false, 0);
                }
            }
        }
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
    
    
    
    public int getDiceNumber() {
        return diceNumber;
    }

    public void setDiceNumber(int diceNumber) {
        this.diceNumber = (int) (Math.random() * ((6 - 1) + 1)) + 1;
    }

    public Cell[][] getBoardGrid() {
        return boardGrid;
    }
    
    

}
