package entity;

/**
 *
 * @author Arvin Ng
 */
public class Hedgehogs {

    private int row;
    private int column;

    public Hedgehogs() {
        this.row = -1;
        this.column = -1;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}
