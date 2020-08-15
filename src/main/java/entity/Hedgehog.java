package entity;

/**
 *
 * @author Arvin Ng
 */
public class Hedgehog {

    private int row;
    private int column;
    private boolean stuck;

    public Hedgehog() {
        this.row = -1;
        this.column = -1;
        this.stuck = false;
    }
    
    //Setters
    public void setRow(int row) {
        this.row = row;
    }
    
    public void setColumn(int column) {
        this.column = column;
    }
    
    public void setStuck(boolean stuck){
        this.stuck = stuck;
    }
    
    //Getters
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public boolean isStuck(){
        return stuck;
    }
    
    //toString
    public String toString(){
        return "\nRow: " + row + "\nColumn: " + column;
    }
}
