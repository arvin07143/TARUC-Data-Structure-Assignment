package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CellView extends JPanel{

    private JLabel backgroundCell;
    private JLabel cellImage;
    /** transparent .png that defines the size of a cell */
    private ImageIcon standardBackgroundImage = ImageLoader.loadIcon("standardEnabledCell.png");
    private ImageIcon standardFinishedBackgroundImage = ImageLoader.loadIcon("finishline.jpg");
    private ImageIcon[] backgroundImage = { ImageLoader.loadIcon("standardEnabledCell.png") , ImageLoader.loadIcon("wall.png"), ImageLoader.loadIcon("pit.png"),
            ImageLoader.loadIcon("blackHole.png") };

    /** holds the hidden stack indicators */
    private JPanel leftPanel;
    /** hidden stack indicators */
    private ArrayList<JPanel> hiddenColor;

    /** Movement buttons */
    private JButton upButton, downButton, forwardButton;

    /** borders */
    private Border availableMoveBorder, stdBorder;

    public CellView(int obstacleMode) {

        super.setLayout(new BorderLayout());

        backgroundCell = new JLabel();
        setBackgroundImage(obstacleMode);
        add(backgroundCell, BorderLayout.CENTER);
        this.setComponentZOrder(backgroundCell, 0);

        cellImage = new JLabel();

        leftPanel = new JPanel(new GridLayout());
        leftPanel.setSize(new Dimension(12, 70));
        add(leftPanel, BorderLayout.WEST);

        hiddenColor = new ArrayList<JPanel>();

        upButton = new JButton("UP");
        downButton = new JButton("DOWN");
        forwardButton = new JButton(">");
        add(upButton, BorderLayout.NORTH);
        add(downButton, BorderLayout.SOUTH);
        add(forwardButton, BorderLayout.EAST);

        stdBorder = BorderFactory.createLineBorder(Color.black);
        setBorder(stdBorder);
    }

    public void setBackgroundImage(int cellType) {
        if (cellType == -1) {
            backgroundCell.setIcon(standardBackgroundImage);
        }
        else if (cellType == -2) {
            backgroundCell.setIcon(standardFinishedBackgroundImage);
        }
        else {
            backgroundCell.setIcon(backgroundImage[cellType]);
        }
    }

    public void setCellImage(ImageIcon image) {
        cellImage.setIcon(image);
        add(cellImage, BorderLayout.CENTER);
        setComponentZOrder(cellImage, 0);
        cellImage.setHorizontalAlignment(SwingConstants.CENTER);
        this.repaint();
    }

    public void disableAllMoves() { //disable moves buttons during placement
        forwardButton.setVisible(false);
        upButton.setVisible(false);
        downButton.setVisible(false);
    }

    public void setBorder(Color color) {
        availableMoveBorder = BorderFactory.createLineBorder(color, 3);
        setBorder(availableMoveBorder);
        this.repaint();
    }

    public void setHiddenColor(Color color) {
        hiddenColor.add(new JPanel());
        hiddenColor.get(hiddenColor.size() - 1).setBackground(color);
        hiddenColor.get(hiddenColor.size() -1).setBorder(stdBorder);
        leftPanel.add(hiddenColor.get(hiddenColor.size() - 1), BorderLayout.WEST);
        setComponentZOrder(leftPanel, 0);
        this.repaint();
    }

    public void removeTopHidden(){
        if (hiddenColor.size() >= 1){
            leftPanel.remove(hiddenColor.get(hiddenColor.size()-1));
            hiddenColor.remove(hiddenColor.size()-1);
            this.repaint();
        }
    }

    public void enableMoveUp() {
        upButton.setVisible(true);
        setComponentZOrder(upButton, 0);
        repaint();
    }

    /** Method enableMoveDown: <br />
     * enables and makes visible the down button
     */
    public void enableMoveDown() {
        downButton.setVisible(true);
        setComponentZOrder(downButton, 0);
        this.repaint();
    }

    /** Method enableMoveForward: <br />
     * enables and makes visible the forward button
     */
    public void enableMoveForward() {
        forwardButton.setVisible(true);
        setComponentZOrder(forwardButton, 0);
        this.repaint();
    }

    public void resetBorder() {
        setBorder(stdBorder);
        this.repaint();
    }

    public JButton getUpButton() {
        return upButton;
    }

    public JButton getDownButton() {
        return downButton;
    }

    public JButton getForwardButton() {
        return forwardButton;
    }


}
