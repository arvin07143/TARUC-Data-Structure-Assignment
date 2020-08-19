package ui;

import adt.ListInterface;
import entity.Board;
import entity.Hedgehog;
import entity.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame {

    private JPanel playArea;
    private CellView[][] playBoard;
    private JTextArea statusBar;
    private JTextArea playerRemainingList;

    private Color topColor;

    private ImageIcon[] diceIcon = {ImageLoader.loadIcon("dice1.png"), ImageLoader.loadIcon("dice2.png")
            , ImageLoader.loadIcon("dice3.png"), ImageLoader.loadIcon("dice4.png")};

    private JLabel currentDice;
    private JButton passSideways;

    private Board gameBoard;
    private int modeSelect;
    private int columnAllowedHeight = 1;
    private int columnHeightCounter = 0;
    

    public GameFrame(int numPlayer, int numHedge, int winHedge, Integer modeSelect) {
        super("The Hedgehog Race");
        this.modeSelect = modeSelect;

        JMenuBar frameMenu = new JMenuBar();
        setJMenuBar(frameMenu);
        JMenu gameMenu = new JMenu("Game");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartPanel startPanel = new StartPanel();
                dispose();
            }
        });
        JMenuItem quitGame = new JMenuItem("Exit Game");
        quitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gameMenu.add(newGame);
        gameMenu.add(quitGame);

        JMenuItem rules = new JMenuItem("Rules");
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ruleListing =
                        "Rules of the HedgeHog Race:" +
                                "\n\nBeginning Stage:" +
                                "\n   Each player places a hedgehog in the first row until all their hedgehogs are placed." +
                                "\n   Hedgehogs can't be stacked until all other rows are filled." +

                                "\n\nMovement Stage:" +
                                "\n   Each player has the opportunity to make a sideways move before moving a hedgehog forward." +
                                "\n   A hedgehog in the highlighted row MUST move forward, even if it doesn't belong to the current player." +
                                "\n   If you don't want to move sideways, move a token forward or click the 'Skip Sideways Move' button." +
                                "\n   The goal is to reach the cake, but watch out for obstacles!" +

                                "\n\nObstacles:" +
                                "\n   Pits: a hedgehog in a pit can't move until it's tied for last place." +
                                "\n   Black Holes: a hedgehog in a black hole is stuck there forever.  Be extra careful around them." +
                                "\n   Worm Holes: a wormhole will randomly transport a hedgehog to another location on the board." +
                                "\n   Origins: an origin pit will bounce a hedgehog back to the beginning of the board." +

                                "\n\nHave Fun!";
                JOptionPane.showMessageDialog(null, ruleListing, "Rules", JOptionPane.PLAIN_MESSAGE);
            }
        });
        JMenuItem about = new JMenuItem("About Us");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String about = "The Hedgehog Race" +
                        "\n Created by : Arvin Ng Kwok Wai" +
                        "\n                     : Jeffrey Oh Tze Khai" +
                        "\n                     : Loke Kit Yao" +
                        "\n                     : Cham Chiang Hang" +
                        "\n Version: 1.0" +
                        "\n Release Date: 15 / 8 / 2020" +
                        "\n Description: " +
                        "\n    Project created for BACS2063 Assignment of TARUC" +
                        "\n    This game is about the furious battle of hedgehogs" +
                        "\n    having an epic race for the finish line";
                JOptionPane.showMessageDialog(null, about, "About", JOptionPane.PLAIN_MESSAGE);
            }
        });
        helpMenu.add(rules);
        helpMenu.add(about);

        frameMenu.add(gameMenu);
        frameMenu.add(helpMenu);

        gameBoard = new Board(numPlayer, numHedge, winHedge, modeSelect);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        playArea = new JPanel();
        playArea.setBackground(Color.black);


        add(playArea, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        passSideways = new JButton("Pass Sideways Move");
        passSideways.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameBoard.getStage() == gameBoard.PLAY && gameBoard.isSideMoved() != true) {
                    showFrontMoves(gameBoard.diceNumber);
                    gameBoard.setSideMoved(true);
                }
            }
        });
        currentDice = new JLabel(ImageLoader.loadIcon("dice1.png"));
        currentDice.setBorder(new LineBorder(Color.BLACK, 1));
        currentDice.setPreferredSize(new Dimension(80, 80));
        statusBar = new JTextArea("\n\nTEST");
        statusBar.setEditable(false);
        statusBar.setBorder(new LineBorder(Color.black, 1));
        statusBar.setPreferredSize(new Dimension(400, 80));

        playerRemainingList = new JTextArea();
        playerRemainingList.setEditable(false);
        playerRemainingList.setBorder(new LineBorder(Color.black, 1));
        playerRemainingList.setPreferredSize(new Dimension(200, 80));

        infoPanel.add(passSideways);
        infoPanel.add(statusBar);
        infoPanel.add(playerRemainingList);
        infoPanel.add(currentDice);

        add(infoPanel, BorderLayout.SOUTH);
        initPlayArea();
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameFrame game = new GameFrame(2, 2, 2, 0); //testing conditions
            }
        });

    }
    
    public void initPlayArea() {
        playBoard = new CellView[4][8];
        playArea.setLayout(new GridLayout(4, 8));
        

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                playBoard[i][j] = new CellView(-1);
                if (j == 0) {
                    playBoard[i][0].addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            for (int i = 0; i < 4; i++) {
                                if (e.getSource() == playBoard[i][0] && gameBoard.getStage() == gameBoard.PLACEMENT &&
                                    gameBoard.getBoardGrid()[i][0].getCellStackSize() < columnAllowedHeight) {
                                    setHiddenColor(i, 0);
                                    gameBoard.initPlacement(i, 0);
                                    playBoard[i][0].setCellImage(gameBoard.currentPlayer.getPlayerImage());
                                    gameBoard.newTurn();
                                    setStatusBar();
                                    if (gameBoard.getStage() == gameBoard.PLAY) {
                                        for (int a = 0; a < gameBoard.rowCount; a++) {
                                            for (int b = 0; b < gameBoard.columnCount; b++) {
                                                playBoard[a][b].resetBorder();
                                            }
                                        }
                                        System.out.println(gameBoard.toString());
                                        beginTurn();
                                    }
                                    columnHeightCounter++;
                                    if (columnHeightCounter % 4 == 0){
                                        columnAllowedHeight++;
                                    }
                                }
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            if (gameBoard.getStage() == gameBoard.PLACEMENT) {
                                for (int i = 0; i < gameBoard.rowCount; i++) {
                                    if (e.getSource() == playBoard[i][0]) {
                                        playBoard[i][0].setBorder(gameBoard.currentPlayer.getPlayerColor());
                                    }
                                }
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            if (gameBoard.getStage() == gameBoard.PLACEMENT) {
                                for (int i = 0; i < gameBoard.rowCount; i++) {
                                    if (e.getSource() == playBoard[i][0]) {
                                        playBoard[i][0].resetBorder();
                                    }
                                }
                            }
                        }
                    });
                }
                //add it to the board (next open grid spot
                playArea.add(playBoard[i][j]);
                playBoard[i][j].disableAllMoves();
                playBoard[i][j].repaint();
            }
        }


        drawCells();
        add(playArea, BorderLayout.CENTER);
        setStatusBar();
        pack();
        setVisible(true);

    } //initialise play area and handles placement stage

    public void drawCells() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (gameBoard.getBoardGrid()[i][j].isObstacleEnabled()) {
                    playBoard[i][j].setBackgroundImage(modeSelect);
                }
                playBoard[i][7].setBackgroundImage(-2);

                playBoard[i][j].disableAllMoves();
                playBoard[i][j].resetBorder();
                playBoard[i][j].repaint();
            }
        }
    } //draw cells according to the cell status in actual board

    public void updateCellStatus(int iniRow, int iniCol, int finalRow, int finalCol) {
        if (gameBoard.getBoardGrid()[iniRow][iniCol].getCellStackSize() == 0) {
            playBoard[iniRow][iniCol].setCellImage(null);
        } else {
            playBoard[iniRow][iniCol].setCellImage(ImageLoader.loadIcon(gameBoard.currentPlayer.playerImageName[gameBoard.getBoardGrid()[iniRow][iniCol].getCellStack().peek().getId()]));
        }
        playBoard[iniRow][iniCol].removeTopHidden();
        playBoard[iniRow][iniCol].repaint();

        playBoard[finalRow][finalCol].setCellImage(ImageLoader.loadIcon(gameBoard.currentPlayer.playerImageName[gameBoard.getBoardGrid()[finalRow][finalCol].getCellStack().peek().getId()]));

        setHiddenColor(finalRow, finalCol);
        playBoard[finalRow][finalCol].repaint();

    }

    private void setHiddenColor(int row, int col) {
        try {
            if (gameBoard.getBoardGrid()[row][col].getCellStackSize() > 1) {
                playBoard[row][col].setHiddenColor(topColor);
            }
        }
        catch (Exception dse) {
            playBoard[row][col].setHiddenColor(Color.lightGray);
        }
        playBoard[row][col].repaint();
    }


    public void beginTurn() {

        setLeaderText();
        drawCells();
        setCurrentDiceImage(gameBoard.getDiceNumber());
        showUpDownButtons();
    }

    public void showUpDownButtons() {
        Hedgehog[] playerHedgehogs = gameBoard.currentPlayer.getHedgehogs();
        boolean movable = false;
        int row;
        int col;
        
        for (int i = 0; i < playerHedgehogs.length; i++) {
            if (!playerHedgehogs[i].isDisabled() && !playerHedgehogs[i].isStuck()) {
                row = playerHedgehogs[i].getRow();
                col = playerHedgehogs[i].getColumn();
                if (row < gameBoard.rowCount - 1 && col != gameBoard.getColumnCount() - 1 && (!(gameBoard.getBoardGrid()[row+1][col].isObstacleEnabled() && modeSelect == 1))) {
                    playBoard[row][col].enableMoveDown();
                    movable = true;
                    playBoard[row][col].revalidate();
                    if(playBoard[row][col].getDownButton().getActionListeners().length == 0) {
                        playBoard[row][col].getDownButton().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for (int i = 0; i < gameBoard.rowCount; i++) {
                                    for (int j = 0; j < gameBoard.columnCount; j++) {
                                        if (e.getSource() == playBoard[i][j].getDownButton()) {
                                            getTopColor(i + 1, j);
                                            if (gameBoard.moveTokenDown(i, j)) System.out.println("CALLED");
                                            updateCellStatus(i, j, i + 1, j);
                                            gameBoard.setSideMoved(true);
                                            showFrontMoves(gameBoard.diceNumber);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
                if (row > 0 && col != gameBoard.getColumnCount() - 1 && (!(gameBoard.getBoardGrid()[row-1][col].isObstacleEnabled() && (modeSelect == 1 || playerHedgehogs[i].isStuck())))) {
                    playBoard[row][col].enableMoveUp();
                    movable = true;
                    if (playBoard[row][col].getUpButton().getActionListeners().length == 0) {
                        playBoard[row][col].getUpButton().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for (int i = 0; i < gameBoard.rowCount; i++) {
                                    for (int j = 0; j < gameBoard.columnCount; j++) {
                                        if (e.getSource() == playBoard[i][j].getUpButton()) {
                                            getTopColor(i - 1, j);
                                            if (gameBoard.moveTokenUp(i, j)) System.out.println("CALLED");
                                            updateCellStatus(i, j, i - 1, j);
                                            gameBoard.setSideMoved(true);
                                            showFrontMoves(gameBoard.diceNumber);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
        if (!movable){
            showFrontMoves(gameBoard.diceNumber);
            JOptionPane.showMessageDialog(this,"No available moves ! Turn automatically skipped");
        }
    }

    public void getTopColor(int targetRow, int targetCol) {
        if (gameBoard.boardGrid[targetRow][targetCol].getCellStack().peek() == null) topColor = null;
        else
            topColor = gameBoard.currentPlayer.colorOptions[gameBoard.boardGrid[targetRow][targetCol].getCellStack().peek().getId()];
    }

    public void showFrontMoves(int diceNumber) {
        int invalidHedgehogs = 0;

        statusBar.setText("\n\nPlease move the hedgehog on row " + gameBoard.getDiceNumber()+1);
        for (int i = 0; i < gameBoard.rowCount; i++) {
            for (int j = 0; j < gameBoard.columnCount; j++) {
                playBoard[i][j].disableAllMoves();
            }
        }

        Hedgehog[] hedgehogsInRow = gameBoard.getHedgehogInRow(diceNumber - 1);
        if (hedgehogsInRow.length != 0) {
            for (int i = 0; i < hedgehogsInRow.length; i++) {
                if (hedgehogsInRow[i].getColumn() != 7 && !hedgehogsInRow[i].isStuck()){
                    if (!(gameBoard.getBoardGrid()[hedgehogsInRow[i].getRow()][hedgehogsInRow[i].getColumn() + 1].isObstacleEnabled() && modeSelect == 1)) {
                        int cols = hedgehogsInRow[i].getColumn();

                        playBoard[diceNumber - 1][cols].enableMoveForward();
                        if (playBoard[diceNumber - 1][cols].getForwardButton().getActionListeners().length == 0) {
                            playBoard[diceNumber - 1][cols].getForwardButton().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    for (int i = 0; i < gameBoard.rowCount; i++) {
                                        for (int j = 0; j < gameBoard.columnCount; j++) {
                                            if (e.getSource() == playBoard[i][j].getForwardButton()) {
                                                getTopColor(i, j + 1);
                                                if (gameBoard.moveTokenForward(i, j)) {
                                                    updateCellStatus(i, j, i, j + 1);
                                                }
                                                gameBoard.setForwardMoved(true);

                                                for (int a = 0; a < gameBoard.columnCount; a++) {
                                                    playBoard[diceNumber - 1][a].resetBorder();
                                                    playBoard[diceNumber - 1][a].disableAllMoves();
                                                }
                                                gameBoard.newTurn();
                                                beginTurn();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                    else {
                        invalidHedgehogs++;
                    }
                }
                else {
                    invalidHedgehogs++;
                }
            }
            for (int j = 0; j < gameBoard.columnCount; j++) {
                playBoard[diceNumber - 1][j].setBorder(gameBoard.currentPlayer.getPlayerColor());
            }
            if (invalidHedgehogs == hedgehogsInRow.length){
                gameBoard.setForwardMoved(true);
                gameBoard.newTurn();
                for (int i = 0; i < gameBoard.rowCount; i++) {
                    for (int j = 0; j < gameBoard.columnCount; j++) {
                        playBoard[i][j].disableAllMoves();
                    }
                }
                beginTurn();
            }
        } else {
            gameBoard.setForwardMoved(true);
            gameBoard.newTurn();
            for (int i = 0; i < gameBoard.rowCount; i++) {
                for (int j = 0; j < gameBoard.columnCount; j++) {
                    playBoard[i][j].disableAllMoves();
                }
            }
            beginTurn();
        }
        playArea.repaint();
    }

    //Miscellaneous Setters
    

    private void setLeaderText() {
        ListInterface<Player> playerList = gameBoard.getPlayerList();
        String str = "";
        str += "    Player             Hedgehogs To Win \n";
        for(int i = 0 ; i < gameBoard.playerCount ; i++){
            if(playerList.get(i).isWinnable()){
                str += "    " + playerList.get(i).getColorName() + "                " + (gameBoard.hedgehogCount - playerList.get(i).getFinishedHedgehogs()) + "\n";
            }
            else str += "    " + playerList.get(i).getColorName() + "                " + "Not Winnable" + "\n";
        }
        playerRemainingList.setText(str);
    }

    public void setStatusBar() {
        statusBar.setText("\n\n" + "Player " + gameBoard.currentPlayer.getColorName() + " , Please place a hedgehog anywhere at the first column.");
    }

    public void setCurrentDiceImage(int x) {
        currentDice.setIcon(diceIcon[x - 1]);
    }
}
