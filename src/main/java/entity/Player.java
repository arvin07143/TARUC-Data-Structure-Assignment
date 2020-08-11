package entity;

public class Player {
    private String name ;
    private String id;
    private static int hedgehogCount;
    private Hedgehogs[] hedgehogs;
    
    //Constructors
    public Player (){
    }
    public Player (String name, String id, int hedgehogCount){
        this.name = name;
        this.id = id;
        this.hedgehogCount = hedgehogCount;
        for (int i = 0; i < hedgehogCount; i++){
            hedgehogs[i] = new Hedgehogs();
        }
    }

    //Get methods
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public int getHedgehogCount(){
        return hedgehogCount;
    }
    public Hedgehogs[] getHedgehogs(){
        return hedgehogs;
    }

    //Set methods
    public void SetName(String name){
        this.name = name;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setHedgehogCount(int hedgehogCount){
        this.hedgehogCount = hedgehogCount;
    }
    public void setHedgeHogs(int hedgehogNo, int row, int column){
        hedgehogs[hedgehogNo].setRow(row);
        hedgehogs[hedgehogNo].setColumn(row);
    }
   
   
}
