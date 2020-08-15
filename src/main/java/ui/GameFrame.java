package ui;

import entity.Board;
import entity.Cell;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class GameFrame extends JFrame  {

    private JPanel playArea;
    private CellView[][] playBoard;
    private JTextArea statusBar;

    private ImageIcon[] diceIcon = {ImageLoader.loadIcon("dice1.png"),ImageLoader.loadIcon("dice2.png")
            ,ImageLoader.loadIcon("dice3.png"),ImageLoader.loadIcon("dice4.png")};

    private JLabel currentDice;
    private JButton passSideways;

    private Board gameBoard ;

    public GameFrame(int numPlayer , int numHedge , int winHedge) {
        super("The Hedgehog Race");
        gameBoard = new Board(numPlayer,numHedge,winHedge);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        playArea = new JPanel();
        playArea.setBackground(Color.black);


        add(playArea, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        passSideways = new JButton("Pass Sideways Move");
        currentDice = new JLabel(ImageLoader.loadIcon("dice1.png"));
        currentDice.setBorder(new LineBorder(Color.BLACK,1));
        currentDice.setPreferredSize(new Dimension(80,80));
        statusBar = new JTextArea("\n\nPlease select your game settings and press the 'Start Game' button.");
        statusBar.setEditable(false);
        statusBar.setBorder(new LineBorder(Color.black,1));
        statusBar.setSize(new Dimension(600, 800));

        infoPanel.add(statusBar, BorderLayout.CENTER);
        infoPanel.add(passSideways,BorderLayout.EAST);
        infoPanel.add(currentDice,BorderLayout.WEST);

        add(infoPanel,BorderLayout.SOUTH);
        initPlayArea();
        setVisible(true);



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
                                    playBoard[i][0].setHiddenColor(Color.black);
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
                if(gameBoard.getBoardGrid()[i][j].isObstacleEnabled())playBoard[i][j].setBackgroundImage(0);
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


    public static void main(String[] args) {
        GameFrame game = new GameFrame(1,1,1); //testing conditions
    }


}
