package ui;

import entity.Board;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame  {

    private JPanel playArea;
    private CellView[][] playBoard;
    private JTextArea statusBar;
    private JTextArea playerRemainingList;

    private ImageIcon[] diceIcon = {ImageLoader.loadIcon("dice1.png"),ImageLoader.loadIcon("dice2.png")
            ,ImageLoader.loadIcon("dice3.png"),ImageLoader.loadIcon("dice4.png")};

    private JLabel currentDice;
    private JButton passSideways;

    private Board gameBoard ;
    private String modeSelect;

    public GameFrame(int numPlayer , int numHedge , int winHedge , String modeSelect) {
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

        gameBoard = new Board(numPlayer,numHedge,winHedge);
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
                //pass the move
            }
        });
        currentDice = new JLabel(ImageLoader.loadIcon("dice1.png"));
        currentDice.setBorder(new LineBorder(Color.BLACK,1));
        currentDice.setPreferredSize(new Dimension(80,80));
        statusBar = new JTextArea("\n\nPlease select your game settings and press the 'Start Game' button.");
        statusBar.setEditable(false);
        statusBar.setBorder(new LineBorder(Color.black,1));
        statusBar.setPreferredSize(new Dimension(400,80));

        playerRemainingList = new JTextArea();
        playerRemainingList.setEditable(false);
        playerRemainingList.setBorder(new LineBorder(Color.black,1));
        playerRemainingList.setPreferredSize(new Dimension(200,80));

        infoPanel.add(passSideways);
        infoPanel.add(statusBar);
        infoPanel.add(playerRemainingList);
        infoPanel.add(currentDice);

        add(infoPanel,BorderLayout.SOUTH);
        initPlayArea();
        setVisible(true);
    }

    
    public Board getGameBoard(){
        return gameBoard;
    }
    
    public void initPlayArea(){
        playBoard = new CellView[4][8];
        playArea.setLayout(new GridLayout(4,8));


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                playBoard[i][j] = new CellView(-1);
                if(j == 0){
                    playBoard[i][0].addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            for(int i = 0 ; i < 4 ; i++){
                                if(e.getSource() == playBoard[i][0]){
                                    playBoard[i][0].setCellImage(ImageLoader.loadIcon("pI1.png"));
                                    playBoard[i][0].setHiddenColor(Color.red);
                                    revalidate();
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

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

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
        add(playArea,BorderLayout.CENTER);
        pack();
        setVisible(true);

    }

    public void drawCells(){
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 7 ; j++){
                if(gameBoard.getBoardGrid()[i][j].isObstacleEnabled()){
                    if (modeSelect.equals("Walls"))
                        playBoard[i][j].setBackgroundImage(0);
                    else if (modeSelect.equals("Pits"))
                        playBoard[i][j].setBackgroundImage(1);
                    else if (modeSelect.equals("Black Holes"))
                        playBoard[i][j].setBackgroundImage(2);
                }
                    
                playBoard[i][7].setBackgroundImage(-2);
            }
        }
    }


    public void setCurrentDiceImage(int x){
        currentDice.setIcon(diceIcon[x]);
    }

    public void showFrontMoves(int diceNumber){
        for(int j = 0 ; j < 8 ; j++){
            playBoard[diceNumber-1][j].setBorder(Color.red);
        }
    }

    private void setStackColor(){
        playBoard[1][1].setHiddenColor(Color.black); //testing
    }

    private void setLeaderText(){
        playerRemainingList.setText("    Player             Hedgehogs To Win \n");
    }


    public static void main(String[] args) {
        GameFrame game = new GameFrame(1,1,1,""); //testing conditions
        game.setLeaderText();
    }


}
