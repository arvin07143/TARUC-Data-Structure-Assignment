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
     * Standard pit. No token contained can move until tied for last
     */
    public final int PITS = 0;
    /**
     * Black hole pit. No token contained can move. Ever.
     */
    public final int BLACK_HOLES = 1;
    /**
     * Worm hole pit. Token is transported to a random location on the board.
     */
    public final int WORM_HOLES = 2;

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

        PitGridGenerator pitGen = new PitGridGenerator();
        boolean[][] pitGrid = pitGen.pitGridGeneration(rowCount, columnCount);

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (pitGrid[i][j]) {
                    boardGrid[i][j] = new Cell(true, 0); //true = pit
                } else {
                    boardGrid[i][j] = new Cell(false, 0);
                }
            }
        }
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
