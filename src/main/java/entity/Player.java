package entity;

public class Player {
    private String name ;
    private String id;
    private int hedgehogCount;
    
    //Constructors
    public Player (){
    }
    public Player (String name, String id, int hedgehogCount){
        this.name = name;
        this.id = id;
    }

    //Get methods
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }

    //Set methods
    public void SetName(String name){
        this.name = name;
    }
    public void setId(String id){
        this.id = id;
    }
   
   
}
