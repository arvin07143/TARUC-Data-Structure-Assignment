package entity;

/**
 *
 * @author Arvin Ng
 */
public class Hedgehog {

    private int row;
    private int column;

    public Hedgehog() {
        this.row = -1;
        this.column = -1;
    }
    
    //Setters
    public void setRow(int row) {
        this.row = row;
    }
    
    public void setColumn(int column) {
        this.column = column;
    }
    
    //Getters
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    //toString
    public String toString(){
        return "\nRow: " + row + "\nColumn: " + column;
    }
}
