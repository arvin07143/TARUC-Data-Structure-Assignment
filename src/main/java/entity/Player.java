package entity;

public class Player {

    private String name;
    private String id;
    private static int hedgehogCount;
    private Hedgehog[] hedgehogs;

    //Constructors
    public Player() {
    }

    public Player(String name, String id, int hedgehogCount) {
        this.name = name;
        this.id = id;
        this.hedgehogCount = hedgehogCount;
        hedgehogs = new Hedgehog[hedgehogCount];
        for (int i = 0; i < hedgehogCount; i++) {
            hedgehogs[i] = new Hedgehog();
        }
    }

    //Get methods
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getHedgehogCount() {
        return hedgehogCount;
    }

    public Hedgehog[] getHedgehogs() {
        return hedgehogs;
    }

    public Hedgehog getHedgehogs(int hedgehogNo){
        return hedgehogs[hedgehogNo-1];
    }
    
    //Set methods
    public void SetName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHedgehogCount(int hedgehogCount) {
        this.hedgehogCount = hedgehogCount;
    }

    public void setHedgeHog(int hedgehogNo, int row, int column) {
        hedgehogs[hedgehogNo-1].setRow(row);
        hedgehogs[hedgehogNo-1].setColumn(column);
    }
    
    //toString
    public String toString(){
        String outputStr = "";
        for (int i = 0; i < hedgehogCount; i++) {
            
            outputStr += "Hedgehog " + (i + 1) + ":" + hedgehogs[i] + "\n\n";
        }
        return outputStr;
    }
}
