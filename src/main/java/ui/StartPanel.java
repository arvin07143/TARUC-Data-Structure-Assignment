package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class StartPanel extends JFrame{
    private JLabel playerCountLabel;
    private JComboBox playerCount;
    private Integer[] playerChoices = {2,3,4};

    private JLabel hedgehogCountLabel;
    private JComboBox hedgehogCount;
    private Integer[] hedgehogChoices = {2,3,4};

    private JLabel winningCountLabel;
    private JComboBox winningCount;
    private Integer[] winningChoices = {1,2,3,4};

    private JLabel obstacleModeLabel;								//pit mode
    private JComboBox modeSelect;								//drop down menu for pit mode
    private String[] modes = { "None", "Walls", "Pits", "Black Holes" };	//content of drop down menu

    private JButton startButton;			//Start Game or New Game
    private JButton cancelButton;			//Skip Side Step



    public StartPanel(){
        super("New Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(350,400));

        playerCountLabel = new JLabel("Number of Players : ");
        playerCount = new JComboBox(playerChoices);
        playerCount.setPreferredSize(new Dimension(96,25));

        JPanel field1 = new JPanel();
        field1.setLayout(new BorderLayout());
        field1.add(playerCountLabel,BorderLayout.CENTER);
        field1.add(playerCount,BorderLayout.EAST);


        JPanel field2 = new JPanel();
        hedgehogCountLabel = new JLabel("Hedgehogs per Player : ");
        hedgehogCount = new JComboBox(hedgehogChoices);
        hedgehogCount.setPreferredSize(new Dimension(96,25));
        field2.setLayout(new BorderLayout());
        field2.add(hedgehogCountLabel,BorderLayout.WEST);
        field2.add(hedgehogCount,BorderLayout.EAST);

        JPanel field3 = new JPanel();
        field3.setLayout(new BorderLayout());
        winningCountLabel = new JLabel("Hedgehogs to Win : ",JLabel.TRAILING);
        winningCount = new JComboBox(winningChoices);
        winningCount.setPreferredSize(new Dimension(96,25));
        field3.add(winningCountLabel,BorderLayout.WEST);
        field3.add(winningCount,BorderLayout.EAST);

        JPanel field4 = new JPanel();
        field4.setLayout(new BorderLayout());
        obstacleModeLabel = new JLabel("Obstacle Type : ",JLabel.TRAILING);
        modeSelect = new JComboBox(modes);
        field4.add(obstacleModeLabel,BorderLayout.WEST);
        field4.add(modeSelect,BorderLayout.EAST);

        JPanel field5 = new JPanel();
        field5.setLayout(new BorderLayout());
        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
                setVisible(false);
            }
        });
        field5.add(startButton,BorderLayout.WEST);
        field5.add(Box.createRigidArea(new Dimension(15,0)),BorderLayout.CENTER);

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(startButton.getPreferredSize());
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        field5.add(cancelButton,BorderLayout.EAST);

        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
        this.add(new JLabel("Options"));
        this.add((Box.createRigidArea(new Dimension(0,20))));
        this.add(field1);
        this.add((Box.createRigidArea(new Dimension(0,20))));
        this.add(field2);
        this.add((Box.createRigidArea(new Dimension(0,20))));
        this.add(field3);
        this.add((Box.createRigidArea(new Dimension(0,20))));
        this.add(field4);
        this.add((Box.createRigidArea(new Dimension(0,20))));
        this.add(field5);
        this.add((Box.createRigidArea(new Dimension(0,20))));

        this.getRootPane().setBorder(new EmptyBorder(20,30,20,30));
        pack();
        this.setResizable(false);
        setVisible(true);
    }

    private void startGame() {
        GameFrame game = new GameFrame((Integer)playerCount.getSelectedItem(),(Integer)hedgehogCount.getSelectedItem()
                ,(Integer)winningCount.getSelectedItem(), (Integer)modeSelect.getSelectedIndex());
    }

    public static void main(String[] args){
        StartPanel panel = new StartPanel();
    }


}
