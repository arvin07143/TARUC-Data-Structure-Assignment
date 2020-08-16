package entity;

import adt.ArrayList;
import adt.ArrayQueue;
import adt.QueueInterface;

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

    public QueueInterface<Player> playerQueue;
    public int diceNumber;
    private boolean sideMoved;			//indicates whether a side move has been made
    private boolean forwardMoved;		//indicates whether a forward move has been made
    private int stage;					//placement, play, or game over

    public Board(int playerCount, int hedgehogCount, int winCount) {
        this.playerCount = playerCount;
        this.hedgehogCount = hedgehogCount;
        this.winCount = winCount;

        currentPlayer = new Player(0,hedgehogCount);
        playerQueue = new ArrayQueue(playerCount);
        for(int i = 1 ; i < playerCount ; i++){
            playerQueue.enqueue(new Player(i,hedgehogCount));
        }

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

        stage = PLACEMENT; //set mode to placement mode for players to place tokens


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

    public int getStage() {
        return stage;
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public void newDiceNumber() {
        this.diceNumber = (int) (Math.random() * ((rowCount - 1) + 1)) + 1;
    }

    public Cell[][] getBoardGrid() {
        return boardGrid;
    }

    public void moveToken(int startRow , int startColumn , int endRow , int endColumn){
        try{
            if(true){
                //
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Coordinate parameters out of bounds");
        }
    }

    public void moveTokenUp(int startRow , int startColumn){
        if(!sideMoved){
            moveToken(startRow,startColumn,startRow-1,startColumn);
            sideMoved = true;
        }
    }

    public void moveTokenDown(int startRow , int startColumn){
        if(!sideMoved){
            moveToken(startRow,startColumn,startRow+1,startColumn);
            sideMoved = true;
        }
    }

    public void moveTokenForward(int startRow , int startColumn){
        if(!forwardMoved){
            moveToken(startRow,startColumn,startRow,startColumn+1);
            forwardMoved = true;
        }
    }

    public void newTurn(){
        if(forwardMoved || stage == PLACEMENT){
            playerQueue.enqueue(currentPlayer);
            currentPlayer = playerQueue.dequeue();
            sideMoved = false;
            forwardMoved = false;
            newDiceNumber();
        }
    }

    public Hedgehog[] getHedgehogInRow(int row){
        ArrayList<Hedgehog> hedgehogList = new ArrayList();
        for(int j = 0 ; j < columnCount ; j++){
            if(boardGrid[row][j].cellStack.peek() != null){
                hedgehogList.add(boardGrid[row][j].cellStack.peek());
            }
        }

        Hedgehog[] returnArray = new Hedgehog[hedgehogList.getArraySize()];
        returnArray = hedgehogList.getCurrentArray();

        return returnArray;
    }

    public void setSideMoved(boolean sideMoved) {
        this.sideMoved = sideMoved;
    }

    public void setForwardMoved(boolean forwardMoved) {
        this.forwardMoved = forwardMoved;
    }

    public boolean isSideMoved() {
        return sideMoved;
    }

    public void skipSideways(){
        sideMoved = true;
    }

}
