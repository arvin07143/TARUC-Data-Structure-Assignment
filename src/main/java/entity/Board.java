package entity;

import adt.*;

public class Board {

    public final int PLACEMENT = 0;

    public final int PLAY = 1;

    public final int GAME_OVER = 2;

    public final int FINISHED = -2;

    public final int NORMAL = -1;

    public final int WALLS = 1; //Wall. No token contained can move into the cell.

    public final int PITS = 2; //Pit. 1st token to fall into the pit will be trapped forever. Other tokens can then move through it normally

    public final int BLACK_HOLES = 3; //Black hole. No token contained can move. Ever.

    public int rowCount = 4;
    public int columnCount = 8;

    public int playerCount;
    public int hedgehogCount;
    public int winCount;

    public Cell[][] boardGrid = new Cell[rowCount][columnCount];
    public Player currentPlayer;

    private ListInterface<Player> playerList;
    public QueueInterface<Player> playerQueue;
    public StackInterface<Hedgehog> playerMovement = new ArrayStack();
    public Hedgehog previousMovement = new Hedgehog();
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

        currentPlayer = new Player(0,hedgehogCount); //generate player queue
        playerList = new ArrayList<>();
        playerList.add(currentPlayer);
        playerQueue = new ArrayQueue(playerCount);
        for(int i = 1 ; i < playerCount ; i++){
            Player temp = new Player(i,hedgehogCount);
            playerQueue.enqueue(temp);
            playerList.add(temp);
        }

        ObstacleGridGenerator obstacleGen = new ObstacleGridGenerator(); //generate obstacle grids
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

    //Get
    public int getColumnCount() {
        return columnCount;
    }

    public int getStage() {
        return stage;
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public int getRowCount() {
        return rowCount;
    }

    public Cell[][] getBoardGrid() {
        return boardGrid;
    }

    public ListInterface<Player> getPlayerList() {
        return playerList;
    }

    public ArrayList<Hedgehog> getHedgehogInRow(int row){
        ArrayList<Hedgehog> hedgehogList = new ArrayList();
        for(int j = 0 ; j < columnCount ; j++){
            if(boardGrid[row][j].cellStack.peek() != null && !(boardGrid[row][j].getCellStack().peek().isStuck()) && !(boardGrid[row][j].getCellStack().peek().isDisabled())){
                hedgehogList.add(boardGrid[row][j].cellStack.peek());
            }
        }
        return hedgehogList;
    }

    //Set

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
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

    //Methods

    public void newDiceNumber() {
        this.diceNumber = (int) (Math.random() * (rowCount) + 1);
    }

    public void initPlacement(int row , int col){
        if(boardGrid[row][col].getCellStack().peek() != null){
            boardGrid[row][col].getCellStack().peek().setDisabled(true);
        }
        boardGrid[row][col].pushHedgehog(currentPlayer.getHedgehogs().get(currentHedge));
        currentPlayer.getHedgehogs().get(currentHedge).setRow(row);
        currentPlayer.getHedgehogs().get(currentHedge).setColumn(col);
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

    public boolean moveToken(int startRow , int startColumn , int endRow , int endColumn){
        System.out.println(toString());
        previousMovement.setRow(startRow);
        previousMovement.setColumn(startColumn);
        if(boardGrid[startRow][startColumn].getCellStack().peek() != null &&
                !(boardGrid[startRow][startColumn].getCellStack().peek().isDisabled())){
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
            playerMovement.push(tempHedge);
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


    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < rowCount ; i++){
            for(int j = 0 ; j < columnCount ; j++){
                str.append(boardGrid[i][j].getCellStack().peek() != null);
                str.append("  ");
            }
            str.append("\n");
        }
        return str.toString();
    }

}
