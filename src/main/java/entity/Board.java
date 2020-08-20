package entity;

import adt.ArrayList;
import adt.ArrayQueue;
import adt.ListInterface;
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
    public int columnCount = 8;

    public int playerCount;
    public int hedgehogCount;
    public int winCount;

    public Cell[][] boardGrid = new Cell[rowCount][columnCount];
    public Player currentPlayer;

    private ListInterface<Player> playerList = new ArrayList<>();
    public QueueInterface<Player> playerQueue;
    public int diceNumber = 1;
    private boolean sideMoved;			//indicates whether a side move has been made
    private boolean forwardMoved;		//indicates whether a forward move has been made
    private int stage;					//placement, play, or game over

    private int currentHedge = 0;
    private int turnCounter ;

    public Board(int playerCount, int hedgehogCount, int winCount , int modeSelect) {
        this.playerCount = playerCount;
        this.hedgehogCount = hedgehogCount;
        this.winCount = winCount;

        currentPlayer = new Player(0,hedgehogCount);
        playerList.add(currentPlayer);
        playerQueue = new ArrayQueue(playerCount);
        for(int i = 1 ; i < playerCount ; i++){
            Player temp = new Player(i,hedgehogCount);
            playerQueue.enqueue(temp);
            playerList.add(temp);
        }

        ObstacleGridGenerator obstacleGen = new ObstacleGridGenerator();
        boolean[][] obstacleGrid = obstacleGen.obstacleGridGeneration(rowCount, columnCount);

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (obstacleGrid[i][j] && modeSelect != 0) {
                    boardGrid[i][j] = new Cell(true, modeSelect); //true = obstacles
                } else {
                    boardGrid[i][j] = new Cell(); //default cell no obstacles
                }
            }
        }

        stage = PLACEMENT; //set mode to placement mode for players to place tokens
        turnCounter = 0;
    }

    public void initPlacement(int row , int col){
        if(boardGrid[row][col].getCellStack().peek() != null){
            boardGrid[row][col].getCellStack().peek().setDisabled(true);
        }
        boardGrid[row][col].pushHedgehog(currentPlayer.getHedgehogs()[currentHedge]);
        currentPlayer.getHedgehogs()[currentHedge].setRow(row);
        currentPlayer.getHedgehogs()[currentHedge].setColumn(col);
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
        this.diceNumber = (int) (Math.random() * (rowCount) + 1);
    }

    public Cell[][] getBoardGrid() {
        return boardGrid;
    }

    public boolean moveToken(int startRow , int startColumn , int endRow , int endColumn){
        System.out.println(toString());
        if(boardGrid[startRow][startColumn].getCellStack().peek() != null && !(boardGrid[startRow][startColumn].getCellStack().peek().isDisabled())){
            Hedgehog tempHedge = boardGrid[startRow][startColumn].popHedgehog();
            if(boardGrid[startRow][startColumn].getCellStack().getSize() >= 1){
                boardGrid[startRow][startColumn].getCellStack().peek().setDisabled(false);
            }
            if(boardGrid[endRow][endColumn].getCellStack().peek() != null) {
                boardGrid[endRow][endColumn].getCellStack().peek().setDisabled(true);
            }
            tempHedge.setRow(endRow);
            tempHedge.setColumn(endColumn);
            boardGrid[endRow][endColumn].pushHedgehog(tempHedge);

            if(tempHedge.getColumn() == 7){
                int finishedID = tempHedge.getId();
                playerList.get(finishedID).setFinishedHedgehogs(playerList.get(finishedID).getFinishedHedgehogs()+1);
            }
            System.out.println(toString());
            return true;
        }
        return false;
    }

    public ListInterface<Player> getPlayerList() {
        return playerList;
    }

    public boolean moveTokenUp(int startRow , int startColumn){
        if(moveToken(startRow,startColumn,startRow-1,startColumn)){
            sideMoved = true;
            return true;
        }
        return false;
    }

    public boolean moveTokenDown(int startRow , int startColumn){
        if(moveToken(startRow,startColumn,startRow+1,startColumn)){
            sideMoved = true;
            return true;
        }
        return false;
    }

    public boolean moveTokenForward(int startRow , int startColumn){
        if(!forwardMoved && moveToken(startRow,startColumn,startRow,startColumn+1)){
            forwardMoved = true;
            return true;
        }
        return false;
    }

    public void newTurn(){
        turnCounter++;
        if(stage == PLACEMENT || forwardMoved){
            if(turnCounter % playerCount == 0){
                currentHedge++;
                if(currentHedge == hedgehogCount){
                    stage = PLAY;
                }
            }
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
            if(boardGrid[row][j].cellStack.peek() != null && !(boardGrid[row][j].getCellStack().peek().isStuck()) && !(boardGrid[row][j].getCellStack().peek().isDisabled())){
                hedgehogList.add(boardGrid[row][j].cellStack.peek());
            }
        }

        Hedgehog[] returnArray = new Hedgehog[hedgehogList.getArraySize()];
        for(int i = 0 ; i < hedgehogList.getArraySize() ; i++){
            returnArray[i] = hedgehogList.get(i);
        }

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

    public String toString(){
        String str = "";
        for(int i = 0 ; i < rowCount ; i++){
            for(int j = 0 ; j < columnCount ; j++){
                str += boardGrid[i][j].getCellStack().peek() != null;
                str += "  ";
            }
            str += "\n";
        }
        return str;
    }

}
