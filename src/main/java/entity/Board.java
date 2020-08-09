package entity;

/**
 *
 * @author Arvin Ng
 */
public class Board {
    public int rowCount;
    public int columnCount;
    public Cell[][] boardGrid;
    public Player currentPlayer;
    public int diceNumber;

    public Board(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        
        boardGrid = new Cell[rowCount][columnCount];
        
        for(int i = 0 ; i < rowCount ; i++){
            for (int j = 0 ; j < columnCount ; j++){
                boardGrid[i][j] = new Cell(false , 0);
            }
        }
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public void setDiceNumber(int diceNumber) {
        this.diceNumber = (int)(Math.random() * ((6 - 1) + 1)) + 1;
    }
    
    
    
    
}
